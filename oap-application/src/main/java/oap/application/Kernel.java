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
package oap.application;

import com.fasterxml.jackson.core.type.TypeReference;
import oap.application.remote.RemoteInvocationHandler;
import oap.application.supervision.Supervisor;
import oap.json.Binder;
import oap.reflect.Reflect;
import oap.reflect.Reflection;
import oap.util.Maps;
import oap.util.Stream;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;

import java.net.URL;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toSet;
import static org.slf4j.LoggerFactory.getLogger;

public class Kernel {
    private static Logger logger = getLogger( Kernel.class );
    private final List<URL> modules;
    private Supervisor supervisor = new Supervisor();

    public Kernel( List<URL> modules ) {
        logger.debug( "modules = " + modules );
        this.modules = modules;
    }

    private Map<String, Module.Service> initializeServices( Map<String, Module.Service> services,
        Set<String> initialized ) {

        HashMap<String, Module.Service> deferred = new HashMap<>();

        for( Map.Entry<String, Module.Service> entry : services.entrySet() ) {
            Module.Service service = entry.getValue();
            String serviceName = entry.getKey();
            if( initialized.containsAll( service.dependsOn ) ) {
                logger.debug( "initializing " + serviceName );

                Reflection reflect = Reflect.reflect( service.implementation );

                Object instance;
                if( service.remoteUrl == null ) {
                    initializeServiceLinks( serviceName, service );
                    instance = reflect.newInstance( service.parameters );
                } else instance = RemoteInvocationHandler.proxy(
                    service.remoteUrl,
                    service.remoteName,
                    reflect.underlying
                );
                Application.register( serviceName, instance );
                if( service.supervision.supervise )
                    supervisor.startSupervised( serviceName, instance,
                        service.supervision.startWith,
                        service.supervision.stopWith );
                if( service.supervision.thread )
                    supervisor.startThread( serviceName, instance );
                else {
                    if( service.supervision.schedule && service.supervision.delay > 0 )
                        supervisor.scheduleWithFixedDelay( serviceName, (Runnable) instance,
                            service.supervision.delay, TimeUnit.SECONDS );
                    else if( service.supervision.schedule && service.supervision.cron != null )
                        supervisor.scheduleCron( serviceName, (Runnable) instance,
                            service.supervision.cron );
                }
                initialized.add( serviceName );
            } else {
                logger.debug( "dependencies are not ready - deferring " + serviceName + " -> " +
                    CollectionUtils.subtract( service.dependsOn, initialized ) );
                deferred.put( entry.getKey(), service );
            }
        }

        return deferred.size() == services.size() ? deferred : initializeServices( deferred, initialized );
    }

    private void initializeServiceLinks( String name, Module.Service service ) {
        for( Map.Entry<String, Object> entry : service.parameters.entrySet() )
            if( entry.getValue() instanceof String && ((String) entry.getValue()).startsWith( "@service:" ) ) {
                logger.debug( "for " + name + " linking " + entry );
                Object link = Application.service( ((String) entry.getValue()).substring( "@service:".length() ) );
                if( link == null ) throw new ApplicationException(
                    "for " + name + " service link " + entry.getValue() + " is not initialized yet" );
                entry.setValue( link );
            }
    }

    private Set<Module> initialize( Set<Module> modules, Set<String> initialized ) {
        HashSet<Module> deferred = new HashSet<>();

        for( Module module : modules ) {
            logger.debug( "initializing module " + module.name );
            if( initialized.containsAll( module.dependsOn ) ) {

                Map<String, Module.Service> def =
                    initializeServices( module.services, new LinkedHashSet<>() );
                if( !def.isEmpty() ) {
                    List<String> names = Stream.of( def.entrySet().stream() ).map( Map.Entry::getKey ).toList();
                    logger.error( "failed to initialize: " + names );
                    throw new ApplicationException( "failed to initialize services: " + names );
                }

                initialized.add( module.name );
            } else {
                logger.debug( "dependencies are not ready - deferring " + module.name );
                deferred.add( module );
            }
        }

        return deferred.size() == modules.size() ? deferred : initialize( deferred, initialized );
    }

    public void start() {
        start( Collections.emptyMap() );
    }

    @SuppressWarnings( "unchecked" )
    public void start( String config ) {
        start( (Map<String, Map<String, Object>>) Binder.hocon.unmarshal( Map.class, config ) );
    }

    public void start( Map<String, Map<String, Object>> config ) {
        logger.debug( "initializing application kernel..." );

        Set<Module> moduleConfigs = Stream.of( modules )
            .map( m -> Module.parse( m, config ) )
            .collect( toSet() );
        logger.trace( "modules = " + Stream.of( moduleConfigs ).map( m -> m.name ).toList() );

        if( !initialize( moduleConfigs, new HashSet<>() ).isEmpty() ) {
            logger.error( "failed to initialize: " + moduleConfigs );
            throw new ApplicationException( "failed to initialize modules" );
        }

        supervisor.start();
    }

    public void stop() {
        supervisor.stop();
        Application.unregisterServices();
    }

    public void start( Path configPath ) {
        start( configPath.toFile().exists() ? Binder.hocon.unmarshal(
            new TypeReference<Map<String, Map<String, Object>>>() {
            }, configPath ) : Maps.of() );
    }
}
