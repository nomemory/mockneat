package com.mockneat.generator.units;

import java.util.function.Supplier;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class SupplierUnit<T> implements GeneratorUnit {

    private Supplier<T> gen;

    protected SupplierUnit(Supplier<T> gen) {
        this.gen = gen;
    }

    public static <T> SupplierUnit from(Supplier<T> gen) {
        return new SupplierUnit(gen);
    }

    @Override
    public Object next() {
        return this.gen.get();
    }
}
