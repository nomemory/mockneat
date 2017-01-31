package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.CVVType;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class CVVS implements RandUnitGeneric<String> {

    private Rand rand;

    public CVVS(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.cvvs().ofType(CVVType.CVV4).val();
    }

    public CVVSOfType ofType(CVVType type) {
        return new CVVSOfType(rand, type);
    }
}
