package com.mockneat.mock;

import com.mockneat.types.enums.RandomType;

/**
 * Created by andreinicolinciobanu on 30/12/2016.
 */
public class Constants {

    public static final MockNeat[] RANDS = { new MockNeat(RandomType.OLD), new MockNeat(RandomType.SECURE), new MockNeat(RandomType.THREAD_LOCAL) };
    public static final MockNeat RAND = RANDS[0];

    public static final int BOOLS_CYCLES = 1000;
    public static final int CCS_CYCLES = 1000;
    public static final int CHARS_CYCLES = 1000;
    public static final int COMPOSE_CYCLES = 1000;
    public static final int CVVS_CYCLES= 1000;
    public static final int COUNTRIES_CYCLES = 1000;
    public static final int CURRENCIES_CYCLES = 1000;
    public static final int DAYS_CYCLES = 1000;
    public static final int DOMAIN_CYCLES = 1000;
    public static final int DOUBLES_CYCLES = 1000;
    public static final int EMAILS_CYCLES = 1000;
    public static final int FLOATS_CYCLES = 1000;
    public static final int INTS_CYCLES = 1000;
    public static final int IPV4S_CYCLES = 1000;
    public static final int IPV6S_CYCLES = 1000;
    public static final int LONGS_CYCLES = 1000;
    public static final int MONEY_CYCLES = 1000;
    public static final int OBJS_CYCLES = 1000;
    public static final int PASS_CYCLES = 1000;
    public static final int RU_CYCLES = 1000;
    public static final int URL_CYCLES = 1000;
}
