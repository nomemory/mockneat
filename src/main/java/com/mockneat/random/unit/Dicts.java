package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.DictType;

import java.util.Random;

/**
 * Created by andreinicolinciobanu on 16/01/2017.
 */
public class Dicts {

    private Rand rand;

    public Dicts(Rand rand) {
        this.rand = rand;
    }

    public RandUnitString from(DictType type) {
        return new DictsFrom(rand, type);
    }

}
