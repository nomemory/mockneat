package net.andreinc.mockneat.abstraction;

/**
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

import org.apache.commons.lang3.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.generate;
import static net.andreinc.aleph.AlephFormatter.template;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static net.andreinc.mockneat.utils.MockUnitUtils.*;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

@FunctionalInterface
@SuppressWarnings("unchecked")
public interface MockUnit<T> {

    // Functional Method
    Supplier<T> supplier();

    default T val() { return supplier().get(); }

    default void serialize(String strPath) {
        T object = supplier().get();

        isTrue(object instanceof Serializable, OBJECT_NOT_SERIALIZABLE);

        Serializable sObj = (Serializable) object;
        try { SerializationUtils.serialize(sObj, new FileOutputStream(strPath)); }
        catch (FileNotFoundException e) { throw new UncheckedIOException(e); }
    }

    default <R> R val(Function<T, R> function) {
        notNull(function, "function");
        return function.apply(supplier().get());
    }

    default void consume(Consumer<T> consumer) {
        notNull(consumer, "consumer");
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
        notNull(function, "function");
        Supplier<R> supp = () -> function.apply(supplier().get());
        return () -> supp;
    }

    default MockUnitInt mapToInt(Function<T, Integer> function) {
        notNull(function, "function");
        Supplier<Integer> supp = () -> function.apply(val());
        return () -> supp;
    }

    default MockUnitDouble mapToDouble(Function<T, Double> function) {
        notNull(function, "function");
        Supplier<Double> supp = () -> function.apply(val());
        return () -> supp;
    }

    default MockUnitLong mapToLong(Function<T, Long> function) {
        notNull(function, "function");
        Supplier<Long> supp = () -> function.apply(val());
        return () -> supp;
    }

    default MockUnitString mapToString(Function<T, String> function) {
        notNull(function, "function");
        Supplier<String> supp = () -> function.apply(val());
        return () -> supp;
    }

    default MockUnitString mapToString() {
        return () -> ifSupplierNotNullDo(supplier(), s -> s.toString());
    }

    default MockUnit<Stream<T>> stream() {
        Supplier<Stream<T>> supp = () -> generate(supplier());
        return () -> supp;
    }

    default MockUnit<List<T>> list(Class<? extends List> listClass, int size) {
        notNull(listClass, "listClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<List<T>> supp = () -> {
            try {
                List<T> result = listClass.getDeclaredConstructor().newInstance();
                loop(size, () -> add(listClass, result, supplier()));
                return result;
            } catch (Exception e) {
                String fmt = template("Cannot instantiate list '{l.Name}'.")
                                .arg("l", listClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<List<T>> list(int size) {
        return list(ArrayList.class, size);
    }

    default MockUnit<Set<T>> set(Class<? extends Set> setClass, int size) {
        notNull(setClass, "setClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Set<T>> supp = () -> {
            try {
                Set<T> result = setClass.getDeclaredConstructor().newInstance();
                loop(size, () -> add(setClass, result, supplier()));
                return result;
            } catch (Exception e) {
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
        notNull(collectionClass, "collectionClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Collection<T>> supp = () -> {
            try {
                Collection<T> result = collectionClass.getDeclaredConstructor().newInstance();
                loop(size, () -> add(collectionClass, result, supplier()));
                return result;
            } catch (Exception e) {
                String fmt = template("Cannot instantiate collection: '#{c.name}'.")
                                .arg("c", collectionClass.getName())
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Collection<T>> collection(int size) {
        return collection(ArrayList.class, size);
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, int size, Supplier<R> keysSupplier) {
        notNull(mapClass, "mapClass");
        notNull(keysSupplier, "keysSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.getDeclaredConstructor().newInstance();
                loop(size, () -> put(mapClass, result, keysSupplier, supplier()));
                return result;
            } catch (Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<R, T>> mapKeys(int size, Supplier<R> keysSupplier) {
        return mapKeys(HashMap.class, size, keysSupplier);
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, Iterable<R> keys) {
        notNull(mapClass, "mapClass");
        notNull(keys, "keys");
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.getDeclaredConstructor().newInstance();
                keys.forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            } catch (Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Iterable<R> keys) {
        return mapKeys(HashMap.class, keys);
    }

    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, R[] keys) {
        notNull(mapClass, "mapClass");
        notNull(keys, "keys");
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<R, T>> mapKeys(R[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default MockUnit<Map<Integer, T>> mapKeys(Class<? extends Map> mapClass, int[] keys) {
        notNull(mapClass, "mapClass");
        notNull(keys, "keys");
        Supplier<Map<Integer, T>> supp = () -> {
            try {
                Map<Integer, T> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<Integer, T>> mapKeys(int[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default MockUnit<Map<Long, T>> mapKeys(Class<? extends Map> mapClass, long[] keys) {
        notNull(mapClass, "mapClass");
        notNull(keys, "keys");
        Supplier<Map<Long, T>> supp = () -> {
            try {
                Map<Long, T> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<Long, T>> mapKeys(long[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default MockUnit<Map<Double, T>> mapKeys(Class<? extends Map> mapClass, double[] keys) {
        notNull(mapClass, "mapClass");
        notNull(keys, "keys");
        Supplier<Map<Double, T>> supp = () -> {
            try {
                Map<Double, T> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<Double, T>> mapKeys(double[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, int size, Supplier<R> valuesSupplier) {
        notNull(mapClass, "mapClass");
        notNull(valuesSupplier, "valuesSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.getDeclaredConstructor().newInstance();
                loop(size, () -> put(mapClass, result, supplier(), valuesSupplier));
                return result;
            } catch (Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<T, R>> mapVals(int size, Supplier<R> valuesSupplier) {
        return mapVals(HashMap.class, size, valuesSupplier);
    }

    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, Iterable<R> values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.getDeclaredConstructor().newInstance();
                values.forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch (Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }

        };
        return () -> supp;
    }

    default <R> MockUnit<Map<T, R>> mapVals(Iterable<R> values) {
        return mapVals(HashMap.class, values);
    }

    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, R[] values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default <R> MockUnit<Map<T, R>> mapVals(R[] values) {
        return mapVals(HashMap.class, values);
    }

    default MockUnit<Map<T, Integer>> mapVals(Class<? extends Map> mapClass, int[] values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, Integer>> supp = () -> {
            try {
                Map<T, Integer> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<T, Integer>> mapVals(int[] values) {
        return mapVals(HashMap.class, values);
    }

    default MockUnit<Map<T, Long>> mapVals(Class<? extends Map> mapClass, long[] values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, Long>> supp = () -> {
            try {
                Map<T, Long> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<T, Long>> mapVals(long[] values) {
        return mapVals(HashMap.class, values);
    }

    default MockUnit<Map<T, Double>> mapVals(Class<? extends Map> mapClass, double[] values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, Double>> supp = () -> {
            try {
                Map<T, Double> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    default MockUnit<Map<T, Double>> mapVals(double[] values) {
        return mapVals(HashMap.class, values);
    }

    default MockUnit<T[]> array(Class<T> cls, int size) {
        notNull(cls, "cls");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<T[]> supp = () -> {
            T[] objs = (T[]) Array.newInstance(cls, size);
            range(0, size).forEach(i -> objs[i] = supplier().get());
            return objs;
        };
        return () -> supp;
    }
}
