package com.mockneat.types.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

/**
 * Created by andreinicolinciobanu on 19/12/2016.
 */
public enum StringFormatType {

    UPPER_CASE(StringUtils::upperCase),
    LOWER_CASE(StringUtils::lowerCase),
    CAPITALIZED(StringUtils::capitalize);

    private Function<String, String> formatter;

    public Function<String, String> getFormatter() {
        return formatter;
    }

    StringFormatType(Function<String, String> formatter) {
        this.formatter = formatter;
    }
}
