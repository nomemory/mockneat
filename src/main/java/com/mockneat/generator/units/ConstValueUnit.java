package com.mockneat.generator.units;

import com.mockneat.types.Value;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class ConstValueUnit implements GeneratorUnit {

    private Value value;

    protected ConstValueUnit(Value value) {
        this.value = value;
    }

    public static ConstValueUnit from(Value value) {
        return new ConstValueUnit(value);
    }

    @Override
    public Object next() {
        return value.get();
    }

}
