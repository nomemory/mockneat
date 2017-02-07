package com.mockneat.utils;

import com.mockneat.types.enums.*;

import java.util.List;
import java.util.function.Function;

/**
 * Created by andreinicolinciobanu on 03/01/2017.
 */
public class NextUtils {

    public static final String INVALID_CLASS_TYPE = "Invalid class type. Type cannot be null.";
    public static final String IPV4_TYPE_NOT_NULL = "Invalid IPv4Type. Type cannot be null.";
    public static final String MAC_FORMAT_TYPE_NOT_NULL = "Invalid MACAddressFormatType. Type cannot be null.";
    public static final String STRING_FORMAT_TYPE_NOT_NULL = "Invalid StringFormatType. Type cannot be null.";
    public static final String CREDIT_CARD_TYPE_NOT_NULL = "Invalid CreditCardType. Type cannot be null.";
    public static final String CVV_TYPE_NOT_NULL = "Invalid CVVType. Type cannot be null.";
    public static final String BOUND_NOT_NULL = "Invalid bound(s). Value(s) must be different than NULL.";
    public static final String BOUND_NOT_NAN = "Invalid bound(s). Value(s) must be different than NaN.";
    public static final String BOUND_NOT_INFINITY = "Invalid bound(s). Value(s) must be different than INFINITY.";
    public static final String BOUND_NOT_ZERO = "Invalid bound(s). Value(s) must be different than ZERO.";
    public static final String BOUND_POSITIVE = "Invalid bound(s). Value(s) must pe positive.";
    public static final String BOUNDS_INVALID = "Invalid bound(s). Values must be positive and lowerBound < upperBound.";
    public static final String INVALID_LIST_SIZE = "Invalid list size. Values must be non-null and strictly greater than ZERO.";
    public static final String INVALID_ALPHABET_R = "Invalid type. The type cannot be NULL.";
    public static final String INVALID_ALPHABET = "Invalid type. The array cannot be null or empty.";
    public static final String INVALID_ALPHABET_LIST = "Invalid type. The list cannot be empty or null.";
    public static final String INVALID_CHAR_ALPHABET = "Invalid char[] alphabet. The array cannot be null or empty";
    public static final String INVALID_STRING_ALPHABET = "Invalid String alphabet. The string cannot be null or empty.";
    public static final String INVALID_INT_ALPHABET = "Invalid int[] alphabet. The array cannot be null or empty.";
    public static final String INVALID_DOUBLE_ALPHABET = "Invalid double[] alphabet. The array cannot be null or empty.";
    public static final String INVALID_BYTE_ALPHABET = "Invalid byte[] alphabet. The array cannot be null or empty.";
    public static final String INVALID_FLOAT_ALPHABET = "Invalid float  [] alphabet. The array cannot be null or empty.";
    public static final String INVALID_NEXT_FUNCTION = "Invalid name() function. The function cannot be NULL.";
    public static final String INVALID_PROBABILITY = "Invalid probability. Value must be a number different than null between [0, 100].";
    public static final String INVALID_TYPES = "Invalid types. Array cannot be null or empty.";
    public static final String CANNOT_CREATE_INSTANCE_OF_LIST = "Cannot create instance of List. Please check if the List implementation has a public non-arg constructor.";

    private NextUtils() {}


    public static void checkType(Class<?> type) {
        if (null == type) {
            throw new IllegalArgumentException(INVALID_CLASS_TYPE);
        }
    }

    public static <T> void checkTypes(T[] types) {
        if (null==types || 0 == types.length) {
            throw new IllegalArgumentException(INVALID_TYPES);
        }
    }

    public static void checkDoubleBound(Double bound) {
        if (null == bound) {
            throw new IllegalArgumentException(BOUND_NOT_NULL);
        }

        if (bound.isNaN()) {
            throw new IllegalArgumentException(BOUND_NOT_NAN);
        }

        if (bound.isInfinite()) {
            throw new IllegalArgumentException(BOUND_NOT_INFINITY);
        }

        if (bound < 0.0) {
            throw new IllegalArgumentException(BOUND_POSITIVE);
        }
    }

    public static void checkFloatBound(Float bound) {
        if (null == bound) {
            throw new IllegalArgumentException(BOUND_NOT_NULL);
        }

        if (bound.isNaN()) {
            throw new IllegalArgumentException(BOUND_NOT_NAN);
        }

        if (bound.isInfinite()) {
            throw new IllegalArgumentException(BOUND_NOT_INFINITY);
        }

        if (bound < 0.0f) {
            throw new IllegalArgumentException(BOUND_POSITIVE);
        }
    }

    public static void checkDoubleBoundNotZero(Double bound) {
        if (bound.equals(0.0)) {
            throw new IllegalArgumentException(BOUND_NOT_ZERO);
        }
    }

    public static void checkIntegerBound(Integer bound) {
        if (null == bound) {
            throw new IllegalArgumentException(BOUND_NOT_NULL);
        }

        if (bound < 0) {
            throw new IllegalArgumentException(BOUND_POSITIVE);
        }
    }

    public static void checkLongBound(Long bound) {
        if (null == bound) {
            throw new IllegalArgumentException(BOUND_NOT_NULL);
        }

        if (bound < 0L) {
            throw new IllegalArgumentException(BOUND_POSITIVE);
        }
    }

    public static void checkIntegerBoundNotZero(Integer bound) {
        if (null == bound) {
            throw new IllegalArgumentException(BOUND_NOT_ZERO);
        }
    }

