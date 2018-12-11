package net.andreinc.mockneat;

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
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

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
    public static final int CVVS_CYCLES= 1000;
    public static final int CSVS_CYCLES = 20;
    public static final int DEP_CYCLES = 1000;
    public static final int CITIES_CYCLES= 1000;
    public static final int COUNTRIES_CYCLES = 1000;
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
    public static final int OBJS_CYCLES = 1000;
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
