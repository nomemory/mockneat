package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;

/**
 * Created by andreinicolinciobanu on 31/01/2017.
 */
public class Texts implements RandUnitString {

    private Rand rand;

    public Texts(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return "";
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
