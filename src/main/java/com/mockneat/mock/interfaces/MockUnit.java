package com.mockneat.mock.interfaces;

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

import com.mockneat.mock.utils.LoopsUtils;
import com.mockneat.mock.utils.MockUnitUtils;
import com.mockneat.mock.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

@FunctionalInterface
@SuppressWarnings("unchecked")
public interface MockUnit<T> {

    Logger logger = LoggerFactory.getLogger(MockUnit.class);

    // Functional Method
    Supplier<T> supplier();

    default T val() { return supplier().get(); }

    default <R> R val(Function<T, R> funct) { return funct.apply(supplier().get()); }

    default String valStr() {
        Object val = supplier().get();
        if (null==val) {
            return "";
        }
        return supplier().get().toString();
    }

    default <R> MockUnit<R> map(Function<T, R> funct) {
        notNull(funct, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "funct");
        Supplier<R> supp = () -> funct.apply(supplier().get());
        return () -> supp;
    }

    default MockUnitInt mapToInt(Function<T, Integer> funct) {
        notNull(funct, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "funct");
        Supplier<Integer> supp = () -> funct.apply(val());
        return () -> supp;
    }

    default MockUnitDouble mapToDouble(Function<T, Double> funct) {
        notNull(funct, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "funct");
        Supplier<Double> supp = () -> funct.apply(val());
        return () -> supp;
    }

    default MockUnitLong mapToLong(Function<T, Long> funct) {
        notNull(funct, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "funct");
        Supplier<Long> supp = () -> funct.apply(val());
        return () -> supp;
    }

    default MockUnitString mapToString(Function<T, String> funct) {
        notNull(funct, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "funct");
        Supplier<String> supp = () -> funct.apply(val());
        return () -> supp;
    }

    default MockUnitString mapToString() {
        return () -> val()::toString;
    }

    default MockUnitString str() { return () -> supplier().get()::toString; }

    default MockUnit<Stream<T>> stream() {
        Supplier<Stream<T>> supp = () -> Stream.generate(supplier());
        return () -> supp;
    }

    default MockUnit<List<T>> list(Class<? extends List> listClass, int size) {
        notNull(listClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "listClass");
        isTrue(size>=0, ValidationUtils.SIZE_BIGGER_THAN_ZERO);
        Supplier<List<T>> supp = () -> {
            try {
                List<T> result = listClass.newInstance();
                LoopsUtils.loop(size, () -> MockUnitUtils.add(listClass, result, supplier()));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate list.", e);
            }
            return new ArrayList<>();
        };
        return () -> supp;
    }

    default MockUnit<List<T>> list(int size) {
        return list(ArrayList.class, size);
    }

    default MockUnit<Set<T>> set(Class<? extends Set> setClass, int size) {
        notNull(setClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "setClass");
        isTrue(size>=0, ValidationUtils.SIZE_BIGGER_THAN_ZERO);
        Supplier<Set<T>> supp = () -> {
            try {
                Set<T> result = setClass.newInstance();
                LoopsUtils.loop(size, () -> MockUnitUtils.add(setClass, result, supplier()));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate set.", e);
            }
            return new HashSet<>();
        };
        return () -> supp;
    }

    default MockUnit<Set<T>> set(int size) {
        return set(HashSet.class, size);
    }

    default MockUnit<Collection<T>> collection(Class<? extends Collection> collectionClass, int size) {
        notNull(collectionClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "collectionClass");
        isTrue(size>=0, ValidationUtils.SIZE_BIGGER_THAN_ZERO);
        Supplier<Collection<T>> supp = () -> {
            try {
                Collection<T> result = collectionClass.newInstance();
                LoopsUtils.loop(size, () -> MockUnitUtils.add(collectionClass, result, supplier()));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate collection.", e);
            }
            return new ArrayList<>();
        };
        return () -> supp;
    }

    default MockUnit<Collection<T>> collection(int size) {
        return collection(ArrayList.class, size);
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, int size, Supplier<R> keysSupplier) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keysSupplier, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "keysSupplier");
        isTrue(size>=0, ValidationUtils.SIZE_BIGGER_THAN_ZERO);
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.newInstance();
                LoopsUtils.loop(size, () -> MockUnitUtils.put(mapClass, result, keysSupplier, supplier()));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<R, T>> mapKeys(int size, Supplier<R> keysSupplier) {
        return mapKeys(HashMap.class, size, keysSupplier);
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, Iterable<R> keys) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.newInstance();
                keys.forEach(key -> MockUnitUtils.put(mapClass, result, key, supplier().get()));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Iterable<R> keys) {
        return mapKeys(HashMap.class, keys);
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, R[] keys) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key -> MockUnitUtils.put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<R, T>> mapKeys(R[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default MockUnit<Map<Integer, T>> mapKeys(Class<? extends Map> mapClass, int[] keys) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<Integer, T>> supp = () -> {
            try {
                Map<Integer, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key -> MockUnitUtils.put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default MockUnit<Map<Integer, T>> mapKeys(int[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default MockUnit<Map<Long, T>> mapKeys(Class<? extends Map> mapClass, long[] keys) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<Long, T>> supp = () -> {
            try {
                Map<Long, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key -> MockUnitUtils.put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default MockUnit<Map<Long, T>> mapKeys(long[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default MockUnit<Map<Double, T>> mapKeys(Class<? extends Map> mapClass, double[] keys) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<Double, T>> supp = () -> {
            try {
                Map<Double, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key -> MockUnitUtils.put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default MockUnit<Map<Double, T>> mapKeys(double[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, int size, Supplier<R> valuesSupplier) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(valuesSupplier, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "valuesSupplier");
        isTrue(size>=0, ValidationUtils.SIZE_BIGGER_THAN_ZERO);
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.newInstance();
                LoopsUtils.loop(size, () -> MockUnitUtils.put(mapClass, result, supplier(), valuesSupplier));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<T, R>> mapVals(int size, Supplier<R> valuesSupplier) {
        return mapVals(HashMap.class, size, valuesSupplier);
    }

    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, Iterable<R> values) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.newInstance();
                values.forEach(value -> MockUnitUtils.put(mapClass, result, supplier().get(), value));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<T, R>> mapVals(Iterable<R> values) {
        return mapVals(HashMap.class, values);
    }

    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, R[] values) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> MockUnitUtils.put(mapClass, result, supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<T, R>> mapVals(R[] values) {
        return mapVals(HashMap.class, values);
    }

    default MockUnit<Map<T, Integer>> mapVals(Class<? extends Map> mapClass, int[] values) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, Integer>> supp = () -> {
            try {
                Map<T, Integer> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> MockUnitUtils.put(mapClass, result, supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default MockUnit<Map<T, Integer>> mapVals(int[] values) {
        return mapVals(HashMap.class, values);
    }

    default MockUnit<Map<T, Long>> mapVals(Class<? extends Map> mapClass, long[] values) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, Long>> supp = () -> {
            try {
                Map<T, Long> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> MockUnitUtils.put(mapClass, result, supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default MockUnit<Map<T, Long>> mapVals(long[] values) {
        return mapVals(HashMap.class, values);
    }

    default MockUnit<Map<T, Double>> mapVals(Class<? extends Map> mapClass, double[] values) {
        notNull(mapClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, Double>> supp = () -> {
            try {
                Map<T, Double> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> MockUnitUtils.put(mapClass, result, supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default MockUnit<Map<T, Double>> mapVals(double[] values) {
        return mapVals(HashMap.class, values);
    }
}
