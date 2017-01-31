package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.CreditCardType;

import static com.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class CCS implements RandUnitGeneric<String> {

    private Rand rand;

    public CCS(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.ccs().ofType(AMERICAN_EXPRESS).val();
    }

    public CCSOfType ofType(CreditCardType creditCardType) {
        return new CCSOfType(rand, creditCardType);
    }
}
