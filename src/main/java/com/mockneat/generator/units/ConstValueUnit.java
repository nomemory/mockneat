package com.mockneat.generator.units;

import com.mockneat.types.Value;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class ConstValueUnit implements MockGeneratorUnit {

    private Value value;

    public static ConstValueUnit from(Value value) {
        return new ConstValueUnit(value);
    }

    protected ConstValueUnit(Value value) {
        this.value = value;
    }

    @Override
    public Object next() {
        return value.get();
    }

}
