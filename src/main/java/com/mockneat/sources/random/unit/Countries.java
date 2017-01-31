package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;

/**
 * Created by andreinicolinciobanu on 16/01/2017.
 */
public class Countries {

    private Rand rand;

    public Countries(Rand rand) {
        this.rand = rand;
    }

    public CountriesNames names() {
        return new CountriesNames(rand);
    }

    public CountriesISO2 iso2() {
        return new CountriesISO2(rand);
    }
}
