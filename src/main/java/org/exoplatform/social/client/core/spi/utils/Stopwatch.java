/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.social.client.core.spi.utils;
import static org.exoplatform.social.client.core.spi.utils.Preconditions.checkNotNull;
import static org.exoplatform.social.client.core.spi.utils.Preconditions.checkState;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;


import java.util.concurrent.TimeUnit;
/**
 * Created by The eXo Platform SAS
 * Author : thanh_vucong
 *          thanhvucong.78@gmail.com
 * Nov 3, 2011  
 */
public class Stopwatch {

  private final Ticker ticker;
  private boolean isRunning;
  private long elapsedNanos;
  private long startTick;
  
  /**
   * Creates (but not start) a new stopwatch using {@link System#nanTime}
   */
  public Stopwatch() {
    this(Ticker.systemTicker());
  }
  
  public Stopwatch(Ticker ticker) {
    this.ticker = checkNotNull(ticker);
  }
  
  public boolean isRunning() {
    return this.isRunning;
  }
  
  /**
   * Starts the stopwatch
   * @return
   */
  public Stopwatch start() {
    checkState(!isRunning);
    isRunning = true;
    startTick = ticker.read();
    return this;
  }
  
  /**
   * Stops the stopwatch. Future reads will return the fixed duration that had
   * elapsed up to this point.
   * @return
   * @throws IllegalStateException if the stopwatch is already stopped.
   */
  public Stopwatch stop() {
    long tick = ticker.read();
    checkState(isRunning);
    isRunning = false;
    elapsedNanos += tick - startTick;
    return this;
  }
  
  /**
   * Sets the ecpased time for this stopwatch to zero 
   * and places it in a stopped state
   * @return
   */
  public Stopwatch reset() {
    elapsedNanos = 0;
    isRunning = false;
    return this;
  }
  
  private long elapsedNanos() {
    return isRunning ? ticker.read() - startTick + elapsedNanos : elapsedNanos; 
  }
  
  /**
   * Returns the current elapsed time shown on this stopwatch, expressed
   * in the desired time unit, with any fraction rounded down.
   *
   * <p>Note that the overhead of measurement can be more than a microsecond, so
   * it is generally not useful to specify {@link TimeUnit#NANOSECONDS}
   * precision here.
   */
  public long elapsedTime(TimeUnit desiredUnit) {
    return desiredUnit.convert(elapsedNanos(), NANOSECONDS);
  }

  
  /**
   * Returns the current elapsed time shown on this stopwatch, expressed
   * in milliseconds, with any fraction rounded down. This is identical to
   * {@code elapsedTime(TimeUnit.MILLISECONDS}.
   */
  public long elapsedMillis() {
    return elapsedTime(MILLISECONDS);
  }

  /**
   * Returns a string representation of the current elapsed time; equivalent to
   * {@code toString(4)} (four significant figures).
   */
  @Override public String toString() {
    return toString(4);
  }

  /**
   * Returns a string representation of the current elapsed time, choosing an
   * appropriate unit and using the specified number of significant figures.
   * For example, at the instant when {@code elapsedTime(NANOSECONDS)} would
   * return {1234567}, {@code toString(4)} returns {@code "1.235 ms"}.
   */
  public String toString(int significantDigits) {
    long nanos = elapsedNanos();

    TimeUnit unit = chooseUnit(nanos);
    double value = (double) nanos / NANOSECONDS.convert(1, unit);

    // Too bad this functionality is not exposed as a regular method call
    return String.format("%." + significantDigits + "g %s", value, abbreviate(unit));
  }

  private static TimeUnit chooseUnit(long nanos) {
    if (SECONDS.convert(nanos, NANOSECONDS) > 0) {
      return SECONDS;
    }
    if (MILLISECONDS.convert(nanos, NANOSECONDS) > 0) {
      return MILLISECONDS;
    }
    if (MICROSECONDS.convert(nanos, NANOSECONDS) > 0) {
      return MICROSECONDS;
    }
    return NANOSECONDS;
  }

  private static String abbreviate(TimeUnit unit) {
    switch (unit) {
      case NANOSECONDS:
        return "ns";
      case MICROSECONDS:
        return "\u03bcs"; // μs
      case MILLISECONDS:
        return "ms";
      case SECONDS:
        return "s";
      default:
        throw new AssertionError();
    }
  }

}
