package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.List;

public interface FromAlphabetGenericUnit<T> {

    Rand getRand();

    default <T> RandUnitFromImpl<T> from(T[] alphabet) {
        return new RandUnitFromImpl<T>(getRand(), alphabet);
    }

    default <T> RandUnitFromImpl<T> from(List<T> alphabet) {
        return new RandUnitFromImpl<T>(getRand(), alphabet);
    }
}
