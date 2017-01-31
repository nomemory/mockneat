package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public interface FromAlphabetDoubleUnit {
    Rand getRand();

    default RandUnitDoubleFromImpl from(Double[] alphabet) {
        return new RandUnitDoubleFromImpl(getRand(), alphabet);
    }

    default RandUnitDoubleFromImpl from(List<Double> alphabet) {
        return new RandUnitDoubleFromImpl(getRand(), alphabet);
    }
}
