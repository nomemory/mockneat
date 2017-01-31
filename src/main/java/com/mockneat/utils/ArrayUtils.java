package com.mockneat.utils;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by andreinicolinciobanu on 22/12/2016.
 */
public class ArrayUtils {

    private ArrayUtils() {}

    public static final Boolean[] toWrapperArray(boolean[] array) {
        if (null == array)
            return null;

        if (0 == array.length)
            return new Boolean[0];

        final Boolean[] result = new Boolean[array.length];

        IntStream
                .range(0, array.length)
                .forEach(i -> result[i] = Boolean.valueOf(array[i]));

        return result;
    }

    public static final Byte[] toWrapperArray(byte[] array) {
        if (null == array)
            return null;

        if (0 == array.length)
            return new Byte[0];

        final Byte[] result = new Byte[array.length];

        IntStream
                .range(0, array.length)
                .forEach(i -> result[i] = Byte.valueOf(array[i]));

        return result;
    }

    public static final Character[] toWrapperArray(char[] array) {
        if (null == array)
            return null;

        if (0 == array.length)
            return new Character[0];

        final Character[] result = new Character[array.length];

        IntStream
                .range(0, array.length)
                .forEach(i -> result[i] = Character.valueOf(array[i]));

        return result;
    }

    public static final Double[] toWrapperArray(double[] array) {
        if (null==array)
            return null;

        if (0 == array.length)
            return new Double[0];

        final Double[] result = new Double[array.length];

        IntStream
                .range(0, array.length)
                .forEach(i -> result[i] = Double.valueOf(array[i]));

        return result;
    }

    public static final Float[] toWrapperArray(float[] array) {
        if (null==array)
            return null;

        if (0 == array.length)
            return new Float[0];

        final Float[] result = new Float[array.length];

        IntStream
                .range(0, array.length)
                .forEach(i -> result[i] = Float.valueOf(array[i]));

        return result;
    }

    public static final Integer[] toWrapperArray(int[] array) {
        if (null == array)
            return null;

        if (0 == array.length)
            return new Integer[0];

        final Integer[] result = new Integer[array.length];

        IntStream
                .range(0, array.length)
                .forEach(i -> result[i] = Integer.valueOf(array[i]));

        return result;
    }

    public static final Long[] toWrapperArray(long[] array) {
        if (null == array)
            return null;

        if (0 == array.length)
            return new Long[0];

        Long[] result = new Long[array.length];

        IntStream
                .range(0, array.length)
                .forEach(i -> result[i] = Long.valueOf(array[i]));

        return result;
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
