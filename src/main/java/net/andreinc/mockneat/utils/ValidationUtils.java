package net.andreinc.mockneat.utils;

import org.apache.commons.lang3.Validate;

import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

public class ValidationUtils {

    public static final String INPUT_PARAMETER_NOT_NULL = "Input parameter: '%s' should not be NULL.";
    public static final String INPUT_PARAMETER_NOT_NULL_OR_EMPTY = "Input parameter: '%s' should not be NULL or empty.";
    public static final String INPUT_COMPOSE_TYPE_NOT_NULL = "Cannot compose Object if the type of the parameter is NULL.";
    public static final String LOWER_BOUND_BIGGER_THAN_ZERO = "The input parameter 'lowerBound' should be >= 0.0.";
    public static final String UPPER_BOUND_BIGGER_THAN_ZERO = "The input parameter 'upperBound' should be > 0.0.";
    public static final String UPPER_BOUND_BIGGER_LOWER_BOUND = "The input parameter 'upperBound' > 'lowerBound'.";
    public static final String UPPER_MONTH_BIGGER_THAN_LOWER = "'lower' Month < 'upper' Month";
    public static final String SIZE_BIGGER_THAN_ZERO_STRICT = "The size needs to be bigger than 0 (>).";
    public static final String SIZE_BIGGER_THAN_ZERO = "The size needs to be bigger than 0 (>=).";
    public static final String CANNOT_ADD_VALUE_TO_COLLECTION = "Cannot add value '%s' to Collection('%s')";
    public static final String CANNOT_ADD_VALUE_TO_LIST = "Cannot add value '%s' to List('%s')";
    public static final String CANNOT_ADD_VALUE_TO_SET = "Cannot add value '%s' to Set('%s')";
    public static final String CANNOT_PUT_VALUES_TO_MAP = "Cannot put values {'%s' : '%s'} to Map('%s')";
    // Time
    public static final String BEFORE_DAY_DIFFERENT_THAN_MONDAY = "Cannot use 'Monday' as 'before'. 'Monday' is considered to be the first day of the week.";
    public static final String AFTER_DAY_DIFFERENT_THAN_SUNDAY = "Cannot use 'Sunday' as 'after'. 'Sunday' is considered to be the last day of the week.";
    public static final String BEFORE_MONTH_DIFFERENT_THAN_JANUARY = "Cannot use 'January' as 'before'. 'January' is considered to be the first month of the year.";
    public static final String AFTER_MONTH_DIFFERENT_TNAN_DECEMBER = "Cannot use 'December' as 'after'. 'December' is considered to be the last month of the year.";
    public static final String CANNOT_URL_ENCODE_UTF_8 = "Cannot URL encode the following string: '%s'.";
    public static final String LOWER_DATE_SMALLER_THAN_UPPER_DATE = "lowerDate '%s' should be < than upperDate '%s'.";
    public static final String LOCAL_DATE_IN_INTERVAL = "%s(%s) should be in in the interval [%s, %s].";
    public static final String MAX_DATE_NOT_BIGGER_THAN = "'maxDate' (%s)  should be < than '%s' (LocalDates.MAX)";
    public static final String MIN_DATE_BIGGER_THAN = "'minDate' (%s) should be > than '%s' (LocalDate.MIN)";
    public static final String MAX_DATE_DIFFERENT_THAN_NOW = "'maxDate' (%s) should be > than now() (%s).";
    public static final String MIN_DATE_DIFFERENT_THAN_NOW = "'minDate' (%s) should be < than now() (%s).";
    // MOCK
    public static final String CANNOT_INSTANTIATE_OBJECT_OF_CLASS = "Cannot create an instance of '%s'. Please verify if the class has a public 'No Arguments' constructor: %s().";
    public static final String CANNOT_SET_FIELD_WITH_VALUE = "Cannot set field %s.%s with value '%s'. Is the supplied value correct ?";
    public static final String CANNOT_INFER_CONSTRUCTOR = "Cannot infer %s%s. Are you sure the constructor exists and it's public ?";
    public static final String CANNOT_INVOKE_STATIC_FACTORY_METHOD = "Cannot invoke '%s.%s()' with params: '%s'.";
    public static final String CANNOT_INVOKE_STATIC_FACTORY_METHOD_RETURN = "Cannot invoke '%s.%s()' with params: '%s'. The return type doesn't match the expected return type.'";
    public static final String JAVA_FIELD_REGEX_MATCH = "Field '%s' doesn't match the Java Naming Conventions for fields.";
    public static final String JAVA_METHOD_REGEX_MATCH = "Method '%s' doesn't match the Java Naming Conventions for methods.";
    public static final String JAVA_FIELD_IS_FINAL = "Field '%s' is marked as FINAL. It cannot be modified. Please remove it from the fields list.";
    public static final String JAVA_FIELD_DOESNT_EXIST_ON_CLASS = "Cannot access field: '%s'.";
    // FORMATTER
    public static final String INPUT_PARAM_ALPHANUMERIC = "Input param '%s' should be AlphaNumeric.";
    // SEQ
    public static final String INT_SEQ_OVERFLOW = "IntSeq overflow. Values are generated inside the interval: [%d, %d]. Cannot increment any further.";
    public static final String LONG_SEQ_OVERFLOW = "LongSeq overflow. Values are generated inside the interval: [%d, %d]. Cannot increment any further.";
    public static final String SEQ_INVALID_RANGE = "The min value (%d) should be lower than the maximum (%d) value of the sequence.";
    // UTILS
    public static final String INVALID_PARAM_NAME_LENGTH_0 = "Named param should have a length!=0.";
    public static final String CANNOT_FIND_PARAM_IN_ARGS = "Cannot find param: '%s' in the map of arguments.";
    // ARRAY
    public static final String ARRAY_CANNOT_BE_INSTANTIED = "Array couldn't be instantiated. MockUnit<T> returned a NULL value when calling val() internally. We cannot infer the type of the array.";
    // PROBABILITIES
    public static final String PROBABILITIES_SUM_BIGGER = "Probabilities sum cannot be bigger (>) than '1.0'.";
    public static final String PROBABILITIES_SUM_NOT_1 = "Probabilities sum must be exactly (=) 1.0 when generating values.";

