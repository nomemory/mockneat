package com.mockneat.generator.units;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class SupplierUnit<T> implements MockGeneratorUnit {

    private Supplier<T> gen;

    public static <T> SupplierUnit from(Supplier<T> gen) {
        return new SupplierUnit(gen);
    }

    protected SupplierUnit(Supplier<T> gen) {
        this.gen = gen;
    }

    @Override
    public Object next() {
        return this.gen.get();
    }
}
