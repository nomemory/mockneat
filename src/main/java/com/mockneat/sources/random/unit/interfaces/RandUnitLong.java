package com.mockneat.sources.random.unit.interfaces;

import java.util.stream.LongStream;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public interface RandUnitLong extends RandUnitGeneral<Long> {
    default LongStream stream() {
        return LongStream.generate(this::val);
    }
}
