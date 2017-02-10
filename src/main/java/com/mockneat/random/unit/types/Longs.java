package com.mockneat.random.unit.types;

/**
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
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.mockneat.random.Rand;
import com.mockneat.random.interfaces.RandUnitLong;

import java.util.Random;
import java.util.function.Supplier;

import static com.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class Longs implements RandUnitLong {

    private Rand rand;
    private Random random;

    public Longs(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    @Override
    public Supplier<Long> supplier() {
        return random::nextLong;
    }

    public RandUnitLong bound(long bound) {
        isTrue(bound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        Supplier<Long> supplier = () -> {
            long b;
            long result;
            do {
                b = (random.nextLong() << 1) >>> 1;
                result = b % bound;
            } while (b-result+bound-1 < 0L);

            return result;
        };
        return () -> supplier;
    }

    public RandUnitLong range(long lowerBound, long upperBound) {
        notNull(lowerBound, INPUT_PARAMETER_NOT_NULL, "lowerBound");
        notNull(upperBound, INPUT_PARAMETER_NOT_NULL, "upperBound");
        isTrue(lowerBound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);
        Supplier<Long> supplier = () ->
                rand.longs().bound(upperBound - lowerBound).val() + lowerBound;
        return () -> supplier;
    }

    public RandUnitLong from(long[] alphabet) {
        notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<Long> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }

}
