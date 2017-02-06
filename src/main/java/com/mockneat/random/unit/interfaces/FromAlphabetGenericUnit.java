package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;

import java.util.List;

@FunctionalInterface
public interface FromAlphabetGenericUnit {

    Rand getRand();

    default <T> RandUnitFromImpl<T> from(T[] alphabet) {
        return new RandUnitFromImpl<>(getRand(), alphabet);
    }

    default <T> RandUnitFromImpl<T> from(List<T> alphabet) {
        return new RandUnitFromImpl<>(getRand(), alphabet);
    }
}
