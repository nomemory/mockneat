package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

import static com.mockneat.utils.NextUtils.checkSize;
import static com.mockneat.utils.NextUtils.checkType;

/**
 * Created by andreinicolinciobanu on 06/02/2017.
 */
public class RandUnitCollectionImpl<T> implements RandUnit<Collection<T>>{

    private static final Logger LOGGER = LoggerFactory.getLogger(RandUnitCollectionImpl.class);

    private Rand rand;
    private Supplier<T> supplier;
    private Class<? extends Collection> collectionClass;
    private Integer size;

    public RandUnitCollectionImpl(Rand rand,
                                   Supplier<T> supplier,
                                   Class<? extends Collection> collectionClass,
                                   Integer size) {
        this.rand = rand;
        this.supplier = supplier;
        this.collectionClass = collectionClass;
        this.size = size;
    }

    @Override
    public Collection<T> val() {
        checkSize(size);
        checkType(collectionClass);
        try {
            Collection result = collectionClass.newInstance();
            int cnt = size;
            while (cnt-- > 0) {
                result.add(supplier.get());
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("Cannot instatiate collection class.", e);
        }
        return new ArrayList<>();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

}
