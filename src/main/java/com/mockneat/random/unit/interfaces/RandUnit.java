package com.mockneat.random.unit.interfaces;

import java.util.function.Supplier;
import java.util.stream.Stream;

public interface RandUnit<T> extends RandUnitGeneric<T> {
    default RandUnit<Stream<T>> stream() {
        Supplier<Stream<T>> supp = () -> Stream.generate(supplier());
        return () -> supp;
    }
}
