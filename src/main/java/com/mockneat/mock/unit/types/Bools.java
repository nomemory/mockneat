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

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnit;
import java.util.Random;
import java.util.function.Supplier;
import static org.apache.commons.lang3.Validate.inclusiveBetween;

public class Bools implements MockUnit<Boolean> {

    private MockNeat mock;
    private Random random;

    public Bools(MockNeat mock) {
        this.mock = mock;
        this.random = mock.getRandom();
    }

    protected Boolean withProb(Double lower, Double trigger, Double upper) {
        return mock.doubles().range(lower, upper).val() < trigger;
    }

    public MockUnit<Boolean> probability(double probability) {
        inclusiveBetween(0.0, 100.0, probability);
        Supplier<Boolean> supp = () -> withProb(0.0, probability, 100.0);
        return () -> supp;
    }

    @Override
    public Supplier<Boolean> supplier() {
        return random::nextBoolean;
    }
}

