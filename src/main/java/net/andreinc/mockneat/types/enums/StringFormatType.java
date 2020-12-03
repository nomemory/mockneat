package net.andreinc.mockneat.types.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.function.UnaryOperator;

@SuppressWarnings("ImmutableEnumChecker")
public enum StringFormatType {

    UPPER_CASE(StringUtils::upperCase),
    LOWER_CASE(StringUtils::lowerCase),
    CAPITALIZED(StringUtils::capitalize);

    private final UnaryOperator<String> formatter;

    StringFormatType(UnaryOperator<String> formatter) {
        this.formatter = formatter;
    }

    public UnaryOperator<String> getFormatter() {
        return formatter;
    }

}
