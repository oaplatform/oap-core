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

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static oap.testng.Asserts.pathOfTestResource;
import static oap.testng.Asserts.urlOfTestResource;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationConfigurationTest {
    @Test
    public void load() {
        System.setProperty( "KEY", "VALUE" );
        ApplicationConfiguration config = ApplicationConfiguration.load(
            pathOfTestResource( getClass(), "application.conf" ),
            pathOfTestResource( getClass(), "conf.d" )
        );
        assertThat( config.services ).isEqualTo( Map.of(
            "ServiceOneP1", Map.of( "parameters", Map.of( "i2", "100", "i3", "VALUE" ) ),
            "ServiceTwo", Map.of( "parameters", Map.of( "j", "3s" ) )
        ) );
    }

    @Test
    public void loadWithUrls() {
        System.setProperty( "KEY", "VALUE" );
        ApplicationConfiguration config = ApplicationConfiguration.load(
            urlOfTestResource( getClass(), "application.conf" ),
            List.of(
                urlOfTestResource( getClass(), "conf.d/test.conf" ),
                urlOfTestResource( getClass(), "conf.d/test2.yaml" )
            ) );
        assertThat( config.services ).isEqualTo( Map.of(
            "ServiceOneP1", Map.of( "parameters", Map.of( "i2", "100", "i3", "VALUE" ) ),
            "ServiceTwo", Map.of( "parameters", Map.of( "j", "3s" ) )
        ) );
    }
}
