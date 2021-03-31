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

package oap.application.module;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Created by igor.petrenko on 2021-03-30.
 */
@EqualsAndHashCode
@ToString
public class Supervision {
    public boolean supervise;
    public boolean thread;
    public boolean schedule;
    public List<String> preStartWith = List.of( "preStart" );
    public List<String> startWith = List.of( "start" );
    public List<String> preStopWith = List.of( "preStop" );
    public List<String> stopWith = List.of( "stop", "close" );
    public long delay; //ms
    public String cron; // http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger
}
