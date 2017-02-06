package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.DictType;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class CountriesISO2 implements RandUnitString {

    private Rand rand;

    public CountriesISO2(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.dicts().from(DictType.COUNTRY_ISO_CODE_2).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

}
