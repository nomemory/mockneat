package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;

import java.util.List;

@FunctionalInterface
public interface FromAlphabetIntUnit {
    Rand getRand();

    default RandUnitIntFromImpl from(Integer[] alphabet) {
        return new RandUnitIntFromImpl(getRand(), alphabet);
    }

    default RandUnitIntFromImpl from(List<Integer> alphabet) {
        return new RandUnitIntFromImpl(getRand(), alphabet);
    }
}
