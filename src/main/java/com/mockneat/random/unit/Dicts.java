package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.random.utils.DictsUtils;
import com.mockneat.types.enums.DictType;

import java.util.function.Supplier;

/**
 * Created by andreinicolinciobanu on 16/01/2017.
 */
public class Dicts {

    private Rand rand;
    private DictsUtils utils = DictsUtils.getInstance();

    public Dicts(Rand rand) {
        this.rand = rand;
    }

    public RandUnitString type(DictType type) {
        // TODO validate type
        return () -> rand.objs().from(utils.get(type)).supplier();
    }
}
