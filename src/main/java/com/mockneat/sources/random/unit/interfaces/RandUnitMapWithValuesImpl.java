package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.Map;
import java.util.function.Supplier;

import static com.mockneat.utils.NextUtils.checkSize;
import static com.mockneat.utils.NextUtils.checkType;

/**
 * Created by andreinicolinciobanu on 06/02/2017.
 */
public class RandUnitMapWithValuesImpl<T,R> implements RandUnit<Map<T,R>> {

    private Rand rand;
    private Supplier<T> keyGen;
    private Supplier<R> valGen;
    private Class<? extends Map> mapClass;
    private Integer size;

    public RandUnitMapWithValuesImpl(Rand rand,
                                   Supplier<T> supplier,
                                   Supplier<R> valGen,
                                   Class<? extends Map> mapClass,
                                   Integer size) {
        this.rand = rand;
        this.keyGen = supplier;
        this.valGen = valGen;
        this.mapClass = mapClass;
        this.size = size;
    }

    @Override
    public Map<T, R> val() {
        checkSize(size);
        checkType(mapClass);
        try {
            Map<T,R> result = mapClass.newInstance();
            int cnt = size;
            while (cnt-- > 0) {
                result.put(keyGen.get(), valGen.get());
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
