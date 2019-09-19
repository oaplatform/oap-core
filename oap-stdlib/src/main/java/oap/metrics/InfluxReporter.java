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

package oap.metrics;

import lombok.extern.slf4j.Slf4j;
import oap.net.Inet;
import oap.util.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
public class InfluxReporter {
    public String application = Strings.UNKNOWN;
    protected String host;
    protected int port;
    protected String database;
    protected String login;
    protected String password;
    protected ArrayList<String> include = new ArrayList<>();
    protected ArrayList<String> exclude = new ArrayList<>();
    protected ArrayList<String> aggregates = new ArrayList<>();
    protected HashMap<String, Object> tags = new HashMap<>();
    protected long connectionTimeout = 10000;
    protected long readTimeout = 10000;
    protected long writeTimeout = 10000;
    protected long period = 60 * 1000;
    protected boolean reset_timers_after_report = false;
    protected boolean skip_empty = false;
    protected InfluxMetricsConfiguration imc = new InfluxMetricsConfiguration();

    private InfluxDBReporter reporter;

    public void start() {
        log.info( "host = {}, application = {}, database = {}, login = {}, period = {} ms",
            host, application, database, login, period );
        log.info( "aggregates = {}", aggregates );

        InfluxDBReporter.Builder builder = InfluxDBReporter
            .forRegistry( Metrics.registry )
            .withFilter( new ReporterFilter( include, exclude ) )
            .withAggregates( aggregates )
            .withTag( "host", Inet.HOSTNAME )
            .withTag( "app", application )
            .convertRatesTo( TimeUnit.MINUTES )
            .convertDurationsTo( TimeUnit.MICROSECONDS )
            .withConnect( this.host, port, database, login, password )
            .withConnectionTimeout( connectionTimeout )
            .withReadTimeout( readTimeout )
            .withWriteTimeout( writeTimeout )
            .withResetTimersAfterReport( reset_timers_after_report )
            .withSkipEmpty( skip_empty )
            .withImc( imc );
        tags.forEach( ( name, value ) -> builder.withTag( name, String.valueOf( value ) ) );
        reporter = builder.build();
        reporter.start( period, TimeUnit.MILLISECONDS );
    }

    public void stop() {
        reporter.stop();
    }
}