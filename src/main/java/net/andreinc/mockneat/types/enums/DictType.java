package net.andreinc.mockneat.types.enums;

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

public enum DictType {

    // COUNTRIES
    COUNTRY_NAME("country-name"),
    COUNTRY_ISO_CODE_2("country-iso-code-2"),

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
    DEPARTMENTS("departments");

    private final String fileName;

    DictType(String fileName) {
        this.fileName = fileName;
    }

    public String getFile() {
        return fileName;
    }

}
