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
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitInt;

import java.util.Random;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Ints extends MockUnitBase implements MockUnitInt {

    private final Random random;

    public static Ints ints() {
        return MockNeat.threadLocal().ints();
    }

    protected Ints() {
        this(MockNeat.threadLocal());
    }

    public Ints(MockNeat mockNeat) {
        super(mockNeat);
        this.random = mockNeat.getRandom();
    }

    @Override
    public Supplier<Integer> supplier() {
        return random::nextInt;
    }

    /**
     * <p>This method can be used to generate arbitrary integer value in the [0, bound) range.</p>
     *
     * @param bound The interval bound.
     * @return A new {@code MockUnitInt}.
     */
    public MockUnitInt bound(int bound) {
        isTrue(bound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        Supplier<Integer> supp = () -> random.nextInt(bound);
        return () -> supp;
    }

    /**
     * <p>This method can be used to generate arbitrary integer value in the [0, bound) range.</p>
     *
     * @param upperBound The interval bound. Should be bigger than Zero.
     * @return A new {@code MockUnitInt}.
     */
    //TODO document
    public MockUnitInt upperBound(int upperBound) {
        return bound(upperBound);
    }

    /**
     * <p> This method can be used to generate arbitrary integer value in [lowerBound, Integer.MAX_VALUE]</p>
     *
     * @param lowerBound The lower bound. Should be different than Integer.MAX_VALUE and bigger than 0.
     * @return A new {@code MockUnitInt}
     */
    //TODO document
    public MockUnitInt lowerBound(int lowerBound) {
        isTrue(lowerBound != Integer.MAX_VALUE, LOWER_BOUND_DIFFERENT_THAN_INTEGER_MAX);
        isTrue(lowerBound >= 0, LOWER_BOUND_BIGGER_THAN_ZERO);
        return rangeClosed(lowerBound, Integer.MAX_VALUE);
    }

    /**
     * <p>This method can be used to generate arbitrary integer values in the [lowerBound, upperBound) range.</p>
     *
     * @param lowerBound The lower bound.
     * @param upperBound The upper bound.
     *
     * @return A new {@code MockUnitInt}.
     */
    public MockUnitInt range(int lowerBound, int upperBound) {
        notNull(lowerBound, "lowerBound");
        notNull(upperBound, "upperBound");
        isTrue(lowerBound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);
        Supplier<Integer> supp = () -> random.nextInt(upperBound - lowerBound) + lowerBound;
        return () -> supp;
    }

    /**
     * <p>This method can be used to generate arbitrary integer values in the [lowerBound, upperBound] range.</p>
     *
     * @param lowerBound The lower bound.
     * @param upperBound The upper bound.
     *
     * @return A new {@code MockUnitInt}
     */
    // TODO document
    public MockUnitInt rangeClosed(int lowerBound, int upperBound) {
        notNull(lowerBound, "lowerBound");
        notNull(upperBound, "upperBound");
        isTrue(lowerBound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);
        Supplier<Integer> supp = () -> random.nextInt(upperBound - lowerBound +1) + lowerBound;
        return () -> supp;
    }

    /**
     * <p>This method can be used to generate arbitrary integer values from the given {@code alphabet}.</p>
     *
     * @param alphabet The alphabet from which the values are selected.
     * @return A new {@code MockUnitInt}.
     */
    public MockUnitInt from(int[] alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<Integer> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
