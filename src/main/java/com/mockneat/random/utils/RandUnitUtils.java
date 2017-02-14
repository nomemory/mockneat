package com.mockneat.random.utils;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static com.mockneat.random.utils.ValidationUtils.*;

public class RandUnitUtils {
    @SuppressWarnings("unchecked")
    public static <T> void add(Class<? extends Collection> collectionClass, Collection<T> result, Supplier<T> supplier) {
        T value = supplier.get();
        try {
            result.add(value);
        } catch (Exception e) {
            String msg = String.format(CANNOT_ADD_VALUE_TO_COLLECTION,
                    value, collectionClass.getSimpleName());
            throw new IllegalArgumentException(msg, e);
        }
    }
    @SuppressWarnings("unchecked")
    public static <T> void add(Class<? extends List> listClass, List<T> result, Supplier<T> supplier) {
        T value = supplier.get();
        try {
            result.add(value);
        } catch (Exception e) {
            String msg = String.format(CANNOT_ADD_VALUE_TO_LIST,
                    value, listClass.getSimpleName());
            throw new IllegalArgumentException(msg, e);
        }
    }
    @SuppressWarnings("unchecked")
    public static <T> void add(Class<? extends Set> setClass, Set<T> result, Supplier<T> supplier) {
        T value = supplier.get();
        try {
            result.add(value);
        } catch (Exception e) {
            String msg = String.format(CANNOT_ADD_VALUE_TO_SET,
                    value, setClass.getSimpleName());
            throw new IllegalArgumentException(msg, e);
        }
    }
    @SuppressWarnings("unchecked")
    public static <T, R> void put(Class<? extends Map> mapClass, Map<T, R> map, Supplier<T> keySupplier, Supplier<R> valueSupplier) {
        T keyVal = keySupplier.get();
        R valVal =  valueSupplier.get();
        try {
            map.put(keyVal, valVal);
        } catch(Exception e ) {
            String msg = String.format(CANNOT_PUT_VALUES_TO_MAP,
                    keyVal, valVal, mapClass.getSimpleName());
            throw new IllegalArgumentException(msg, e);
        }
    }
    @SuppressWarnings("unchecked")
    public static <T, R> void put(Class<? extends Map> mapClass, Map<T, R> map, T key, R value) {
        try {
            map.put(key, value);
        } catch (Exception e) {
            String msg = String.format(CANNOT_PUT_VALUES_TO_MAP,
                    key, value, mapClass.getSimpleName());
            throw new IllegalArgumentException(msg, e);
        }
    }
}
