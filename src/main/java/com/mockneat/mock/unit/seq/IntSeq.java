package com.mockneat.mock.unit.seq;

/*
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import com.mockneat.mock.interfaces.MockUnitInt;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static com.mockneat.mock.utils.ValidationUtils.INT_SEQ_OVERFLOW;
import static com.mockneat.mock.utils.ValidationUtils.SEQ_INVALID_RANGE;
import static java.lang.String.format;
import static org.apache.commons.lang3.Validate.isTrue;

public class IntSeq implements MockUnitInt {

    private int increment = 1;
    private int start = 0;
    private boolean cycle = true;
    private int max = Integer.MAX_VALUE;
    private int min = Integer.MIN_VALUE;
    private AtomicInteger internal;

    public IntSeq(int start, int increment, int max, int min, boolean cycle) {
        isTrue(min<max, SEQ_INVALID_RANGE, min, max);
        this.increment = increment;
        this.start = start;
        this.cycle = cycle;
        this.max = max;
        this.min = min;
        this.internal = new AtomicInteger(start);
    }

    public IntSeq(int start, int increment) {
        isTrue(min<max, SEQ_INVALID_RANGE, min, max);
        this.start = start;
        this.increment = increment;
        this.internal = new AtomicInteger(start);
    }

    public IntSeq(int increment) {
        isTrue(min<max, SEQ_INVALID_RANGE, min, max);
        this.increment = increment;
        this.internal = new AtomicInteger(start);
    }

    public IntSeq() {
        this.internal = new AtomicInteger(start);
    }

    public IntSeq start(int start) {
        isTrue(min<max, SEQ_INVALID_RANGE, min, max);
        this.start = start;
        this.internal = new AtomicInteger(start);
        return this;
    }

    public IntSeq increment(int increment) {
        this.increment = increment;
        return this;
    }

    public IntSeq cycle(boolean cycle) {
        this.cycle = cycle;
        return this;
    }

    public IntSeq max(int max) {
        isTrue(min<max, SEQ_INVALID_RANGE, min, max);
        this.max = max;
        return this;
    }

    public IntSeq min(int min) {
        isTrue(min<max, SEQ_INVALID_RANGE, min, max);
        this.min = min;
        return this;
    }

    private boolean nextValueOverflows() {
        return (increment>0) ? internal.get() > max :
                               internal.get() < min;
    }

    private void fail() {
        String fmt = format(INT_SEQ_OVERFLOW, min,  max);
        throw new IllegalStateException(fmt);
    }

    @Override
    public Supplier<Integer> supplier() {
        return () -> {
            if (nextValueOverflows()) {
                if (cycle) internal.set(start);
                else fail();
            }
            return internal.getAndAdd(increment);
        };
    }
}
