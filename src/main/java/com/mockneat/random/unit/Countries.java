package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;

/**
 * Created by andreinicolinciobanu on 16/01/2017.
 */
public class Countries {

    private Rand rand;

    public Countries(Rand rand) {
        this.rand = rand;
    }

    public RandUnitString names() {
        return new CountriesNames(rand);
    }

    public RandUnitString iso2() {
        return new CountriesISO2(rand);
    }
}
