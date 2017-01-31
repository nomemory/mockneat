package com.mockneat.sources.random.unit.interfaces;

import java.util.stream.Stream;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public interface RandUnitGeneric<T> extends RandUnit<T> {

    default Stream<T> stream() {
        return Stream.generate(this::val);
    }
}
