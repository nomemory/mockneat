package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.DictType;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.types.enums.StringFormatType;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class CountriesISO2 implements RandUnitGeneric<String> {

    private Rand rand;

    public CountriesISO2(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.dicts().from(DictType.COUNTRY_ISO_CODE_2).val();
    }

    public RandUnitFormatStringImpl format(StringFormatType formatType) {
        return new RandUnitFormatStringImpl(rand, formatType, val());
    }
}
