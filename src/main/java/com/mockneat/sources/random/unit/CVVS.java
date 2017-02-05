package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.CVVType;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class CVVS implements RandUnitString {

    private Rand rand;

    public CVVS(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.cvvs().ofType(CVVType.CVV4).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

    public RandUnitString ofType(CVVType type) {
        return new CVVSOfType(rand, type);
    }
}
