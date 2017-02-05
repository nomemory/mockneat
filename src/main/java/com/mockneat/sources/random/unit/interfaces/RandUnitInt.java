package com.mockneat.sources.random.unit.interfaces;

import java.util.stream.IntStream;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public interface RandUnitInt extends RandUnitGeneral<Integer> {
    default IntStream stream() {
        return IntStream.generate(this::val);
    }
}
