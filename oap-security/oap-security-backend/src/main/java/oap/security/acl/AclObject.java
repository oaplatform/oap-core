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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * Created by igor.petrenko on 20.12.2017.
 */
@ToString
@EqualsAndHashCode
public class AclObject implements Serializable {
    private static final long serialVersionUID = -6189594932715997498L;
    public final String type;
    public final List<String> parents;
    public final List<String> ancestors;
    public final List<Acl> acls;
    public String id;
    public String owner;


    @JsonCreator
    public AclObject( @JsonProperty String id,
                      @JsonProperty String type,
                      @JsonProperty List<String> parents,
                      @JsonProperty List<String> ancestors,
                      @JsonProperty List<Acl> acls,
                      @JsonProperty String owner ) {
        this.id = id;
        this.type = type;
        this.parents = new ArrayList<>( parents != null ? parents : emptyList() );
        this.ancestors = new ArrayList<>( ancestors != null ? ancestors : emptyList() );
        this.acls = new ArrayList<>( acls != null ? acls : emptyList() );
        this.owner = owner;
    }

    public AclObject( String type, List<String> parents, List<String> ancestors, List<Acl> acls, String owner ) {
        this( null, type, parents, ancestors, acls, owner );
    }

    @JsonInclude( JsonInclude.Include.NON_DEFAULT )
    @ToString
    @EqualsAndHashCode
    public static class Acl {
        public final AclRole role;
        public final String subjectId;
        public final String parent;
        public final boolean inheritance;

        public Acl( AclRole role, String subjectId, String parent, boolean inheritance ) {
            this.role = role;
            this.subjectId = subjectId;
            this.parent = parent;
            this.inheritance = inheritance;
        }
    }
}
