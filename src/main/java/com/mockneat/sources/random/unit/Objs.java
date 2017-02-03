package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGenericFromImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnitGenericFromMapKeysImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnitGenericFromMapValuesImpl;
import com.mockneat.utils.NextUtils;

import java.util.List;
import java.util.Map;

import static com.mockneat.utils.NextUtils.checkType;

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
        checkType(enumClass);
        T[] arr = enumClass.getEnumConstants();
        return new RandUnitGenericFromImpl<T>(rand, arr);
    }

    public <T, R> RandUnitGenericFromMapKeysImpl<T, R> fromKeys(Map<T, R> map) {
        return new RandUnitGenericFromMapKeysImpl<>(rand, map);
    }

    public <T, R>RandUnitGenericFromMapValuesImpl<T, R> fromValues(Map<T, R> map) {
        return new RandUnitGenericFromMapValuesImpl<>(rand, map);
    }
}
