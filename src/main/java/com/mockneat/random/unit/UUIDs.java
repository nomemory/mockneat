package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * Created by andreinicolinciobanu on 31/01/2017.
 */
public class UUIDs implements RandUnitString {

    @Override
    public Supplier<String> supplier() {
       return () -> UUID.randomUUID().toString();
    }

}
