package com.mockneat.mock.interfaces;

/**
 * Created by andreinicolinciobanu on 22/02/2017.
 */
public interface MockValue {
    Object get();
    default String getStr() {
        Object obj = get();
        if (null==obj) { return ""; }
        else { return obj.toString(); }
    }
}
