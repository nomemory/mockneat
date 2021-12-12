package net.andreinc.mockneat;

import static net.andreinc.mockneat.MockNeat.old;
import static net.andreinc.mockneat.MockNeat.secure;
import static net.andreinc.mockneat.MockNeat.threadLocal;

public class Constants {

    public static final MockNeat[] MOCKS = { old(), secure(), threadLocal() };
    public static final MockNeat M = threadLocal();

    public static final int BOOLS_CYCLES = 1000;
    public static final int CCS_CYCLES = 1000;
    public static final int CHARS_CYCLES = 1000;
    public static final int COMPOSE_CYCLES = 1000;
    public static final int CREATURES_CYCLES = 1000;
    public static final int CVVS_CYCLES= 1000;
    public static final int CSVS_CYCLES = 20;
    public static final int DEP_CYCLES = 1000;
    public static final int CITIES_CYCLES= 1000;
    public static final int COUNTRIES_CYCLES = 1000;
    public static final int COMPANIES_CYCLES = 1000;
    public static final int CURRENCIES_CYCLES = 1000;
    public static final int DAYS_CYCLES = 1000;
    public static final int DOMAIN_CYCLES = 1000;
    public static final int DOUBLES_CYCLES = 1000;
    public static final int EMAILS_CYCLES = 1000;
    public static final int FILES_CYCLES = 1000;
    public static final int FLOATS_CYCLES = 1000;
    public static final int REGEX_CYLCES = 1000;
    public static final int FMT_CYCLES = 1000;
    public static final int GENDERS_CYCLES = 1000;
    public static final int IBANS_CYCLES = 1000;
    public static final int INDUSTRIES_CYCLES = 1000;
    public static final int ISSNS_CYCLES = 1000;
    public static final int INTS_CYCLES = 1000;
    public static final int IPV4S_CYCLES = 1000;
    public static final int IPV6S_CYCLES = 1000;
    public static final int LONGS_CYCLES = 1000;
    public static final int LOCAL_DATES_CYCLES = 1000;
    public static final int MAC_CYCLES = 1000;
    public static final int HASH_CYCLES = 1000;
    public static final int MIME_CYCLES = 1000;
    public static final int MOCK_CYCLES = 1000;
    public static final int MONEY_CYCLES = 1000;
    public static final int MONTH_CYCLES = 1000;
    public static final int NAMES_CYCLES = 1000;
    public static final int NON_BINARY_GENDER_CYLES = 1000;
    public static final int OBJS_CYCLES = 1000;
    public static final int OBJECT_MAP_CYCLES = 1000;
    public static final int PASS_CYCLES = 1000;
    public static final int PROBABILITIES_CYCLES = 1000;
    public static final int SEQ_CYCLES = 1000;
    public static final int SSC_CYCLES = 1000;
    public static final int SHUFFLED_CYCLES = 1000;
    public static final int STRING_CYCLES = 1000;
    public static final int URL_CYCLES = 1000;
    public static final int US_STATES_CYCLES = 1000;
    public static final int WORDS_CYCLES = 1000;
}
