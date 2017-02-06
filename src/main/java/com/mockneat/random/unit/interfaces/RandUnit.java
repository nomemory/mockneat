package com.mockneat.random.unit.interfaces;

import java.util.stream.Stream;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public interface RandUnit<T> extends RandUnitGeneral<T> {

    default Stream<T> stream() {
        return Stream.generate(this::val);
    }
}
