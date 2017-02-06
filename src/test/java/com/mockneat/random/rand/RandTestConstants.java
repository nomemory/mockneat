package com.mockneat.random.rand;

import com.mockneat.random.Rand;
import com.mockneat.types.enums.RandType;

/**
 * Created by andreinicolinciobanu on 30/12/2016.
 */
public class RandTestConstants {

    public static final Rand[] RANDS = { new Rand(RandType.RANDOM), new Rand(RandType.SECURE_RANDOM), new Rand(RandType.THREAD_LOCAL_RANDOM) };
    public static final Rand RAND = RANDS[0];

    public static final Integer NEXT_BOOLEAN_CYCLES = 1000;
    public static final Integer BOOLEAN_STREAMS_LIMIT = 100;

    public static final Integer NEXT_DOUBLE_CYCLES = 1000;
    public static final Integer DOUBLE_STREAMS_LIMIT = 100;

    public static final Integer NEXT_LONG_CYCLES = 1000;
    public static final Integer LONG_STREAMS_LIMIT = 100;

    public static final Integer NEXT_INTEGER_CYCLES = 1000;

    public static final Integer NEXT_BYTES_CYLCES = 1000;

    public static final Integer NEXT_FLOAT_CYCLES = 1000;

    public static final Integer NEXT_GENERIC_CYCLES = 1000;

    public static final Integer NEXT_ALPHABET_CYCLES = 1000;
    public static final Integer ALPHABET_STREAMS_LIMIT = 100;

    public static final Integer NEXT_STRING_CYCLES = 1000;

    public static final Integer NEXT_NETWORKING_CYCLES = 1000;

    public static final Integer NEXT_COUNTRY_CYCLES = 1000;
    public static final Integer COUNTRY_STREAMS_LIMIT = 100;

    public static final Integer NEXT_CREDIT_CARD_CYCLES = 1000;
    public static final Integer CREDIT_CARD_STREAMS_LIMIT = 100;
}
