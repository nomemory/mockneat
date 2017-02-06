package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitGeneral;

import java.util.UUID;

/**
 * Created by andreinicolinciobanu on 31/01/2017.
 */
public class UUIDs implements RandUnitGeneral<String> {

    @Override
    public String val() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Rand getRand() {
        return null;
    }
}
