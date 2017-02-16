package com.mockneat.types.enums;

import com.mockneat.types.Pair;

import java.util.List;

import static com.mockneat.types.enums.DictType.*;
import static java.util.Arrays.asList;

/**
 * Created by andreinicolinciobanu on 16/02/2017.
 */
public enum HostNameType {
    ADJECTIVE_NOUN(
            asList(
                    new Pair<>(EN_ADJECTIVE_1SYLL, EN_NOUN_1SYLL),
                    new Pair<>(EN_ADJECTIVE_1SYLL, EN_NOUN_2SYLL),
                    new Pair<>(EN_ADJECTIVE_1SYLL, EN_NOUN_3SYLL),

                    new Pair<>(EN_ADJECTIVE_2SYLL, EN_NOUN_1SYLL),
                    new Pair<>(EN_ADJECTIVE_2SYLL, EN_NOUN_2SYLL),
                    new Pair<>(EN_ADJECTIVE_2SYLL, EN_NOUN_3SYLL),

                    new Pair<>(EN_ADJECTIVE_3SYLL, EN_NOUN_1SYLL),
                    new Pair<>(EN_ADJECTIVE_3SYLL, EN_NOUN_2SYLL),
                    new Pair<>(EN_ADJECTIVE_3SYLL, EN_NOUN_3SYLL)
            )
    ),

    ADVERB_VERB(
            asList(
                    new Pair<>(EN_ADVERB_1SYLL, EN_VERB_1SYLL),
                    new Pair<>(EN_ADVERB_1SYLL, EN_VERB_2SYLL),
                    new Pair<>(EN_ADVERB_1SYLL, EN_VERB_3SYLL),

                    new Pair<>(EN_ADVERB_2SYLL, EN_VERB_1SYLL),
                    new Pair<>(EN_ADVERB_2SYLL, EN_VERB_2SYLL),
                    new Pair<>(EN_ADVERB_2SYLL, EN_VERB_3SYLL),

                    new Pair<>(EN_ADVERB_3SYLL, EN_VERB_1SYLL),
                    new Pair<>(EN_ADVERB_3SYLL, EN_VERB_2SYLL),
                    new Pair<>(EN_ADVERB_3SYLL, EN_VERB_3SYLL)
            )
    ),

    ADJECTIVE_FIRST_NAME(
            asList(
                    new Pair<>(EN_ADJECTIVE_1SYLL, FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(EN_ADJECTIVE_1SYLL, FIRST_NAME_FEMALE_AMERICAN),

                    new Pair<>(EN_ADJECTIVE_2SYLL, FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(EN_ADJECTIVE_2SYLL, FIRST_NAME_FEMALE_AMERICAN),

                    new Pair<>(EN_ADJECTIVE_3SYLL, FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(EN_ADJECTIVE_3SYLL, FIRST_NAME_FEMALE_AMERICAN)
            )
    ),

    NOUN_FIRST_NAME(
            asList(
                    new Pair<>(EN_NOUN_1SYLL, FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(EN_NOUN_2SYLL, FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(EN_NOUN_3SYLL, FIRST_NAME_MALE_AMERICAN),

                    new Pair<>(EN_NOUN_1SYLL, FIRST_NAME_FEMALE_AMERICAN),
                    new Pair<>(EN_NOUN_2SYLL, FIRST_NAME_FEMALE_AMERICAN),
                    new Pair<>(EN_NOUN_3SYLL, FIRST_NAME_FEMALE_AMERICAN)
            )
    );

    private List<Pair<DictType, DictType>> dictCombos;

    HostNameType(List<Pair<DictType, DictType>> dictCombos) {
        this.dictCombos = dictCombos;
    }

    public List<Pair<DictType, DictType>> getDictCombos() {
        return dictCombos;
    }
}
