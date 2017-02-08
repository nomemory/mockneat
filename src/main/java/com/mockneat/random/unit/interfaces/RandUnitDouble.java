package com.mockneat.random.unit.interfaces;

import java.util.function.Supplier;
import java.util.stream.DoubleStream;

/**
 * Created by andreinicolinciobanu on 07/02/2017.
 */
public interface RandUnitDouble extends RandUnit<Double> {
    default RandUnit<DoubleStream> doubleStream() {
        Supplier<DoubleStream> supp = () -> DoubleStream.generate(() -> supplier().get());
        return () -> supp;
    }
}
