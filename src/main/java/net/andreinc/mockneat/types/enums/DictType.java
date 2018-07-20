package net.andreinc.mockneat.types.enums;

import net.andreinc.mockneat.utils.file.FileManager;

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

@SuppressWarnings("ImmutableEnumChecker")
public enum DictType {

    // COUNTRIES
    COUNTRY_NAME("country-name"),
    COUNTRY_ISO_CODE_2("country-iso-code-2"),

    // US STATES
    US_STATES("us-states"),
    US_STATES_IS_CODE_2("us-states-iso2"),

    // CITIES
    CITIES_US("cities/cities-us"),
    CITIES_CAPITALS("cities/cities-capitals"),

    // DOMAINS
    DOMAIN_EMAIL("domain-email"),
    DOMAIN_TOP_LEVEL_ALL("domain-top-level-all"),
    DOMAIN_TOP_LEVEL_POPULAR("domain-top-level-popular"),

    // MIME TYPES
    MIME_TYPE("mimetypes"),

    //FINANCIAL
    FOREX_PAIRS("forex-pairs"),
    CREDIT_CARD_NAMES("credit-card-names"),

    // NAMES
    FIRST_NAME_MALE_AMERICAN("first-names-american-male"),
    FIRST_NAME_FEMALE_AMERICAN("first-names-american-female"),
    LAST_NAME_AMERICAN("last-names-american"),

    // WORDS
    EN_ADJECTIVE_1SYLL("words/en-adjective-1syll"),
    EN_ADJECTIVE_2SYLL("words/en-adjective-2syll"),
    EN_ADJECTIVE_3SYLL("words/en-adjective-3syll"),
    EN_ADJECTIVE_4SYLL("words/en-adjective-4syll"),
    EN_ADVERB_1SYLL("words/en-adverb-1syll"),
    EN_ADVERB_2SYLL("words/en-adverb-2syll"),
    EN_ADVERB_3SYLL("words/en-adverb-3syll"),
    EN_ADVERB_4SYLL("words/en-adverb-4syll"),
    EN_NOUN_1SYLL("words/en-noun-1syll"),
    EN_NOUN_2SYLL("words/en-noun-2syll"),
    EN_NOUN_3SYLL("words/en-noun-3syll"),
    EN_NOUN_4SYLL("words/en-noun-4syll"),
    EN_VERB_1SYLL("words/en-verb-1syll"),
    EN_VERB_2SYLL("words/en-verb-2syll"),
    EN_VERB_3SYLL("words/en-verb-3syll"),
    EN_VERB_4SYLL("words/en-verb-4syll"),

    //COMPANIES
    DEPARTMENTS("departments"),

    // NAUGHTY STRINGS (TODO Document)
    NS_CVE_VULNERABILITIES("naughtystrings/cvevulnerabilities"),
    NS_EMOJI("naughtystrings/emoji"),
    NS_FILE_INCLUSIONS("naughtystrings/fileinclusions"),
    NS_INCONOUS_STRINGS("naughtystrings/inconousstrings"),
    NS_JAPANESE_EMOJI("naughtystrings/japaneseemoji"),
    NS_MSDOS_SPECIAL_FILENAMES("naughtystrings/msdosspecialfilenames"),
    NS_NUMERIC("naughtystrings/numeric"),
    NS_QUOTATIONS("naughtystrings/quotations"),
    NS_REGIONAL_INDICATORS("naughtystrings/regionalindicators"),
    NS_RESERVED_KEYWORDS("naughtystrings/reservedkeywords"),
    NS_RIGHT_TO_LEFT_STRINGS("naughtystrings/righttoleftstrings"),
    NS_RUBY_INJECTION("naughtystrings/rubyinjection"),
    NS_SCRIPT_INJECTION("naughtystrings/scriptinjection"),
    NS_SERVER_CODE_INJECTION("naughtystrings/servercodeinjection"),
    NS_SPECIAL_CHARS("naughtystrings/specialchars"),
    NS_SQL_INJECTION("naughtystrings/sqlinjection"),
    NS_TRICK_UNICODE("naughtystrings/trickunicode"),
    NS_TWO_BYTE_CHARS("naughtystrings/twobytechars"),
    NS_UNICODE_FONT("naughtystrings/unicodefont"),
    NS_UNICODE_NUMBERS("naughtystrings/unicodenumbers"),
    NS_UNICODE_SYMBOLS("naughtystrings/unicodesymbols"),
    NS_UNICODE_UPSIDE_DOWN("naughtystrings/unicodeupsidedown"),
    NS_UNICODE_SUBSCRIPT_SUPERSCRIPT("naughtystrings/unicodesubsuperscript"),
    NS_UNWANTED_INTERPOLATION("naughtystrings/unwantedinterpolation"),
    NS_XML_INJECTION("naughtystrings/xmlinjection"),
    NS_ZALGO_TEXT("naughtystrings/zalgotext"),

    // SPACE
    SPACE_CONSTELLATIONS("space/constellations"),
    SPACE_GALAXIES("space/galaxies"),
    SPACE_MOONS("space/moons"),
    SPACE_NEBULAS("space/nebulas"),
    SPACE_PLANETS("space/planets"),
    SPACE_STARS("space/stars")
    ;


    public String getFileName() {
        return fileName;
    }

    public int size() {
        return FileManager.getInstance().getLines(fileName).size();
    }

    private final String fileName;

    DictType(String fileName) {
        this.fileName = fileName;
    }

    public String getFile() {
        return fileName;
    }

}
