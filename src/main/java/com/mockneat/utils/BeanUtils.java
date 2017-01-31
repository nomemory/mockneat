package com.mockneat.utils;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class BeanUtils {

    public static final String getGetterName(String field) {
        field = StringUtils.capitalize(field);
        return "get" + field;
    }

    public static final String getSetterName(String field) {
        field = StringUtils.capitalize(field);
        return "set" + field;
    }

}
