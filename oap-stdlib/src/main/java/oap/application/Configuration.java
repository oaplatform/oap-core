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

import lombok.SneakyThrows;
import oap.io.Resources;
import oap.json.Binder;
import oap.util.Stream;
import oap.util.Strings;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Configuration<T> {
    private Class<T> clazz;
    private String name;

    public Configuration( Class<T> clazz, String name ) {
        this.clazz = clazz;
        this.name = name;
    }

    public List<T> fromClassPath() {
        return Stream.of( urlsFromClassPath() )
            .map( this::fromUrl )
            .toList();
    }

    public List<URL> urlsFromClassPath() {
        var ret = new ArrayList<URL>();
        ret.addAll( Resources.urls( "META-INF/" + name + ".json" ) );
        ret.addAll( Resources.urls( "META-INF/" + name + ".conf" ) );
        ret.addAll( Resources.urls( "META-INF/" + name + ".yaml" ) );
        ret.addAll( Resources.urls( "META-INF/" + name + ".yml" ) );

        return ret;
    }

    @SneakyThrows
    public T fromUrl( URL url ) {
        return fromString( Strings.readString( url ), Binder.Format.of( url, true ) );
    }

    public T fromResource( Class<?> contextClass, String name ) {
        return fromUrl( Resources.urlOrThrow( contextClass, name ) );
    }

    public T fromString( String config, Binder.Format format ) {
        return fromString( config, format, Map.of() );
    }

    public T fromString( String config, Binder.Format format, Map<String, Object> substitutions ) {
        return format.binder.unmarshal( clazz, Strings.substitute( config, substitutions ) );
    }
}
