package com.mockneat.random.unit.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

@FunctionalInterface
public interface RandUnit<T> {

    Logger logger = LoggerFactory.getLogger(RandUnit.class);

    // Functional Method
    Supplier<T> supplier();

    default T val() { return supplier().get(); }

    default <R> R val(Function<T, R> funct) { return funct.apply(supplier().get()); }

    default String strVal() { return supplier().get().toString(); }

    default RandUnitString str() { return () -> () -> supplier().get().toString(); }

    default RandUnit<Stream<T>> stream() {
        Supplier<Stream<T>> supp = () -> Stream.generate(supplier());
        return () -> supp;
    }

    default RandUnit<List<T>> list(Class<? extends List> listClass, Integer size) {
        // TODO validate size
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

    default RandUnit<List<T>> list(Integer size) {
        return list(ArrayList.class, size);
    }

    default RandUnit<Set<T>> set(Class<? extends Set> setClass, Integer size) {
        // TODO validate size
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

    default RandUnit<Set<T>> set(Integer size) {
        return set(HashSet.class, size);
    }

    default RandUnit<Collection<T>> collection(Class<? extends Collection> collectionClass, Integer size) {
        // TODO validate size
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

    default RandUnit<Collection<T>> collection(Integer size) {
        return collection(ArrayList.class, size);
    }

    default <R> RandUnit<Map<R, T>> mapWithKeys(Class<? extends Map> mapClass, Integer size, Supplier<R> keysSupplier) {
        // TODO validation
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

    default <R> RandUnit<Map<R, T>> mapWithKeys(Integer size, Supplier<R> keysSupplier) {
        return mapWithKeys(HashMap.class, size, keysSupplier);
    }

    default <R> RandUnit<Map<R, T>> mapWithKeys(Class<? extends Map> mapClass, Iterable<R> keys) {
        //TODO validation
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

    default <R> RandUnit<Map<T, R>> mapWithValues(Class<? extends Map> mapClass, Integer size, Supplier<R> valuesSupplier) {
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

    default <R> RandUnit<Map<T, R>> mapWithValues(Integer size, Supplier<R> valuesSupplier) {
        return mapWithValues(HashMap.class, size, valuesSupplier);
    }

    default <R> RandUnit<Map<T, R>> mapWithValues(Class<? extends Map> mapClass, Iterable<R> values) {
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
