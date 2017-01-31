package com.mockneat.sources.random.unit.interfaces;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public interface RandUnit<T> {

    T val();

    default Collection<T> collection(Integer size) {
        return collection(size, List.class);
    }

    //TODO validate size
    default Collection<T> collection(Integer size, Class<? extends Collection> collectionClass) {
        try {
            Collection<T> result = collectionClass.newInstance();
            int cnt = size;
            while (cnt-->0) {
                result.add(val());
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
