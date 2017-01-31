package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public interface FromAlphabetLongUnit extends RandUnitLong {

    Rand getRand();

    default RandUnitLongFromImpl from(Long[] alphabet) {
        return new RandUnitLongFromImpl(getRand(), alphabet);
    }

    default RandUnitLongFromImpl from(List<Long> alphabet) {
        return new RandUnitLongFromImpl(getRand(), alphabet);
    }
}
