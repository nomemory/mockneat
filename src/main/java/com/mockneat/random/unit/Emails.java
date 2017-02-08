package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;

import java.util.function.Supplier;

import static com.mockneat.types.enums.DictType.DOMAIN_EMAIL;

/**
 * Created by andreinicolinciobanu on 30/01/2017.
 */
public class Emails implements RandUnitString {

    private Rand rand;

    public Emails(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return () -> rand.users().val() + "@" + rand.dicts().type(DOMAIN_EMAIL).val();
    }
}