    public static void checkDoubleBounds(Double lowerBound, Double upperBound) {

        checkDoubleBound(lowerBound);
        checkDoubleBound(upperBound);

        if (lowerBound < 0 || (lowerBound >= upperBound) || upperBound < 0) {
            throw new IllegalArgumentException(BOUNDS_INVALID);
        }
    }
    public static void checkFloatBounds(Float lowerBound, Float upperBound) {

        checkFloatBound(lowerBound);
        checkFloatBound(upperBound);

        if (lowerBound < 0 || (lowerBound >= upperBound) || upperBound < 0) {
            throw new IllegalArgumentException(BOUNDS_INVALID);
        }
    }


    public static void checkIntegerBounds(Integer lowerBound, Integer upperBound) {
        checkIntegerBound(lowerBound);
        checkIntegerBound(upperBound);

        if (lowerBound < 0 || (lowerBound >= upperBound) || upperBound < 0) {
            throw new IllegalArgumentException(BOUNDS_INVALID);
        }
    }

    public static void checkLongBounds(Long lowerBound, Long upperBound) {
        checkLongBound(lowerBound);
        checkLongBound(upperBound);

        if (lowerBound < 0L || (lowerBound >= upperBound) || upperBound < 0L) {
            throw new IllegalArgumentException(BOUNDS_INVALID);
        }
    }

    public static void checkSize(Integer size) {
        if (null == size || 0 >= size) {
            throw new IllegalArgumentException(INVALID_LIST_SIZE);
        }
    }

    public static void checkIntegerAlphabet(int[] alphabet) {
        if (null == alphabet || 0 == alphabet.length) {
            throw new IllegalArgumentException(INVALID_INT_ALPHABET);
        }
    }

    public static void checkLongAlphabet(long[] alphabet) {
        if (null == alphabet || 0L == alphabet.length) {
            throw new IllegalArgumentException(INVALID_INT_ALPHABET);
        }
    }

    public static void checkDoubleAlphabet(double[] alphabet) {
        if (null == alphabet || 0 == alphabet.length) {
            throw new IllegalArgumentException(INVALID_DOUBLE_ALPHABET);
        }
    }

    public static void checkByteAlphabet(byte[] alphabet) {
        if (null == alphabet || 0 == alphabet.length) {
            throw new IllegalArgumentException(INVALID_BYTE_ALPHABET);
        }
    }

    public static void checkStringAlpabet(String alphabet) {
        if (null == alphabet || 0 == alphabet.length()) {
            throw new IllegalArgumentException(INVALID_STRING_ALPHABET);
        }
    }

    public static void checkCharAlphabet(char[] alphabet) {
        if (null == alphabet || 0 == alphabet.length) {
            throw new IllegalArgumentException(INVALID_CHAR_ALPHABET);
        }
    }

    public static <T> void checkAlphabet(T[] alphabet) {
        if (null == alphabet || 0 == alphabet.length) {
            throw new IllegalArgumentException(INVALID_ALPHABET);
        }
    }

    public static <T> void checkAlphabet(List<T> alphabet) {
        if (null == alphabet || 0 == alphabet.size()) {
            throw new IllegalArgumentException(INVALID_ALPHABET_LIST);
        }
    }

    public static void checkFloatAlphabet(float[] alphabet) {
        if (null == alphabet || 0 == alphabet.length) {
            throw new IllegalArgumentException(INVALID_FLOAT_ALPHABET);
        }
    }

    public static  <R,T> void checkListParams(Function<R, T> next,
                                              R alphabet,
                                              Integer size,
                                              Class<? extends List> cls) {
        if (null == next) {
            throw new IllegalArgumentException(INVALID_NEXT_FUNCTION);
        }

        if (null == alphabet) {
            throw new IllegalArgumentException(INVALID_ALPHABET_R);
        }

        if (null == size) {
            throw new IllegalArgumentException(INVALID_LIST_SIZE);
        }

        if (null == cls) {
            throw new IllegalArgumentException(INVALID_CLASS_TYPE);
        }
    }

    public static void checkProbability(Integer probability) {
        if (null == probability) {
            throw new IllegalArgumentException(INVALID_PROBABILITY);
        }
        checkProbabilityBounds(probability);
    }

    public static void checkProbabilityBounds(Integer probability) {
        if (0 > probability || probability > 100) {
            throw new IllegalArgumentException(INVALID_PROBABILITY);
        }
    }

    public static void checkProbability(Double probability) {
        if (null == probability) {
            throw new IllegalArgumentException(INVALID_PROBABILITY);
        }
        checkProbabilityBounds(probability);
    }

    public static void checkProbabilityBounds(Double probability) {
        if (0.0 > probability || probability > 100.0) {
            throw new IllegalArgumentException(INVALID_PROBABILITY);
        }
    }

    public static void checkIpTypeNotNull(IPv4Type type) {
        if (null == type) {
            throw new IllegalArgumentException(IPV4_TYPE_NOT_NULL);
        }
    }

    public static void checkMacFormatTypeNotNull(MACAddressFormatType type) {
        if (null == type) {
            throw new IllegalArgumentException(MAC_FORMAT_TYPE_NOT_NULL);
        }
    }

    public static void checkStringFormatTypeNotNull(StringFormatType type) {
        if (null == type) {
            throw new IllegalArgumentException(STRING_FORMAT_TYPE_NOT_NULL);
        }
    }

    public static void checkCreditCardTypeNotNull(CreditCardType type) {
        if (null == type) {
            throw new IllegalArgumentException(CREDIT_CARD_TYPE_NOT_NULL);
        }
    }

    public static void checkCVVTypeNotNull(CVVType type) {
        if (null == type) {
            throw new IllegalArgumentException(CVV_TYPE_NOT_NULL);
        }
    }
}
