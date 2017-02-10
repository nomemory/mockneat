package com.mockneat.random.unit.objects;

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
import com.mockneat.random.interfaces.*;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class Objs {

    private Rand rand;

    public Objs(Rand rand){
        this.rand = rand;
    }

    public <T> RandUnit<T> from(List<T> alphabet) {
        notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<T> supp = () -> {
            int idx = rand.getRandom().nextInt(alphabet.size());
            return alphabet.get(idx);
        };
        return () -> supp;
    }

    public <T> RandUnit<T> from(T[] alphabet) {
        notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<T> supp = () -> {
            int idx = rand.getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }

    public <T extends Enum<?>> RandUnit<T> from(Class<T> enumClass) {
        notNull(enumClass, INPUT_PARAMETER_NOT_NULL, "enumClass");
        T[] arr = enumClass.getEnumConstants();
        return from(arr);
    }

    public <T> RandUnit<T> fromKeys(Map<T, ?> map) {
        notEmpty(map, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "map");
        Supplier<T> supp = () -> {
            T[] keys = (T[]) map.keySet().toArray();
            int idx = rand.getRandom().nextInt(keys.length);
            return keys[idx];
        };
        return () -> supp;
    }

    public <T> RandUnit<T> fromValues(Map<?, T> map) {
        notEmpty(map, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "map");
        Supplier<T> supp = () -> {
            T[] values = (T[]) map.values().toArray();
            int idx = rand.getRandom().nextInt(values.length);
            return values[idx];
        };
        return () -> supp;
    }

    public RandUnitInt fromInts(Integer[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitInt fromInts(int[] alphabet) {
        return () -> rand.ints().from(alphabet)::val;
    }

    public RandUnitInt fromInts(List<Integer> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitInt fromIntsValues(Map<?, Integer> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitInt fromIntsKeys(Map<Integer, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public RandUnitDouble fromDoubles(Double[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitDouble fromDoubles(double[] alphabet) {
        return () -> rand.doubles().from(alphabet)::val;
    }

    public RandUnitDouble fromDoubles(List<Double> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitDouble fromDoublesValues(Map<?, Double> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitDouble fromDoublesKeys(Map<Double, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public RandUnitLong fromLongs(Long[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitLong fromLongs(long[] alphabet) {
        return () -> rand.longs().from(alphabet)::val;
    }

    public RandUnitLong fromLongs(List<Long> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitLong fromLongsValues(Map<?, Long> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitLong fromLongsKeys(Map<Long, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public RandUnitString fromStrings(String[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitString fromStrings(List<String> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitString fromStringsValues(Map<?, String> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitString fromStringsKeys(Map<String, ?> map) {
        return () -> fromKeys(map)::val;
    }
}
