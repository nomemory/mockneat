package com.mockneat.utils;

import com.mockneat.types.Pair;
import com.mockneat.types.enums.*;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by andreinicolinciobanu on 03/01/2017.
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
