package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andreinicolinciobanu on 02/02/2017.
 */
public class RandUnitGenericFromMapValuesImpl<T, R> implements RandUnitGeneric<R>  {

    private Rand rand;
    private List<R> valArray;

    public RandUnitGenericFromMapValuesImpl(Rand rand, Map<T, R> map) {
        this.rand = rand;
        this.valArray = new ArrayList<>();
        valArray.addAll(map.values());
    }

    @Override
    public R val() {
        return rand.objs().from(valArray).val();
    }
}
