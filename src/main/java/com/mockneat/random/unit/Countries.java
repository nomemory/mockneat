package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;

import static com.mockneat.types.enums.DictType.COUNTRY_ISO_CODE_2;
import static com.mockneat.types.enums.DictType.COUNTRY_NAME;

/**
 * Created by andreinicolinciobanu on 16/01/2017.
 */
public class Countries {

    private Rand rand;

    public Countries(Rand rand) {
        this.rand = rand;
    }

    public RandUnitString names() {
        return rand.dicts().type(COUNTRY_NAME);
    }

    public RandUnitString iso2() {
        return rand.dicts().type(COUNTRY_ISO_CODE_2);
    }
}
