package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.mockneat.utils.NextUtils.checkSize;
import static com.mockneat.utils.NextUtils.checkType;

/**
 * Created by andreinicolinciobanu on 06/02/2017.
 */
public class RandUnitMapWithValuesImpl<T,R> implements RandUnit<Map<T,R>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandUnitMapWithValuesImpl.class);

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
            LOGGER.error("Cannot instantiate map.", e);
        }
        return new HashMap<>();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
