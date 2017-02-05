package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitFromImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnitFromMapKeysImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnitFromMapValuesImpl;

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

    public <T> RandUnit<T> from(List<T> alphabet) {
        return new RandUnitFromImpl<>(rand, alphabet);
    }

    public <T> RandUnit<T> from(T[] alphabet) {
        return new RandUnitFromImpl<>(rand, alphabet);
    }

    public <T extends Enum<?>> RandUnit<T> from(Class<T> enumClass) {
        checkType(enumClass);
        T[] arr = enumClass.getEnumConstants();
        return new RandUnitFromImpl<T>(rand, arr);
    }

    public <T, R> RandUnitFromMapKeysImpl<T, R> fromKeys(Map<T, R> map) {
        return new RandUnitFromMapKeysImpl<>(rand, map);
    }

    public <T, R>RandUnitFromMapValuesImpl<T, R> fromValues(Map<T, R> map) {
        return new RandUnitFromMapValuesImpl<>(rand, map);
    }
}
