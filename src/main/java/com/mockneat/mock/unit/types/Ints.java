package com.mockneat.mock.unit.types;

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

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.utils.ValidationUtils;
import com.mockneat.mock.interfaces.MockUnitInt;
import java.util.Random;
import java.util.function.Supplier;

import static com.mockneat.mock.utils.ValidationUtils.*;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class Ints implements MockUnitInt {

    private Random random;

    public Ints(MockNeat mock) {
        this.random = mock.getRandom();
    }

    @Override
    public Supplier<Integer> supplier() {
        return random::nextInt;
    }

    public MockUnitInt bound(int bound) {
        isTrue(bound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        Supplier<Integer> supp = () -> random.nextInt(bound);
        return () -> supp;
    }

    public MockUnitInt range(int lowerBound, int upperBound) {
        notNull(lowerBound, INPUT_PARAMETER_NOT_NULL, "lowerBound");
        notNull(upperBound, INPUT_PARAMETER_NOT_NULL, "upperBound");
        isTrue(lowerBound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);
        Supplier<Integer> supp = () -> random.nextInt(upperBound - lowerBound) + lowerBound;
        return () -> supp;
    }

    public MockUnitInt from(int[] alphabet) {
        ValidationUtils.notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<Integer> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
