package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;

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
