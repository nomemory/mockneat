package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;
import com.mockneat.utils.NextUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static com.mockneat.utils.NextUtils.checkSize;
import static com.mockneat.utils.NextUtils.checkType;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public interface RandUnitGeneral<T> {

    T val();

    default Collection<T> collection(Integer size) {
        return collection(size, List.class);
    }

    default Collection<T> collection(Integer size, Class<? extends Collection> collectionClass) {
        checkSize(size);
        checkType(collectionClass);
        try {
            Collection<T> result = collectionClass.newInstance();
            int cnt = size;
            while (cnt-- > 0) {
                result.add(val());
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO LOG ERROR
            e.printStackTrace();
        }
        return null;
    }

    default List<T> list(Integer size) {
        checkSize(size);
        return list(size, ArrayList.class);
    }

    default List<T> list(Integer size, Class<? extends List> listClass) {
        checkSize(size);
        checkType(listClass);
        try {
            List<T> result = listClass.newInstance();
            int cnt = size;
            while (cnt-- > 0) {
                result.add(val());
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO LOG ERROR
            e.printStackTrace();
        }
        return null;
    }

    Rand getRand();
}
