package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public interface FromAlphabetIntUnit {
    Rand getRand();

    default RandUnitIntFromImpl from(Integer[] alphabet) {
        return new RandUnitIntFromImpl(getRand(), alphabet);
    }

    default RandUnitIntFromImpl from(List<Integer> alphabet) {
        return new RandUnitIntFromImpl(getRand(), alphabet);
    }
}
