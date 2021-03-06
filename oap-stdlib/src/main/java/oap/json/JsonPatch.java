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

package oap.json;

import lombok.extern.slf4j.Slf4j;
import oap.reflect.TypeRef;
import oap.util.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class JsonPatch {

    public static Map<String, Object> patch( Object o, String patch ) {
        return patch( o, null, map -> map, patch );
    }

    public static Map<String, Object> patch( Object o, String key, Function<Map<String, Object>, Map<String, Object>> select, String patch ) {
        Map<String, Object> objectMap = Binder.json.unmarshal( new TypeRef<>() {}, o );
        Map<String, Object> patchMap = Binder.json.unmarshal( new TypeRef<>() {}, patch );
        Map<String, Object> innerMap = select.apply( objectMap );
        if( innerMap.isEmpty() ) {
            @SuppressWarnings( "unchecked" )
            List<Map<String, Object>> innerList = ( List<Map<String, Object>> ) objectMap.getOrDefault( key, Lists.empty() );
            innerList.add( patchMap );
            objectMap.put( key, innerList );
        } else innerMap.putAll( patchMap );
        return objectMap;
    }
}
