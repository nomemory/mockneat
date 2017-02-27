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
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.mockneat.mock.utils.ValidationUtils;
import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnit;
import java.util.Random;
import java.util.function.Supplier;

import static com.mockneat.mock.utils.ValidationUtils.*;
import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.Validate.notNaN;

public class Floats implements MockUnit<Float> {
    private Random random;

    public Floats(MockNeat mock) {
        this.random = mock.getRandom();
    }

    @Override
    public Supplier<Float> supplier() {
        return random::nextFloat;
    }

    public MockUnit<Float> range(float lowerBound, float upperBound) {
        notNull(lowerBound, INPUT_PARAMETER_NOT_NULL, "lowerBound");
        notNull(upperBound, INPUT_PARAMETER_NOT_NULL, "upperBound");
        finite(lowerBound);
        finite(upperBound);
        notNaN(lowerBound);
        notNaN(upperBound);
        isTrue(lowerBound>=0.0f, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0.0f, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);

        Supplier<Float> supp = () -> random.nextFloat() * (upperBound - lowerBound) + lowerBound;
        return () -> supp;
    }

    public MockUnit<Float> bound(float bound) {
        return range(0f, bound);
    }

    public MockUnit<Float> from(float[] alphabet) {
        ValidationUtils.notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<Float> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
