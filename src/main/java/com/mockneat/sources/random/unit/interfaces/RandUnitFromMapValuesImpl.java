package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andreinicolinciobanu on 02/02/2017.
 */
public class RandUnitFromMapValuesImpl<T, R> implements RandUnit<R> {

    private Rand rand;
    private List<R> valArray;

    public RandUnitFromMapValuesImpl(Rand rand, Map<T, R> map) {
        this.rand = rand;
        this.valArray = new ArrayList<>();
        valArray.addAll(map.values());
    }

    @Override
    public R val() {
        return rand.objs().from(valArray).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
