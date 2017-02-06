package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;

import java.util.*;
import java.util.function.Supplier;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public interface RandUnitGeneral<T> {

    T val();

    Rand getRand();

    default Supplier<T> supplier(){ return this::val; }

    default RandUnit<Collection<T>> collection(Class<? extends Collection> collectionClass, Integer size) {
        return new RandUnitCollectionImpl<>(getRand(), this::val, collectionClass, size);
    }

    default RandUnit<Collection<T>> collection(Integer size) {
        return new RandUnitCollectionImpl<>(getRand(), this::val, ArrayList.class, size);
    }

    default RandUnit<List<T>> list(Integer size) {
        return new RandUnitListImpl<>(getRand(), this::val, ArrayList.class, size);
    }

    default RandUnit<List<T>> list(Class<? extends List> listClass, Integer size) {
        return new RandUnitListImpl<>(getRand(), this::val, listClass, size);
    }

    default RandUnit<Set<T>> set(Class<? extends Set> setClass, Integer size) {
        return new RandUnitSetImpl<>(getRand(), this::val, setClass, size);
    }

    default RandUnit<Set<T>> set(Integer size) {
        return new RandUnitSetImpl<>(getRand(), this::val, HashSet.class, size);
    }

    default <R> RandUnit<Map<R,T>> mapWithKeys(Class<? extends Map> mapClass, Integer size, Supplier<R> keysGen) {
        return new RandUnitMapWithKeysImpl<>(getRand(), this::val, keysGen, mapClass, size);
    }

    default <R> RandUnit<Map<R,T>> mapWithKeys(Integer size, Supplier<R> keyGen) {
        return new RandUnitMapWithKeysImpl<>(getRand(), this::val, keyGen, HashMap.class, size);
    }

    default <R> RandUnit<Map<T,R>> mapWithValues(Class<? extends Map> mapClass, Integer size, Supplier<R> valGen) {
        return new RandUnitMapWithValuesImpl<>(getRand(), this::val, valGen, mapClass, size);
    }

    default <R> RandUnit<Map<T,R>> mapWithValues(Integer size, Supplier<R> valGen) {
        return new RandUnitMapWithValuesImpl<>(getRand(), this::val, valGen, HashMap.class, size);
    }
}
