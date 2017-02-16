package com.mockneat.random;

import com.mockneat.types.enums.RandType;

/**
 * Created by andreinicolinciobanu on 30/12/2016.
 */
public class RandTestConstants {

    public static final Rand[] RANDS = { new Rand(RandType.RANDOM), new Rand(RandType.SECURE_RANDOM), new Rand(RandType.THREAD_LOCAL_RANDOM) };
    public static final Rand RAND = RANDS[0];

    public static final Integer BOOLS_CYCLES = 10000;
    public static final Integer CCS_CYCLES = 10000;
    public static final Integer CHARS_CYCLES = 10000;
    public static final Integer COMPOSE_CYCLES = 10000;
    public static final Integer CVVS_CYCLES= 10000;
    public static final Integer COUNTRIES_CYCLES = 10000;
    public static final Integer DAYS_CYCLES = 10000;
    public static final Integer DOUBLES_CYCLES = 10000;
    public static final Integer EMAILS_CYCLES = 10000;
    public static final Integer INTS_CYCLES = 10000;
    public static final Integer IPV4S_CYCLES = 10000;
    public static final Integer FLOATS_CYCLES = 10000;
    public static final Integer LONGS_CYCLES = 10000;
    public static final Integer OBJS_CYCLES = 10000;
    public static final Integer RU_CYCLES = 10000;
}
