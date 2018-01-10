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

import com.google.common.collect.ImmutableMap;
import lombok.val;
import oap.storage.IdentifierBuilder;
import oap.storage.MemoryStorage;
import oap.storage.Storage;
import org.testng.annotations.Test;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static oap.security.acl.AclService.ROOT;
import static oap.storage.Storage.LockStrategy.NoLock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by igor.petrenko on 29.12.2017.
 */
public class DefaultAclSchemaTest {
    private Storage<SecurityContainer<TestAclObject>> storage;
    private DefaultAclSchema schema;

    @Test
    public void beforeMethod() {
        storage = new MemoryStorage<>( IdentifierBuilder.annotationBuild(), NoLock );

        schema = new DefaultAclSchema(
            ImmutableMap.of(
                "root", new RootStorage(),
                "organization", storage,
                "user", storage ), "/acl/test-acl-schema.conf" );
    }

    @Test
    public void testValidateNewObject() {
        schema.validateNewObject( null, "root" );

        schema.validateNewObject( schema.getObject( ROOT ).get(), "user" );
        schema.validateNewObject( schema.getObject( ROOT ).get(), "organization" );
        assertThatThrownBy( () -> schema.validateNewObject( schema.getObject( ROOT ).get(), "unknown" ) ).hasMessageStartingWith( "unknown is not" );

        val organization = storage.store( new SecurityContainer<>( new TestAclObject( "org1" ), new AclObject( "organization", singletonList( ROOT ), singletonList( ROOT ), emptyList(), ROOT ) ) );
        schema.validateNewObject( organization.acl, "user" );
        assertThatThrownBy( () -> schema.validateNewObject( organization.acl, "root" ) ).hasMessageStartingWith( "root is not" );
    }

    @Test
    public void testGetPermissions() {
        val organization = storage.store( new SecurityContainer<>( new TestAclObject( "org1" ), new AclObject( "organization", singletonList( ROOT ), singletonList( ROOT ), emptyList(), ROOT ) ) );
        assertThat( schema.getPermissions( organization.id ) ).contains( "organization.read" );
    }
}