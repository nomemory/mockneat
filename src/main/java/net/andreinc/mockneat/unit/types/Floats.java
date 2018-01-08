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
import net.andreinc.mockneat.abstraction.MockUnitFloat;

import java.util.Random;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Floats extends MockUnitBase implements MockUnitFloat {

    private final Random random;

    public Floats(MockNeat mockNeat) {
        super(mockNeat);
        this.random = mockNeat.getRandom();
    }

    @Override
    public Supplier<Float> supplier() {
        return random::nextFloat;
    }

    /**
     * <p>Returns a new {@code MockUnitFloat} that can be used to generate random float values in a given range [{@code lowerBound}, {@code upperBound}).</p>
     *
     * @param lowerBound The upper bound of the interval.
     * @param upperBound The lower bound of the interval.
     * @return A new {@code MockUnitFloat}.
     */
    public MockUnitFloat range(float lowerBound, float upperBound) {
        notNull(lowerBound, "lowerBound");
        notNull(upperBound, "upperBound");
        isTrue(lowerBound>=0.0f, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0.0f, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);

        Supplier<Float> supp = () -> random.nextFloat() * (upperBound - lowerBound) + lowerBound;
        return () -> supp;
    }

    /**
     * <p>Returns a new {@code MockUnitFloat} that can be used to generate float values bounded by a specific value: [0, {@code bound}).</p>
     *
     * @param bound The float bound.
     * @return A new {@code MockUnitFloat}.
     */
    public MockUnitFloat bound(float bound) {
        return range(0f, bound);
    }

    /**
     * <p>Returns a new {@code MockUnitFloat} that can be used generate float values from a given "alphabet".</p>
     *
     * @param alphabet An array of values from where the values are randomly picked.
     * @return A new {@code MockUnitFloat}.
     */
    public MockUnitFloat from(float[] alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<Float> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
