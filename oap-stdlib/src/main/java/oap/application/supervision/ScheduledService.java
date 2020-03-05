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
package oap.application.supervision;

import oap.concurrent.scheduler.Scheduled;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class ScheduledService implements Supervised, Runnable {
    private static Logger logger = getLogger( ScheduledService.class );
    protected final Runnable runnable;
    private Scheduled scheduled;

    public ScheduledService( Runnable runnable ) {
        this.runnable = runnable;
    }

    @Override
    public void preStart() {
        
    }

    public void start() {
        this.scheduled = schedule();
    }

    protected abstract Scheduled schedule();

    @Override
    public void preStop() {
        
    }

    @Override
    public void stop() {
        Scheduled.cancel( scheduled );
    }

    @Override
    public void run() {
        try {
            this.runnable.run();
        } catch( Exception e ) {
            logger.error( e.getMessage(), e );
        }
    }
}
