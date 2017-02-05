package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.DictType;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.types.enums.StringFormatType;

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
