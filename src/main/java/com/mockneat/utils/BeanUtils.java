package com.mockneat.utils;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class BeanUtils {

    public static final String getGetterName(String field) {
        field = capitalize(field);
        return "get" + field;
    }

    public static final String getSetterName(String field) {
        field = capitalize(field);
        return "set" + field;
    }

}
