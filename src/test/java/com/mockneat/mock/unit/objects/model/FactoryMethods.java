package com.mockneat.mock.unit.objects.model;

/**
 * Created by andreinicolinciobanu on 08/03/2017.
 */
public class FactoryMethods {

    public static StringBuilder buffBuilder(String val) {
        return new StringBuilder(val);
    }

    public static StringBuffer buffBuffer(String val) {
        return new StringBuffer(val);
    }
}
