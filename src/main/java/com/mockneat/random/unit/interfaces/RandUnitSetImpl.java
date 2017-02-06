package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static com.mockneat.utils.NextUtils.checkSize;
import static com.mockneat.utils.NextUtils.checkType;

/**
 * Created by andreinicolinciobanu on 06/02/2017.
 */
public class RandUnitSetImpl<T> implements RandUnit<Set<T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandUnitMapWithValuesImpl.class);

    private Rand rand;
    private Supplier<T> supplier;
    private Class<? extends Set> collectionClass;
    private Integer size;

    public RandUnitSetImpl(Rand rand,
                            Supplier<T> supplier,
                            Class<? extends Set> collectionClass,
                            Integer size) {
        this.rand = rand;
        this.supplier = supplier;
        this.collectionClass = collectionClass;
        this.size = size;
    }

    @Override
    public Set<T> val() {
        checkSize(size);
        checkType(collectionClass);
        try {
            Set result = collectionClass.newInstance();
            int cnt = size;
            while (cnt-- > 0) {
                result.add(supplier.get());
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("Cannot instantiate set.", e);
        }
        return new HashSet<>();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
