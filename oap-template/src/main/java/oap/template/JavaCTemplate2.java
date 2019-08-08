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

package oap.template;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import oap.concurrent.StringBuilderPool;
import oap.template.model.TemplateNode;
import oap.template.model.TemplateRoot;
import oap.tools.MemoryClassLoader;
import oap.util.Pair;

import java.io.BufferedReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.joining;

@Slf4j
public class JavaCTemplate2<T, TLine extends Template.Line> implements Template<T, TLine> {
    private final BiFunction<T, Accumulator, ?> func;
    private final Map<String, String> overrides;
    private final Map<String, Supplier<String>> mapper;
    private final TemplateStrategy<TLine> map;

    @SneakyThrows
    @SuppressWarnings( "unchecked" )
    JavaCTemplate2( String name, Class<T> clazz, List<TLine> pathAndDefault, String delimiter, TemplateStrategy<TLine> map,
                    Map<String, String> overrides, Map<String, Supplier<String>> mapper,
                    Path cacheFile ) {
        this.map = map;
        this.overrides = overrides;
        this.mapper = mapper;
        var c = new StringBuilder();

        try {
            build( name, clazz, pathAndDefault, delimiter ).render( c );

            var line = new AtomicInteger( 0 );
            log.trace( "\n{}", new BufferedReader( new StringReader( c.toString() ) )
                .lines()
                .map( l -> String.format( "%3d", line.incrementAndGet() ) + " " + l )
                .collect( joining( "\n" ) )
            );

            var fullTemplateName = getClass().getPackage().getName() + "." + name;
            var mcl = new MemoryClassLoader( fullTemplateName, c.toString(), cacheFile );
            func = ( BiFunction<T, Accumulator, ?> ) mcl.loadClass( fullTemplateName ).newInstance();
        } catch( Exception e ) {
            log.error( c.toString() );
            throw e;
        }
    }

    private TemplateNode build( String name, Class<T> clazz, List<TLine> pathAndDefault, String delimiter ) {
        var root = new TemplateRoot( name, clazz );

        var num = new AtomicInteger();
        var fields = new FieldStack();

        int size = pathAndDefault.size();
        for( int x = 0; x < size; x++ ) {
            addPath( clazz, pathAndDefault.get( x ), delimiter, num, fields, x + 1 >= size );
        }
        return root;
    }

    private void addPath( Class<T> clazz, TLine line, String delimiter,
                          AtomicInteger num, FieldStack fields,
                          boolean last ) {

    }

    @Override
    public <R> R render( T source, Accumulator<R> accumulator ) {
        return ( R ) func.apply( source, accumulator );
    }

    @Override
    public String renderString( T source ) {
        try( var sbPool = StringBuilderPool.borrowObject() ) {
            return render( source, new StringAccumulator( sbPool.getObject() ) );
        }
    }

    private static class FieldStack {
        private Stack<HashMap<String, Pair<Type, String>>> stack = new Stack<>();

        FieldStack() {
            stack.push( new HashMap<>() );
        }

        public FieldStack up() {
            stack.push( new HashMap<>( stack.peek() ) );

            return this;
        }

        public FieldStack down() {
            stack.pop();

            return this;
        }

        public Pair<Type, String> computeIfAbsent( String key, Function<String, Pair<Type, String>> func ) {
            Pair<Type, String> v = stack.peek().get( key );
            if( v == null ) {
                v = func.apply( key );
                stack.peek().put( key, v );
            }
            return v;
        }

        public Pair<Type, String> get( String key ) {
            return stack.peek().get( key );
        }
    }
}
