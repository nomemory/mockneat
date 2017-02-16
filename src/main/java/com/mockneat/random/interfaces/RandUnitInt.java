package com.mockneat.random.interfaces;

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


import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.mockneat.random.utils.ValidationUtils.SIZE_BIGGER_THAN_ZERO;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.Validate.isTrue;

public interface RandUnitInt extends RandUnit<Integer> {
    default RandUnit<IntStream> intStream() {
        Supplier<IntStream> supp = () -> IntStream.generate(supplier()::get);
        return () -> supp;
    }
    default RandUnit<int[]> arrayPrimitive(int size) {
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<int[]> supp = () -> {
            final int[] result = new int[size];
            range(0, size).forEach(i -> result[i] = val());
            return result;
        };
        return () -> supp;
    }
    default RandUnit<Integer[]> array(int size) {
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Integer[]> supp = () -> {
            final Integer[] result = new Integer[size];
            range(0, size).forEach(i -> result[i] = val());
            return result;
        };
        return () -> supp;
    }
}
