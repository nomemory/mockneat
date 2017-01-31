package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.List;

public interface FromAlphabetGenericUnit<T> {

    Rand getRand();

    default <T> RandUnitGenericFromImpl<T> from(T[] alphabet) {
        return new RandUnitGenericFromImpl<T>(getRand(), alphabet);
    }

    default <T> RandUnitGenericFromImpl<T> from(List<T> alphabet) {
        return new RandUnitGenericFromImpl<T>(getRand(), alphabet);
    }
}
