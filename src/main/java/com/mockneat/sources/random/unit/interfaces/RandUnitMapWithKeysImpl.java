package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.Map;
import java.util.function.Supplier;

import static com.mockneat.utils.NextUtils.checkSize;
import static com.mockneat.utils.NextUtils.checkType;

/**
 * Created by andreinicolinciobanu on 06/02/2017.
 */
public class RandUnitMapWithKeysImpl<T,R> implements RandUnit<Map<R, T>> {

    private Rand rand;
    private Supplier<T> valGen;
    private Supplier<R> keysGen;
    private Class<? extends Map> mapClass;
    private Integer size;

    public RandUnitMapWithKeysImpl(Rand rand,
                                   Supplier<T> valGen,
                                   Supplier<R> keysGen,
                                   Class<? extends Map> mapClass,
                                   Integer size) {
        this.rand = rand;
        this.valGen = valGen;
        this.keysGen = keysGen;
        this.mapClass = mapClass;
        this.size = size;
    }

    @Override
    public Map<R, T> val() {
        checkSize(size);
        checkType(mapClass);
        try {
            Map<R, T> result = mapClass.newInstance();
            int cnt = size;
            while (cnt-- > 0) {
                result.put(keysGen.get(), valGen.get());
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO LOG ERROR
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
