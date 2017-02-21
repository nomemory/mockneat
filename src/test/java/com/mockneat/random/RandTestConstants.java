package com.mockneat.random;

import com.mockneat.types.enums.RandType;

/**
 * Created by andreinicolinciobanu on 30/12/2016.
 */
public class RandTestConstants {

    public static final Rand[] RANDS = { new Rand(RandType.RANDOM), new Rand(RandType.SECURE_RANDOM), new Rand(RandType.THREAD_LOCAL_RANDOM) };
    public static final Rand RAND = RANDS[0];

    public static final int BOOLS_CYCLES = 10000;
    public static final int CCS_CYCLES = 10000;
    public static final int CHARS_CYCLES = 10000;
    public static final int COMPOSE_CYCLES = 10000;
    public static final int CVVS_CYCLES= 10000;
    public static final int COUNTRIES_CYCLES = 10000;
    public static final int CURRENCIES_CYCLES = 10000;
    public static final int DAYS_CYCLES = 10000;
    public static final int DOUBLES_CYCLES = 10000;
    public static final int EMAILS_CYCLES = 10000;
    public static final int FLOATS_CYCLES = 10000;
    public static final int INTS_CYCLES = 10000;
    public static final int IPV4S_CYCLES = 10000;
    public static final int IPV6S_CYCLES = 10000;
    public static final int LONGS_CYCLES = 10000;
    public static final int MONEY_CYCLES = 10000;
    public static final int OBJS_CYCLES = 10000;
    public static final int PASS_CYCLES = 10000;
    public static final int RU_CYCLES = 10000;
    public static final int URL_CYCLES = 10000;
}