    private ValidationUtils() {}

    public static <T> T[] notEmptyTypes(T... types) {
        Validate.notEmpty(types, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, types);
        range(0, types.length).forEach(i ->
            notNull(types[i], INPUT_PARAMETER_NOT_NULL, "types[" + i + "]"));
        return types;
    }
    public static char[] notEmpty(char[] array, String fmt, Object... params) {
        if (null==array)
            throw new NullPointerException(String.format(fmt, params));
        if (0==array.length)
            throw new IllegalArgumentException(String.format(fmt, params));
        return array;
    }
    public static double [] notEmpty(double[] array, String fmt, Object... params) {
        if (null==array)
            throw new NullPointerException(String.format(fmt, params));
        if (0==array.length)
            throw new IllegalArgumentException(String.format(fmt, params));
        return array;
    }
    public static float[] notEmpty(float[] array, String fmt, Object... params) {
        if (null==array)
            throw new NullPointerException(String.format(fmt, params));
        if (0==array.length)
            throw new IllegalArgumentException(String.format(fmt, params));
        return array;
    }
    public static int[] notEmpty(int[] array, String fmt, Object... params) {
        if (null==array)
            throw new NullPointerException(String.format(fmt, params));
        if (0==array.length)
            throw new IllegalArgumentException(String.format(fmt, params));
        return array;
    }
    public static long[] notEmpty(long[] array, String fmt, Object... params) {
        if (null==array)
            throw new NullPointerException(String.format(fmt, params));
        if (0==array.length)
            throw new IllegalArgumentException(String.format(fmt, params));
        return array;
    }
}
