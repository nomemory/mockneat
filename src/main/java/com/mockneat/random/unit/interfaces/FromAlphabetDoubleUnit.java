package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;

import java.util.List;

@FunctionalInterface
public interface FromAlphabetDoubleUnit {
    Rand getRand();

    default RandUnitDoubleFromImpl from(Double[] alphabet) {
        return new RandUnitDoubleFromImpl(getRand(), alphabet);
    }

    default RandUnitDoubleFromImpl from(List<Double> alphabet) {
        return new RandUnitDoubleFromImpl(getRand(), alphabet);
    }
}
