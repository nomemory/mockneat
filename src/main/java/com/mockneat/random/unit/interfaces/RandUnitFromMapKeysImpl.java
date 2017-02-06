package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andreinicolinciobanu on 02/02/2017.
 */
public class RandUnitFromMapKeysImpl<T, R> implements RandUnit<T> {

    private Rand rand;
    private List<T> keyArray;

    public RandUnitFromMapKeysImpl(Rand rand, Map<T,R> map) {
        this.rand = rand;
        this.keyArray = new ArrayList<>();
        keyArray.addAll(map.keySet());
    }

    @Override
    public T val() {
        return rand.objs().from(keyArray).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
