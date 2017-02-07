package com.mockneat.random.unit.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

@FunctionalInterface
public interface RandUnitGeneric<T> {

    Logger logger = LoggerFactory.getLogger(RandUnitGeneric.class);

    // Functional Method
    Supplier<T> supplier();

    default T val() { return supplier().get(); }

    default T val(Function<T, T> funct) { return funct.apply(supplier().get()); }

    default RandUnit<Stream<T>> stream() {
        Supplier<Stream<T>> supp = () -> Stream.generate(supplier());
        return () -> supp;
    }

    default RandUnitGeneric<List<T>> list(Class<? extends List> listClass, Integer size) {
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

    default RandUnitGeneric<List<T>> list(Integer size) {
        return list(ArrayList.class, size);
    }

    default RandUnitGeneric<Set<T>> set(Class<? extends Set> setClass, Integer size) {
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

    default RandUnitGeneric<Set<T>> set(Integer size) {
        return set(HashSet.class, size);
    }

    default RandUnitGeneric<Collection<T>> collection(Class<? extends Collection> collectionClass, Integer size) {
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

    default RandUnitGeneric<Collection<T>> collection(Integer size) {
        return collection(ArrayList.class, size);
    }

    default <R> RandUnitGeneric<Map<R, T>> mapWithKeys(Class<? extends Map> mapClass, Integer size, Supplier<R> keysSupplier) {
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

    default <R> RandUnitGeneric<Map<R, T>> mapWithKeys(Integer size, Supplier<R> keysSupplier) {
        return mapWithKeys(HashMap.class, size,keysSupplier);
    }

    default <R> RandUnitGeneric<Map<T, R>> mapWithValues(Class<? extends Map> mapClass, Integer size, Supplier<R> valuesSupplier) {
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

    default <R> RandUnitGeneric<Map<T, R>> mapWithValues(Integer size, Supplier<R> valuesSupplier) {
        return mapWithValues(HashMap.class, size, valuesSupplier);
    }
}
