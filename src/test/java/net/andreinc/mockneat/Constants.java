package net.andreinc.mockneat;

import static net.andreinc.mockneat.MockNeat.old;
import static net.andreinc.mockneat.MockNeat.secure;
import static net.andreinc.mockneat.MockNeat.threadLocal;

/**
 * Created by andreinicolinciobanu on 30/12/2016.
 */
public class Constants {

    public static final MockNeat[] MOCKS = { old(), secure(), threadLocal() };
    public static final MockNeat M = threadLocal();

    public static final int BOOLS_CYCLES = 100;
    public static final int CCS_CYCLES = 100;
    public static final int CHARS_CYCLES = 100;
    public static final int COMPOSE_CYCLES = 100;
    public static final int CVVS_CYCLES= 100;
    public static final int DEP_CYCLES = 100;
    public static final int COUNTRIES_CYCLES = 100;
    public static final int CURRENCIES_CYCLES = 100;
    public static final int DAYS_CYCLES = 100;
    public static final int DOMAIN_CYCLES = 100;
    public static final int DOUBLES_CYCLES = 100;
    public static final int EMAILS_CYCLES = 100;
    public static final int FILES_CYCLES = 100;
    public static final int FLOATS_CYCLES = 100;
    public static final int FMT_CYCLES = 100;
    public static final int INTS_CYCLES = 100;
    public static final int IPV4S_CYCLES = 100;
    public static final int IPV6S_CYCLES = 100;
    public static final int LONGS_CYCLES = 100;
    public static final int LOCAL_DATES_CYCLES = 100;
    public static final int MAC_CYCLES = 100;
    public static final int MOCK_CYCLES = 100;
    public static final int MONEY_CYCLES = 100;
    public static final int NAMES_CYCLES = 100;
    public static final int OBJS_CYCLES = 100;
    public static final int PASS_CYCLES = 100;
    public static final int SEQ_CYCLES = 100;
    public static final int STRING_CYCLES = 100;
    public static final int URL_CYCLES = 100;
}
