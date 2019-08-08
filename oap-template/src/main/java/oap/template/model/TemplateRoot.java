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

package oap.template.model;

/**
 * Created by igor.petrenko on 07.08.2019.
 */
public class TemplateRoot<T> implements TemplateNode {
    private final String name;
    private final Class<T> clazz;

    public TemplateRoot( String name, Class<T> clazz ) {
        this.name = name.replaceAll( "[\\s%\\-;\\\\/:*?\"<>|]", "_" );
        this.clazz = clazz;
    }

    @Override
    public void render( StringBuilder sb ) {
        var className = clazz.getName().replace( '$', '.' );

        sb.append( "package " ).append( getClass().getPackage().getName() ).append( ";\n"
            + "\n"
            + "import oap.util.Strings;\n"
            + "import oap.concurrent.StringBuilderPool;\n"
            + "\n"
            + "import java.util.*;\n"
            + "import java.util.function.BiFunction;\n"
            + "import com.google.common.base.CharMatcher;\n"
            + "\n"
            + "public  class " ).append( name ).append( " implements BiFunction<" ).append( className ).append( ", Accumulator, Object> {\n"
            + "\n"
            + "   @Override\n"
            + "   public Object apply( " ).append( className ).append( " s, Accumulator acc ) {\n"
            + "     try(var jbPool = StringBuilderPool.borrowObject()) {\n"
            + "     var jb = jbPool.getObject();\n"
            + "\n" );

        sb.append( "\n"
            + "     return acc.build();\n"
            + "     }\n"
            + "   }\n"
            + "}" );
    }
}
