package net.andreinc.mockneat.unit.types;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitLong;

import java.util.Random;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Longs extends MockUnitBase implements MockUnitLong {

    private final Random random;

    public Longs() {
        this(MockNeat.threadLocal());
    }

    public Longs(MockNeat mockNeat) {
        super(mockNeat);
        this.random = mockNeat.getRandom();
    }

    @Override
    public Supplier<Long> supplier() {
        return random::nextLong;
    }

    /**
     * <p>This method returns a {@code MockUnitLong} that can be used to generate arbitrary numbers in the [0, bound) interval.</p>
     *
     * @param bound The interval's bound.
     * @return A new {@code MockUnitLong}.
     */
    public MockUnitLong bound(long bound) {
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

    /**
     * <p>This method returns a {@code MockUnitLong} that can be used generate arbitrary numbers in the given range: [loweBound, upperBound)</p>
     *
     * @param lowerBound The lower bound of the interval.
     * @param upperBound The upper bound of the interval.
     * @return A new {@code MockUnitLong}.
     */
    public MockUnitLong range(long lowerBound, long upperBound) {
        notNull(lowerBound, "lowerBound");
        notNull(upperBound, "upperBound");
        isTrue(lowerBound >= 0, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound > 0, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound > lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);
        Supplier<Long> supplier = () ->
                mockNeat.longs().bound(upperBound - lowerBound).val() + lowerBound;
        return () -> supplier;
    }

    /**
     * <p>This method returns a {@code MockUnitLong} that can be used to generate arbitrary long numbers from the given alphabet.</p>
     *
     * @param alphabet The alphabet from which the values are selected.
     * @return A new {@code MockUnitLong}.
     */
    public MockUnitLong from(long[] alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<Long> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }

}
