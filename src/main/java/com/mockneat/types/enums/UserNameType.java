package com.mockneat.types.enums;

import com.mockneat.types.Pair;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by andreinicolinciobanu on 18/01/2017.
 */
public enum UserNameType {

    ADJECTIVE_NOUN(
            asList(
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.EN_NOUN_1SYLL),
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.EN_NOUN_2SYLL),
                    new Pair<>(DictType.EN_ADJECTIVE_2SYLL, DictType.EN_NOUN_1SYLL)
            )
    ),

    ADVERB_VERB(
            asList(
                    new Pair<>(DictType.EN_ADVERB_1SYLL, DictType.EN_VERB_1SYLL),
                    new Pair<>(DictType.EN_ADVERB_2SYLL, DictType.EN_VERB_1SYLL),
                    new Pair<>(DictType.EN_ADVERB_1SYLL, DictType.EN_VERB_2SYLL)
            )
    ),

    ADJECTIVE_FIRST_NAME(
            asList(
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.FIRST_NAME_FEMALE_AMERICAN)
            )
    ),

    ADJECTIVE_FIRST_NAME_MALE(
            asList(
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.FIRST_NAME_MALE_AMERICAN)
            )
    ),

    ADJECTIVE_FIRST_NAME_FEMALE(
            asList(
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.FIRST_NAME_FEMALE_AMERICAN)
            )
    );

    private List<Pair<DictType, DictType>> dictCombos;

    UserNameType(List<Pair<DictType, DictType>> dictCombos) {
        this.dictCombos = dictCombos;
    }

    public List<Pair<DictType, DictType>> getDictCombos() {
        return dictCombos;
    }
}
