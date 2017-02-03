package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;

import java.util.UUID;

/**
 * Created by andreinicolinciobanu on 31/01/2017.
 */
public class UUIDs implements RandUnit<String> {

    @Override
    public String val() {
        return UUID.randomUUID().toString();
    }
}
