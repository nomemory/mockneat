package net.andreinc.mockneat.interfaces;

/*
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.utils.MockUnitUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static net.andreinc.mockneat.utils.MockUnitUtils.put;
import static net.andreinc.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static net.andreinc.mockneat.utils.ValidationUtils.SIZE_BIGGER_THAN_ZERO;
import static java.lang.String.format;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

@FunctionalInterface
@SuppressWarnings("unchecked")
public interface MockUnit<T> {

    // Functional Method
    Supplier<T> supplier();

    default T val() { return supplier().get(); }

    default <R> R val(Function<T, R> function) {
        notNull(function, INPUT_PARAMETER_NOT_NULL, "function");
        return function.apply(supplier().get());
    }

    //TODO document
    default void consume(Consumer<T> consumer) {
        notNull(consumer, INPUT_PARAMETER_NOT_NULL, "consumer");
        consumer.accept(val());
    }

    default String valStr() {
        return valStr("");
    }

    default String valStr(String valueIfNull) {
        Object val = supplier().get();
        if (null == val) {
            return valueIfNull;
        }
        return val.toString();
    }

    default <R> MockUnit<R> map(Function<T, R> function) {
        notNull(function, INPUT_PARAMETER_NOT_NULL, "function");
        Supplier<R> supp = () -> function.apply(supplier().get());
        return () -> supp;
    }

    default MockUnitInt mapToInt(Function<T, Integer> function) {
        notNull(function, INPUT_PARAMETER_NOT_NULL, "function");
        Supplier<Integer> supp = () -> function.apply(val());
        return () -> supp;
    }

    default MockUnitDouble mapToDouble(Function<T, Double> function) {
        notNull(function, INPUT_PARAMETER_NOT_NULL, "function");
        Supplier<Double> supp = () -> function.apply(val());
        return () -> supp;
    }

    default MockUnitLong mapToLong(Function<T, Long> function) {
        notNull(function, INPUT_PARAMETER_NOT_NULL, "function");
        Supplier<Long> supp = () -> function.apply(val());
        return () -> supp;
    }

    default MockUnitString mapToString(Function<T, String> function) {
        notNull(function, INPUT_PARAMETER_NOT_NULL, "function");
        Supplier<String> supp = () -> function.apply(val());
        return () -> supp;
    }

    default MockUnitString mapToString() {
        return () -> val()::toString;
    }

    default MockUnit<Stream<T>> stream() {
        Supplier<Stream<T>> supp = () -> Stream.generate(supplier());
        return () -> supp;
    }

    default MockUnit<List<T>> list(Class<? extends List> listClass, int size) {
        notNull(listClass, INPUT_PARAMETER_NOT_NULL, "listClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<List<T>> supp = () -> {
            try {
                List<T> result = listClass.newInstance();
                loop(size, () -> MockUnitUtils.add(listClass, result, supplier()));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate list '%s'.", listClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<List<T>> list(int size) {
        return list(ArrayList.class, size);
    }

    default MockUnit<Set<T>> set(Class<? extends Set> setClass, int size) {
        notNull(setClass, INPUT_PARAMETER_NOT_NULL, "setClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Set<T>> supp = () -> {
            try {
                Set<T> result = setClass.newInstance();
                loop(size, () -> MockUnitUtils.add(setClass, result, supplier()));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate set: '%s'.", setClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Set<T>> set(int size) {
        return set(HashSet.class, size);
    }

    default MockUnit<Collection<T>> collection(Class<? extends Collection> collectionClass, int size) {
        notNull(collectionClass, INPUT_PARAMETER_NOT_NULL, "collectionClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Collection<T>> supp = () -> {
            try {
                Collection<T> result = collectionClass.newInstance();
                loop(size, () -> MockUnitUtils.add(collectionClass, result, supplier()));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate collection: '%s'.", collectionClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Collection<T>> collection(int size) {
        return collection(ArrayList.class, size);
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, int size, Supplier<R> keysSupplier) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keysSupplier, INPUT_PARAMETER_NOT_NULL, "keysSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.newInstance();
                loop(size, () -> put(mapClass, result, keysSupplier, supplier()));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map: '%s'.", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<R, T>> mapKeys(int size, Supplier<R> keysSupplier) {
        return mapKeys(HashMap.class, size, keysSupplier);
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, Iterable<R> keys) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.newInstance();
                keys.forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map: '%s'.", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Iterable<R> keys) {
        return mapKeys(HashMap.class, keys);
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, R[] keys) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map: '%s'.", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<R, T>> mapKeys(R[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default MockUnit<Map<Integer, T>> mapKeys(Class<? extends Map> mapClass, int[] keys) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<Integer, T>> supp = () -> {
            try {
                Map<Integer, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map: '%s'", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<Integer, T>> mapKeys(int[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default MockUnit<Map<Long, T>> mapKeys(Class<? extends Map> mapClass, long[] keys) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<Long, T>> supp = () -> {
            try {
                Map<Long, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map '%s'.", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<Long, T>> mapKeys(long[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default MockUnit<Map<Double, T>> mapKeys(Class<? extends Map> mapClass, double[] keys) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<Double, T>> supp = () -> {
            try {
                Map<Double, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map '%s'.", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<Double, T>> mapKeys(double[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, int size, Supplier<R> valuesSupplier) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(valuesSupplier, INPUT_PARAMETER_NOT_NULL, "valuesSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.newInstance();
                loop(size, () -> put(mapClass, result, supplier(), valuesSupplier));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map: '%s'.", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<T, R>> mapVals(int size, Supplier<R> valuesSupplier) {
        return mapVals(HashMap.class, size, valuesSupplier);
    }

    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, Iterable<R> values) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.newInstance();
                values.forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map: '%s'", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<T, R>> mapVals(Iterable<R> values) {
        return mapVals(HashMap.class, values);
    }

    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, R[] values) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map: '%s'", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<T, R>> mapVals(R[] values) {
        return mapVals(HashMap.class, values);
    }

    default MockUnit<Map<T, Integer>> mapVals(Class<? extends Map> mapClass, int[] values) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, Integer>> supp = () -> {
            try {
                Map<T, Integer> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map '%s'.", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<T, Integer>> mapVals(int[] values) {
        return mapVals(HashMap.class, values);
    }

    default MockUnit<Map<T, Long>> mapVals(Class<? extends Map> mapClass, long[] values) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, Long>> supp = () -> {
            try {
                Map<T, Long> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map: '%s'.", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<T, Long>> mapVals(long[] values) {
        return mapVals(HashMap.class, values);
    }

    default MockUnit<Map<T, Double>> mapVals(Class<? extends Map> mapClass, double[] values) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, Double>> supp = () -> {
            try {
                Map<T, Double> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                String fmt = format("Cannot instantiate map: '%s'.", mapClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<T, Double>> mapVals(double[] values) {
        return mapVals(HashMap.class, values);
    }

    default MockUnit<T[]> array(Class<T> cls, int size) {
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        notNull(cls, INPUT_PARAMETER_NOT_NULL, "cls");
        Supplier<T[]> supp = () -> {
            T[] objs = (T[]) Array.newInstance(cls, size);
            range(0, size).forEach(i -> objs[i] = supplier().get());
            return objs;
        };
        return () -> supp;
    }
}
