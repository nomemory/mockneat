package com.mockneat.sources.random.unit.interfaces;

import java.util.stream.DoubleStream;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public interface RandUnitDouble extends RandUnitGeneral<Double> {
    default DoubleStream stream() {
        return DoubleStream.generate(this::val);
    }
}
