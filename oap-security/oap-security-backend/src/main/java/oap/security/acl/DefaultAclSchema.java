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

package oap.security.acl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import oap.io.Resources;
import oap.json.Binder;
import oap.reflect.TypeRef;
import oap.storage.ROStorage;
import oap.storage.Storage;
import oap.util.Lists;
import oap.util.Strings;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

/**
 * Created by igor.petrenko on 29.12.2017.
 */
@Slf4j
public class DefaultAclSchema implements AclSchema {
    private final String identity;
    private final Map<String, Storage<? extends oap.security.acl.SecurityContainer<?>>> objectStorage;
    private final Storage<AclSchemaContainer> schemaStorage;
    private final Optional<AclSchema> remoteSchema;
    private final String schemaPath;
    private AclSchemaBean schema;

    @JsonCreator
    public DefaultAclSchema(
        String identity,
        Storage<AclSchemaContainer> schemaStorage,
        Map<String, Storage<? extends oap.security.acl.SecurityContainer<?>>> objectStorage,
        String schema, AclSchema remoteSchema ) {
        this.identity = identity;
        this.schemaStorage = schemaStorage;
        this.objectStorage = objectStorage;
        this.remoteSchema = Optional.ofNullable( remoteSchema );
        this.schemaPath = schema;
    }

    public void start() {
        log.info( "acl schema path = {}", schema );

        final List<URL> urls = Resources.urls( schemaPath );
        log.debug( "found {}", urls );

        Preconditions.checkState( urls.size() == 1, "only one " + schemaPath + " allowed, but found " + urls );

        final Optional<URL> url = Resources.url( getClass(), schemaPath );
        log.debug( "found2 {}", url );

        val configs = Lists.tail( urls ).stream().map( Strings::readString ).toArray( String[]::new );

        val lSchema = Binder.hoconWithConfig( configs ).unmarshal( new TypeRef<AclSchemaBean>() {}, Lists.head( urls ) );


        for( val aclSchemaContainer : schemaStorage ) {
            if( log.isDebugEnabled() )
                log.debug( "found children schema {}", aclSchemaContainer );
            else
                log.info( "found children schema {}", aclSchemaContainer.owner );

            lSchema.findByPath( aclSchemaContainer.schema.parentPath ).merge( aclSchemaContainer.schema );
        }

        this.schema = remoteSchema.map( rs -> rs.addSchema( identity, lSchema ) ).orElse( lSchema );

        log.info( "acl schema = {}", this.schema );
    }

    @Override
    public void validateNewObject( AclObject parent, String newObjectType ) throws AclSecurityException {
        log.trace( "validateNewObject parent = {}, newObjectType = {}", parent, newObjectType );

        val parentSchema = getSchemas( parent );
        if( parentSchema.stream().noneMatch( schema -> schema.containsChild( newObjectType ) ) ) {
            throw new AclSecurityException( newObjectType + " is not allowed here." );
        }
    }

    @Override
    public Optional<AclObject> getObject( String id ) {
        for( val storage : objectStorage.values() ) {
            Optional<? extends SecurityContainer<?>> con;
            if( ( con = storage.get( id ) ).isPresent() ) return con.map( c -> c.acl );
        }

        return remoteSchema.flatMap( rs -> rs.getObject( id ) );
    }

    @Override
    public Stream<AclObject> selectObjects() {
        return remoteSchema
            .map( rs -> Stream.concat( selectLocalObjects(), rs.listObjects().stream() ) )
            .orElse( selectLocalObjects() );
    }

    @Override
    public List<AclObject> listObjects() {
        return remoteSchema
            .map( rs -> Stream.concat( selectLocalObjects(), rs.listObjects().stream() ) )
            .orElse( selectLocalObjects() )
            .collect( toList() );
    }

    @Override
    public Stream<AclObject> selectLocalObjects() {
        return objectStorage.values()
            .stream()
            .flatMap( ROStorage::select )
            .map( con -> con.acl );
    }

    @Override
    public Optional<AclObject> updateLocalObject( String id, Consumer<AclObject> cons ) {
        for( val os : objectStorage.values() ) {
            val res = os.update( id, con -> {
                cons.accept( con.acl );
                return con;
            } );

            if( res.isPresent() ) return res.map( r -> r.acl );
        }

        return Optional.empty();
    }

    @Override
    public Iterable<AclObject> objects() {
        return () -> selectObjects().iterator();
    }

    @Override
    public Iterable<AclObject> localObjects() {
        return () -> selectLocalObjects().iterator();
    }

    @Override
    public void deleteObject( String id ) {
        for( val os : objectStorage.values() ) {
            if( os.delete( id ).isPresent() ) return;
        }

        remoteSchema.ifPresent( rs -> rs.deleteObject( id ) );
    }

    @Override
    public List<String> getPermissions( String objectId ) {
        val object = getObject( objectId ).orElse( null );
        if( object == null ) return emptyList();

        val objectSchema = getSchemas( object );

        return objectSchema
            .stream()
            .flatMap( os -> os.permissions.stream() )
            .distinct()
            .collect( toList() );
    }

    @Override
    public AclSchemaBean addSchema( String owner, AclSchemaBean clientSchema ) {
        log.info( "add schema owner={}, schema={}", owner, clientSchema );
        schemaStorage.store( new AclSchemaContainer( owner, clientSchema ) );
        schema.findByPath( clientSchema.parentPath ).merge( clientSchema );
        log.debug( "result schema = {}", schema );
        return schema;
    }

    @SuppressWarnings( "unchecked" )
    private List<AclSchemaBean> getSchemas( AclObject parent ) {
        if( parent == null ) return singletonList( schema );
        if( parent.parents.isEmpty() )
            return schema.getChild( parent.type )
                .map( Collections::singletonList )
                .orElse( Collections.emptyList() );

        return parent.parents
            .stream()
            .flatMap( id ->
                getSchemas( getObject( id ).orElseThrow( () -> new IllegalStateException( "Unknown object " + id ) ) )
                    .stream()
                    .flatMap( aclType ->
                        aclType.getChild( parent.type )
                            .map( Stream::of )
                            .orElse( Stream.empty() ) )
            )
            .collect( toList() );
    }
}
