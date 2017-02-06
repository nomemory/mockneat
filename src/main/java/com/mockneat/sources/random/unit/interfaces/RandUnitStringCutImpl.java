package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.function.Supplier;

/**
 * Created by andreinicolinciobanu on 05/02/2017.
 */
public class RandUnitStringCutImpl implements RandUnitString {

    private Rand rand;
    private Integer maxSize;
    private Supplier<String> val;

    public RandUnitStringCutImpl(Rand rand, Integer maxSize, Supplier<String> val) {
        this.rand = rand;
        this.maxSize = maxSize;
        this.val = val;
    }

    @Override
    public String val() {
        return val.get().substring(0, maxSize);
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
