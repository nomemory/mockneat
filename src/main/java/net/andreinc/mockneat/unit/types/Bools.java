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
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitBase;

import java.util.Random;
import java.util.function.Supplier;

import static org.apache.commons.lang3.Validate.inclusiveBetween;

public class Bools extends MockUnitBase implements MockUnit<Boolean> {

    private final Random random;

    public Bools(Random random) {
        this(MockNeat.threadLocal());
    }

    public Bools(MockNeat mockNeat) {
        super(mockNeat);
        this.random = mockNeat.getRandom();
    }

    /**
     * <p>Returns a new {@code MockUnit<Boolean>} that returns arbitrary boolean values with a given probability.</p>
     *
     * @param probability The probability to obtain {@code true}. (Eg.: If the probability is 99.99, the {@code MockUnit<Boolean>} will generate {@code true} in 99.99% of the cases.
     * @return A new {@code MockUnit<Boolean>}
     */
    public MockUnit<Boolean> probability(double probability) {
        inclusiveBetween(0.0, 100.0, probability);
        Supplier<Boolean> supp = () -> mockNeat.doubles()
                                            .range(0.0, 100.0)
                                            .val() < probability;
        return () -> supp;
    }

    /**
     * <p>Returns the internal {@code Supplier<Boolean>} that is used to generate boolean values.</p>
     *
     * @return A {@code Supplier<Boolean>} used to generate arbitrary {@code Boolean} values.
     */
    @Override
    public Supplier<Boolean> supplier() {
        return random::nextBoolean;
    }
}

