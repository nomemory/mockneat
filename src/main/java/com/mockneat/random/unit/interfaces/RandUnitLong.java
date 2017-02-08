package com.mockneat.random.unit.interfaces;

import java.util.function.Supplier;
import java.util.stream.LongStream;

/**
 * Created by andreinicolinciobanu on 07/02/2017.
 */
public interface RandUnitLong extends RandUnit<Long> {
    default RandUnit<LongStream> longStream() {
        Supplier<LongStream> supp = () -> LongStream.generate(() -> supplier().get());
        return () -> supp;
    }
}
