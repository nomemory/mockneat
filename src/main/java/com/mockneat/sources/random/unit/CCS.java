package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.CreditCardType;

import static com.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class CCS implements RandUnitString {

    private Rand rand;

    public CCS(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.ccs().ofType(AMERICAN_EXPRESS).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

    public RandUnitString ofType(CreditCardType creditCardType) {
        return new CCSOfTypes(rand, creditCardType);
    }

    public RandUnitString ofTypes(CreditCardType... creditCardTypes) { return new CCSOfTypes(rand, creditCardTypes); }
}
