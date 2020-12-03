package net.andreinc.mockneat.utils;

import net.andreinc.mockneat.abstraction.MockUnit;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.andreinc.aleph.AlephFormatter.str;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public final class MockUnitUtils {

    private MockUnitUtils() {}

    @SuppressWarnings("rawtypes")
    public static <T> void add(Class<? extends Collection> collectionClass, Collection<T> result, Supplier<T> supplier) {
        T value = supplier.get();
        try {
            result.add(value);
        } catch (Exception e) {
            String msg = str(CANNOT_ADD_VALUE_TO_COLLECTION)
                            .arg("val", value)
                            .arg("cls", collectionClass)
                            .fmt();
            throw new IllegalArgumentException(msg, e);
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T> void add(Class<? extends List> listClass, List<T> result, Supplier<T> supplier) {
        T value = supplier.get();
        try {
            result.add(value);
        } catch (Exception e) {
            String msg = str(CANNOT_ADD_VALUE_TO_LIST).args("value", value, "cls", listClass).fmt();
            throw new IllegalArgumentException(msg, e);
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T> void add(Class<? extends Set> setClass, Set<T> result, Supplier<T> supplier) {
        T value = supplier.get();
        try {
            result.add(value);
        } catch (Exception e) {
            String msg = str(CANNOT_ADD_VALUE_TO_SET).args("value", value, "cls", setClass).fmt();
            throw new IllegalArgumentException(msg, e);
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T, R> void put(Class<? extends Map> mapClass, Map<T, R> map, Supplier<T> keySupplier, Supplier<R> valueSupplier) {
        T keyVal = keySupplier.get();
        R valVal =  valueSupplier.get();
        try {
            map.put(keyVal, valVal);
        } catch(Exception e ) {
            String msg = str(CANNOT_PUT_VALUES_TO_MAP)
                            .args("key", keyVal, "val", valVal, "cls", mapClass.getSimpleName())
                            .fmt();
            throw new IllegalArgumentException(msg, e);
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T, R> void put(Class<? extends Map> mapClass, Map<T, R> map, T key, R value) {
        try {
            map.put(key, value);
        } catch (Exception e) {
            String msg = str(CANNOT_PUT_VALUES_TO_MAP)
                            .args("key", key, "val", value, "cls", mapClass.getSimpleName())
                            .fmt();
            throw new IllegalArgumentException(msg, e);
        }
    }

    public static Object mockOrObject(Object obj) {
        if (null == obj) {
            return null;
        }
        if (obj instanceof MockUnit<?>) {
            return ((MockUnit<?>) obj).val();
        }
        return obj;
    }

    public static String listTypes(Object[] objects) {
        final StringBuilder buff = new StringBuilder("(");
        Arrays.stream(objects).forEach(obj -> {
            if (null != obj) { buff.append(obj.getClass().getName()); }
            else { buff.append("null"); }
            buff.append(',');
        });
        buff.deleteCharAt(buff.length()-1);
        buff.append(')');
        return buff.toString();
    }

    public static <T, R> Supplier<R> ifSupplierNotNullDo(Supplier<T> supplier, Function<T, R> function) {
        return () -> {
            T val = supplier.get();
            if (null == val)
                return null;
            return function.apply(val);
        };
    }

}
