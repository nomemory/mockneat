package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.StringFormatType;

/**
 * Created by andreinicolinciobanu on 30/01/2017.
 */
public class Emails implements RandUnitString {

    private Rand rand;

    public Emails(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.users().val() + "@" + rand.dicts().from(DictType.DOMAIN_EMAIL).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
