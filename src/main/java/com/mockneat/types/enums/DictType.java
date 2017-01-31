package com.mockneat.types.enums;

public enum DictType {

    // COUNTRIES
    COUNTRY_NAME("country-name"),
    COUNTRY_ISO_CODE_2("country-iso-code-2"),

    // DOMAINS
    DOMAIN_EMAIL("domain-email"),
    DOMAIN_TOP_LEVEL_ALL("domain-top-level-all"),
    DOMAIN_TOP_LEVEL_POPULAR("domain-top-level-popular"),
    DOMAIN_COUNTRY("domain-country"),

    // NAMES
    FIRST_NAME_MALE_AMERICAN("first-names-american-male"),
    FIRST_NAME_FEMALE_AMERICAN("first-names-american-female"),

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
    EN_VERB_4SYLL("words/en-verb-4syll");

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    DictType(String fileName) {
        this.fileName = fileName;
    }
}
