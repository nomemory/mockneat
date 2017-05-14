package net.andreinc.mockneat.unit.objects;

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
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.ArrayList;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

//TODO document
public class Shufflers extends MockUnitBase {

    public Shufflers(MockNeat mockNeat) {
        super(mockNeat);
    }

    public <T> MockUnit<T[]> array(T[] source) {
        notNull(source, "source");
        Supplier<T[]> supplier = () -> {
            T[] result = source.clone();
            T tmp = null;
            for(int j, i = 0; i < result.length - 2; ++i) {
                j = mockNeat.ints().range(i, result.length).val();
                tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
            return result;
        };
        return () -> supplier;
    }

    public MockUnit<int[]> arrayInt(int[] source) {
        notNull(source, "source");
        Supplier<int[]> supplier = () -> {
            int[] result = source.clone();
            int tmp;
            for(int j, i = 0; i < result.length; ++i) {
                j = mockNeat.ints().range(i, result.length).val();
                tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
            return result;
        };
        return () -> supplier;
    }

    public MockUnit<long[]> arrayLong(long[] source) {
        notNull(source, "source");
        Supplier<long[]> supplier = () -> {
            long[] result = source.clone();
            long tmp;
            for(int j, i = 0; i < result.length; ++i) {
                j = mockNeat.ints().range(i, result.length).val();
                tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
            return result;
        };
        return () -> supplier;
    }

    public MockUnit<double[]> arrayDouble(double[] source) {
        notNull(source, "source");
        Supplier<double[]> supplier = () -> {
            double[] result = source.clone();
            double tmp;
            for(int j, i = 0; i < result.length; ++i) {
                j = mockNeat.ints().range(i, result.length).val();
                tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
            return result;
        };
        return () -> supplier;
    }

    public MockUnit<float[]> arrayFloat(float[] source) {
        notNull(source, "source");
        Supplier<float[]> supplier = () -> {
            float[] result = source.clone();
            float tmp;
            for(int j, i = 0; i < result.length; ++i) {
                j = mockNeat.ints().range(i, result.length).val();
                tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
            return result;
        };
        return () -> supplier;
    }

    public <T> MockUnit<ArrayList<T>> arrayList(ArrayList<T> source) {
        notNull(source, "source");
        Supplier<ArrayList<T>> supplier = () -> {
            ArrayList<T> result = (ArrayList<T>) source.clone();
            T tmp;
            for(int j, i = 0; i < result.size() - 2; ++i) {
                j = mockNeat.ints().range(i, result.size()).val();
                tmp = result.get(i);
                result.set(i, result.get(j));
                result.set(j, tmp);
            }
            return result;
        };

        return () -> supplier;
    }

    public MockUnitString string(String source) {
        notNull(source, "source");
        Supplier<String> supplier = () -> {
            char[] chars = source.toCharArray();
            char c;
            for(int j, i = 0; i < chars.length; ++i) {
                j  = mockNeat.ints().range(i, chars.length).val();
                c = chars[i];
                chars[i] = chars[j];
                chars[j] = c;
            }
            return new String(chars);
        };
        return () -> supplier;
    }
}
