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

package oap.http.cors;

import com.google.common.collect.ImmutableSet;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import oap.http.Request;

import java.util.Set;
import java.util.regex.Pattern;

import static oap.http.Request.HttpMethod.DELETE;
import static oap.http.Request.HttpMethod.GET;
import static oap.http.Request.HttpMethod.HEAD;
import static oap.http.Request.HttpMethod.OPTIONS;
import static oap.http.Request.HttpMethod.POST;
import static oap.http.Request.HttpMethod.PUT;
import static oap.http.cors.RequestCors.NO_ORIGIN;

@EqualsAndHashCode
@ToString
public class PatternCorsPolicy implements CorsPolicy {

   public static final PatternCorsPolicy DEFAULT = new PatternCorsPolicy("^[^:/]*\\.oaplatform\\.org$",
       "Content-type, Authorization",true, ImmutableSet.of( HEAD, POST, GET, PUT, DELETE, OPTIONS ) );

   public final Pattern domainPattern;
   public final String allowHeaders;
   public final boolean allowCredentials;
   public boolean autoOptions = true;
   public Set<Request.HttpMethod> allowMethods;

   public PatternCorsPolicy( final String domainRegexp, final String allowHeaders,
                             final boolean allowCredentials, final Set<Request.HttpMethod> allowMethods ) {
      this.domainPattern = Pattern.compile( domainRegexp );
      this.allowHeaders = allowHeaders;
      this.allowCredentials = allowCredentials;
      this.allowMethods = allowMethods;
   }

   @Override
   public RequestCors getCors( Request request ) {
      final String origin = request.header( "Origin" ).orElse( NO_ORIGIN );

      final String allowedOrigin = domainPattern.matcher( origin ).matches() ? origin : NO_ORIGIN;

      return new RequestCors( allowedOrigin, allowHeaders, allowCredentials, autoOptions, allowMethods );
   }
}
