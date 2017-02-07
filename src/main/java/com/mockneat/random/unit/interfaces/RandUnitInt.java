package com.mockneat.random.unit.interfaces;

import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Created by andreinicolinciobanu on 07/02/2017.
 */
public interface RandUnitInt extends RandUnitGeneric<Integer> {
    default RandUnit<IntStream> intStream() {
        Supplier<IntStream> supp = () -> IntStream.generate(() -> supplier().get());
        return () -> supp;
    }
}
