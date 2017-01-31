package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGenericFromImpl;
import com.mockneat.utils.NextUtils;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 26/01/2017.
 */
public class Objs {

    private Rand rand;

    public Objs(Rand rand){
        this.rand = rand;
    }

    public <T> RandUnitGenericFromImpl<T> from(List<T> alphabet) {
        return new RandUnitGenericFromImpl<>(rand, alphabet);
    }

    public <T> RandUnitGenericFromImpl<T> from(T[] alphabet) {
        return new RandUnitGenericFromImpl<>(rand, alphabet);
    }

    public <T extends Enum<?>> RandUnitGenericFromImpl<T> from(Class<T> enumClass) {
        NextUtils.checkType(enumClass);
        T[] arr = enumClass.getEnumConstants();
        return new RandUnitGenericFromImpl<T>(rand, arr);
    }
}
