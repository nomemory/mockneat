package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;

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
