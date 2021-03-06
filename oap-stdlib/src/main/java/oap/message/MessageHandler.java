/*
 * The MIT License (MIT)
 *
 * Copyright (c) Open Application Platform Authors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package oap.message;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import lombok.extern.slf4j.Slf4j;
import oap.io.Sockets;
import org.apache.commons.codec.binary.Hex;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static oap.concurrent.Threads.isInterrupted;
import static oap.message.MessageProtocol.EOF_MESSAGE_TYPE;
import static oap.message.MessageProtocol.MD5_LENGTH;
import static oap.message.MessageProtocol.PROTOCOL_VERSION_1;
import static oap.message.MessageProtocol.STATUS_ALREADY_WRITTEN;
import static oap.message.MessageProtocol.STATUS_OK;
import static oap.message.MessageProtocol.STATUS_UNKNOWN_ERROR_NO_RETRY;
import static oap.message.MessageProtocol.STATUS_UNKNOWN_MESSAGE_TYPE;

/**
 * Input protocol:
 * <ul>
 * <li><b>byte</b>         - message type
 * <li><b>short</b>        - message version
 * <li><b>long</b>         - client id
 * <li><b>byte(16)</b>     - md5
 * <li><b>bytes(8)</b>     - reserved
 * <li><b>int</b>          - data size
 * <li><b>...</b>          - protocol data
 * </ul>
 * <p>
 * output protocol:
 * <ul>
 * <li><b>short</b>        - message version
 * <li><b>long</b>         - client id
 * <li><b>byte(16)</b>     - md5
 * <li><b>bytes(8)</b>     - reserved
 * <li><b>short</b>        - response status
 * <ul>
 * <li><b>0</b>   - ok
 * <li><b>1</b>   - unknown error
 * <li><b>100</b>   - unknown message type
 * <li><b>101</b>   - already written
 * </ul>
 * </ul>
 * <p>
 * <p>
 * Created by igor.petrenko on 2019-12-10.
 */
@Slf4j
public class MessageHandler implements Runnable, Closeable {
    private final Socket socket;
    private final long soTimeout;
    private final HashMap<Byte, MessageListener> listeners;
    private final MessageHashStorage control;
    private final long hashTtl;
    private final AtomicInteger activeCounter;
    private boolean closed;

    public MessageHandler( Socket socket, long soTimeout, HashMap<Byte, MessageListener> listeners,
                           MessageHashStorage control, long hashTtl, AtomicInteger activeCounter ) {
        this.socket = socket;
        this.soTimeout = soTimeout;
        this.listeners = listeners;
        this.control = control;
        this.hashTtl = hashTtl;
        this.activeCounter = activeCounter;

        Metrics.gauge( "messages_hash", Tags.empty(), control, MessageHashStorage::size );
    }

    @Override
    public void run() {
        activeCounter.incrementAndGet();

        String hostName = null;

        try {
            hostName = socket.getInetAddress().getCanonicalHostName();

            var out = new DataOutputStream( socket.getOutputStream() );
            var in = new DataInputStream( socket.getInputStream() );
            socket.setSoTimeout( ( int ) soTimeout );
            socket.setKeepAlive( true );

            while( !closed && !isInterrupted() ) {
                var messageType = in.readByte();
                log.trace( "new message from {}", hostName );

                if( messageType == EOF_MESSAGE_TYPE ) {
                    log.info( "EOF" );
                    closed = true;
                    return;
                }

                var messageVersion = in.readShort();
                var clientId = in.readLong();
                var md5 = in.readNBytes( MD5_LENGTH );

                in.skipBytes( 8 ); // reserved
                var size = in.readInt();

                log.trace( "type = {}, version = {}, clientId = {}, md5 = {}, size = {}",
                    messageType, messageVersion, clientId, Hex.encodeHexString( md5 ), size );

                if( !control.contains( messageType, clientId, md5 ) ) {
                    var listener = listeners.get( messageType );
                    if( listener == null ) {
                        control.add( messageType, clientId, md5 );
                        in.skipNBytes( size );
                        writeResponse( out, STATUS_UNKNOWN_MESSAGE_TYPE, clientId, md5 );
                    } else {
                        var data = in.readNBytes( size );
                        short status;
                        try {
                            status = listener.run( messageVersion, hostName, size, data );
                        } catch( Exception e ) {
                            log.error( "[" + hostName + "] " + e.getMessage(), e );
                            writeResponse( out, STATUS_UNKNOWN_ERROR_NO_RETRY, clientId, md5 );
                            break;
                        }
                        writeResponse( out, status, clientId, md5 );
                        if( status == STATUS_OK ) {
                            Metrics.counter( "messages", Tags.of( "type", String.valueOf( Byte.toUnsignedInt( messageType ) ) ) ).increment();
                            control.add( messageType, clientId, md5 );
                        } else
                            log.trace( "WARN [{}/{}] buffer ({}, " + size + ") status == {}.)",
                                hostName, clientId, Hex.encodeHexString( md5 ), MessageProtocol.statusToString( status ) );
                    }
                } else {
                    log.warn( "[{}/{}] buffer ({}, {}) already written.)", hostName, clientId, Hex.encodeHexString( md5 ), size );
                    Metrics.counter( "oap.message.server.already_written", "type", String.valueOf( Byte.toUnsignedInt( messageType ) ) ).increment();

                    in.skipNBytes( size );

                    writeResponse( out, STATUS_ALREADY_WRITTEN, clientId, md5 );
                }

                control.update( hashTtl );
            }
        } catch( EOFException e ) {
            log.debug( "[{}] {} ended, closed", hostName, socket );
        } catch( SocketTimeoutException e ) {
            log.info( "[{}] no activity on socket for {}ms, timeout, closing...", hostName, soTimeout );
            log.trace( "[" + hostName + "] " + e.getMessage(), e );
        } catch( Exception e ) {
            log.error( "[" + hostName + "] " + e.getMessage(), e );
        } finally {
            activeCounter.decrementAndGet();
            Sockets.close( socket );
            log.debug( "socket closed: {}", socket );
        }
    }

    public void writeResponse( DataOutputStream out, short status, long clientId, byte[] md5 ) throws IOException {
        out.writeByte( PROTOCOL_VERSION_1 );
        out.writeLong( clientId );
        out.write( md5 );
        out.write( MessageProtocol.RESERVED, 0, MessageProtocol.RESERVED.length );
        out.writeShort( status );
    }

    @Override
    public void close() {
        this.closed = true;
    }
}
