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

package oap.security.ws;

import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import oap.http.HttpResponse;
import oap.http.Request;
import oap.http.Session;
import oap.reflect.Reflection;
import oap.security.acl.AclService;
import oap.ws.Interceptor;

import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

/**
 * Created by igor.petrenko on 22.12.2017.
 */
@Slf4j
public class SecurityInterceptor2 implements Interceptor {
    private final AclService aclService;
    private final TokenService2 tokenService;

    public SecurityInterceptor2( AclService aclService, TokenService2 tokenService ) {
        this.aclService = aclService;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<HttpResponse> intercept( Request request, Session session, Reflection.Method method,
                                             Map<Reflection.Parameter, Object> originalValues ) {
        val annotation = method.findAnnotation( WsSecurity2.class ).orElse( null );
        if( annotation == null ) return Optional.empty();

        if( session == null ) {
            final HttpResponse httpResponse = HttpResponse.status( 500, "Session doesn't exist; check if service is session aware" );

            log.error( httpResponse.toString() );

            return Optional.of( httpResponse );
        }

        var userId = ( String ) session.get( "user-id" ).orElse( null );
        if( userId == null ) {
            val sessionToken = request.header( "Authorization" ).orElse( request.cookie( "Authorization" ).orElse( null ) );
            if( sessionToken == null ) {
                final HttpResponse httpResponse = HttpResponse.status( 401, "Session token is missing in header or cookie" );

                log.debug( httpResponse.toString() );

                return Optional.of( httpResponse );
            }
            val token = tokenService.getToken( sessionToken ).orElse( null );
            if( token == null ) {
                final HttpResponse httpResponse = HttpResponse.status( 401, format( "Token id [%s] expired or was " +
                    "not created", sessionToken ) );

                log.debug( httpResponse.toString() );

                return Optional.of( httpResponse );
            }
            userId = token.userId;
            session.set( "sessionToken", token.id );
            session.set( "user-id", userId );
        } else {
            log.trace( "User [{}] found in session", userId );
        }

        val objectId = getObjectId( method, annotation, originalValues );

        if( !aclService.checkOne( objectId, userId, annotation.permission() ) ) {
            val httpResponse = HttpResponse.status( 403, String.format( "User [%s] has no access to method [%s]", userId, method.name() ) );

            log.debug( httpResponse.toString() );

            return Optional.of( httpResponse );
        }

        return Optional.empty();
    }

    private String getObjectId( Reflection.Method method, WsSecurity2 annotation,
                                Map<Reflection.Parameter, Object> originalValues ) {
        val parameterName = annotation.object();
        val parameter = method.getParameter( parameterName );
        return originalValues.get( parameter ).toString();
    }
}
