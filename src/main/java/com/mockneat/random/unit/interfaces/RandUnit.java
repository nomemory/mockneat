package com.mockneat.random.unit.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.utils.ValidationUtils.SIZE_BIGGER_THAN_ZERO;
import static com.mockneat.utils.ValidationUtils.SIZE_BIGGER_THAN_ZERO_STRICT;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

@FunctionalInterface
public interface RandUnit<T> {

    Logger logger = LoggerFactory.getLogger(RandUnit.class);

    // Functional Method
    Supplier<T> supplier();

    default T val() { return supplier().get(); }

    default <R> R val(Function<T, R> funct) { return funct.apply(supplier().get()); }

    default String valStr() { return supplier().get().toString(); }

    default RandUnitString str() { return () -> supplier().get()::toString; }

    default RandUnit<Stream<T>> stream() {
        Supplier<Stream<T>> supp = () -> Stream.generate(supplier());
        return () -> supp;
    }

    default RandUnit<List<T>> list(Class<? extends List> listClass, int size) {
        notNull(listClass, INPUT_PARAMETER_NOT_NULL, "listClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<List<T>> supp = () -> {
            try {
                List<T> result = listClass.newInstance();
                int cnt = size;
                while(cnt-->0)
                    result.add(supplier().get());
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate list.", e);
            }
            return new ArrayList<>();
        };
        return () -> supp;
    }

    default RandUnit<List<T>> list(int size) {
        return list(ArrayList.class, size);
    }

    default RandUnit<Set<T>> set(Class<? extends Set> setClass, int size) {
        notNull(setClass, INPUT_PARAMETER_NOT_NULL, "setClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Set<T>> supp = () -> {
            try {
                Set<T> result = setClass.newInstance();
                int cnt = size;
                while(cnt-->0) {
                    result.add(supplier().get());
                }
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate set.", e);
            }
            return new HashSet<>();
        };
        return () -> supp;
    }

    default RandUnit<Set<T>> set(int size) {
        return set(HashSet.class, size);
    }

    default RandUnit<Collection<T>> collection(Class<? extends Collection> collectionClass, int size) {
        notNull(collectionClass, INPUT_PARAMETER_NOT_NULL, "collectionClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Collection<T>> supp = () -> {
            try {
                Collection<T> result = collectionClass.newInstance();
                int cnt = size;
                while(cnt-->0)
                    result.add(supplier().get());
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate collection.", e);
            }
            return new ArrayList<>();
        };
        return () -> supp;
    }

    default RandUnit<Collection<T>> collection(int size) {
        return collection(ArrayList.class, size);
    }

    default <R> RandUnit<Map<R, T>> mapWithKeys(Class<? extends Map> mapClass, int size, Supplier<R> keysSupplier) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keysSupplier, INPUT_PARAMETER_NOT_NULL, "keysSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.newInstance();
                int cnt = size;
                while(cnt-->0)
                    result.put(keysSupplier.get(), supplier().get());
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> RandUnit<Map<R, T>> mapWithKeys(int size, Supplier<R> keysSupplier) {
        return mapWithKeys(HashMap.class, size, keysSupplier);
    }

    default <R> RandUnit<Map<R, T>> mapWithKeys(Class<? extends Map> mapClass, Iterable<R> keys) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.newInstance();
                keys.forEach(key -> result.put(key, supplier().get()));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> RandUnit<Map<R, T>> mapWithKeys(Iterable<R> keys) {
        return mapWithKeys(HashMap.class, keys);
    }

    default <R> RandUnit<Map<R, T>> mapWithKeys(Class<? extends Map> mapClass, R[] keys) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key ->result.put(key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> RandUnit<Map<R, T>> mapWithKeys(R[] keys) {
        return mapWithKeys(HashMap.class, keys);
    }

    default RandUnit<Map<Integer, T>> mapWithKeys(Class<? extends Map> mapClass, int[] keys) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<Integer, T>> supp = () -> {
            try {
                Map<Integer, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key ->result.put(key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default RandUnit<Map<Integer, T>> mapWithKeys(int[] keys) {
        return mapWithKeys(HashMap.class, keys);
    }

    default RandUnit<Map<Long, T>> mapWithKeys(Class<? extends Map> mapClass, long[] keys) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<Long, T>> supp = () -> {
            try {
                Map<Long, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key ->result.put(key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default RandUnit<Map<Long, T>> mapWithKeys(long[] keys) {
        return mapWithKeys(HashMap.class, keys);
    }

    default RandUnit<Map<Double, T>> mapWithKeys(Class<? extends Map> mapClass, double[] keys) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(keys, INPUT_PARAMETER_NOT_NULL, "keys");
        Supplier<Map<Double, T>> supp = () -> {
            try {
                Map<Double, T> result = mapClass.newInstance();
                Arrays.stream(keys).forEach(key ->result.put(key, supplier().get()));
                return result;
            }
            catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default RandUnit<Map<Double, T>> mapWithKeys(double[] keys) {
        return mapWithKeys(HashMap.class, keys);
    }

    default <R> RandUnit<Map<T, R>> mapWithValues(Class<? extends Map> mapClass, int size, Supplier<R> valuesSupplier) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(valuesSupplier, INPUT_PARAMETER_NOT_NULL, "valuesSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.newInstance();
                int cnt = size;
                while (cnt-- > 0) {
                    result.put(supplier().get(), valuesSupplier.get());
                }
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> RandUnit<Map<T, R>> mapWithValues(int size, Supplier<R> valuesSupplier) {
        return mapWithValues(HashMap.class, size, valuesSupplier);
    }

    default <R> RandUnit<Map<T, R>> mapWithValues(Class<? extends Map> mapClass, Iterable<R> values) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.newInstance();
                values.forEach(value -> result.put(supplier().get(), value));
                return result;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> RandUnit<Map<T, R>> mapWithValues(Iterable<R> values) {
        return mapWithValues(HashMap.class, values);
    }

    default <R> RandUnit<Map<T, R>> mapWithValues(Class<? extends Map> mapClass, R[] values) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> result.put(supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default <R> RandUnit<Map<T, R>> mapWithValues(R[] values) {
        return mapWithValues(HashMap.class, values);
    }

    default RandUnit<Map<T, Integer>> mapWithValues(Class<? extends Map> mapClass, int[] values) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, Integer>> supp = () -> {
            try {
                Map<T, Integer> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> result.put(supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default RandUnit<Map<T, Integer>> mapWithValues(int[] values) {
        return mapWithValues(HashMap.class, values);
    }

    default RandUnit<Map<T, Long>> mapWithValues(Class<? extends Map> mapClass, long[] values) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, Long>> supp = () -> {
            try {
                Map<T, Long> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> result.put(supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default RandUnit<Map<T, Long>> mapWithValues(long[] values) {
        return mapWithValues(HashMap.class, values);
    }

    default RandUnit<Map<T, Double>> mapWithValues(Class<? extends Map> mapClass, double[] values) {
        notNull(mapClass, INPUT_PARAMETER_NOT_NULL, "mapClass");
        notNull(values, INPUT_PARAMETER_NOT_NULL, "values");
        Supplier<Map<T, Double>> supp = () -> {
            try {
                Map<T, Double> result = mapClass.newInstance();
                Arrays.stream(values).forEach(value -> result.put(supplier().get(), value));
                return result;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("Cannot instantiate map.", e);
            }
            return new HashMap<>();
        };
        return () -> supp;
    }

    default RandUnit<Map<T, Double>> mapWithValues(double[] values) {
        return mapWithValues(HashMap.class, values);
    }
}
