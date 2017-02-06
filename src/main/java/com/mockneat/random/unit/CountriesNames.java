package com.mockneat.random.unit;


import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;

import static com.mockneat.types.enums.DictType.COUNTRY_NAME;

public class CountriesNames implements RandUnitString {

    private Rand rand;

    protected CountriesNames(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.dicts().from(COUNTRY_NAME).val();
    }

    @Override
    public Rand getRand() {
        return null;
    }
}
