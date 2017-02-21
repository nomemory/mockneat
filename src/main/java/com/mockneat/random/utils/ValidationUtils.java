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
    //TODO public static final String PORT_VALUE_BIGGER_THAN_ZERO = "The port value needs to be bigger than 0 (>).";
    public static final String CANNOT_ADD_VALUE_TO_COLLECTION = "Cannot add value '%s' to Collection('%s')";
    public static final String CANNOT_ADD_VALUE_TO_LIST = "Cannot add value '%s' to List('%s')";
    public static final String CANNOT_ADD_VALUE_TO_SET = "Cannot add value '%s' to Set('%s')";
    public static final String CANNOT_PUT_VALUES_TO_MAP = "Cannot put values {'%s' : '%s'} to Map('%s')";
    public static final String BEFORE_DAY_DIFFERENT_THAN_MONDAY = "Cannot use 'Monday' as 'before'. 'Monday' is considered to be the first day of the week.";
    public static final String AFTER_DAY_DIFFERENT_THAN_SUNDAY = "Cannot use 'Sunday' as 'after'. 'Sunday' is considered to be the last day of the week.";
    public static final String BEFORE_MONTH_DIFFERENT_THAN_JANUARY = "Cannot use 'January' as 'before'. 'January' is considered to be the first month of the year.";
    public static final String AFTER_MONTH_DIFFERENT_TNAN_DECEMBER = "Cannot use 'December' as 'after'. 'December' is considered to be the last month of the year.";
    public static final String CANNOT_URL_ENCODE_UTF_8 = "Cannot URL encode the following string: '%s'.";

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
