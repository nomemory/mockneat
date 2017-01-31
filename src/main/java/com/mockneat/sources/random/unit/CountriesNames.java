package com.mockneat.sources.random.unit;


import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.StringFormatType;

import static com.mockneat.types.enums.DictType.COUNTRY_NAME;

public class CountriesNames implements RandUnitGeneric<String> {

    private Rand rand;

    protected CountriesNames(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.dicts().from(COUNTRY_NAME).val();
    }

    public RandUnitFormatStringImpl format(StringFormatType formatType) {

        return new RandUnitFormatStringImpl(rand, formatType, this.val());
    }
}
