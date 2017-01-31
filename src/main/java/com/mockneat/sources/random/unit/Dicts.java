package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.types.enums.DictType;

import java.util.Random;

/**
 * Created by andreinicolinciobanu on 16/01/2017.
 */
public class Dicts {

    private Rand rand;
    private Random random;

    public Dicts(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    public DictsFrom from(DictType type) {
        return new DictsFrom(rand, type);
    }

}
