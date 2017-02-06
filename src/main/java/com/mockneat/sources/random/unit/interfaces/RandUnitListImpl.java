package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.List;
import java.util.function.Supplier;

import static com.mockneat.utils.NextUtils.checkSize;
import static com.mockneat.utils.NextUtils.checkType;

public class RandUnitListImpl<T> implements RandUnit<List<T>>{
    private Rand rand;
    private Supplier<T> supplier;
    private Class<? extends List> collectionClass;
    private Integer size;

    public RandUnitListImpl(Rand rand,
                            Supplier<T> supplier,
                            Class<? extends List> collectionClass,
                            Integer size) {
        this.rand = rand;
        this.supplier = supplier;
        this.collectionClass = collectionClass;
        this.size = size;
    }

    @Override
    public List<T> val() {
        checkSize(size);
        checkType(collectionClass);
        try {
            List result = collectionClass.newInstance();
            int cnt = size;
            while (cnt-- > 0) {
                result.add(supplier.get());
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
