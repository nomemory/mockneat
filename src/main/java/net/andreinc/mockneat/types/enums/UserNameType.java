package net.andreinc.mockneat.types.enums;

import net.andreinc.mockneat.types.Pair;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

@SuppressWarnings("ImmutableEnumChecker")
public enum UserNameType {

    ADJECTIVE_NOUN(
            unmodifiableList(asList(
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.EN_NOUN_1SYLL),
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.EN_NOUN_2SYLL),
                    new Pair<>(DictType.EN_ADJECTIVE_2SYLL, DictType.EN_NOUN_1SYLL)
            ))
    ),

    ADVERB_VERB(
            unmodifiableList(asList(
                    new Pair<>(DictType.EN_ADVERB_1SYLL, DictType.EN_VERB_1SYLL),
                    new Pair<>(DictType.EN_ADVERB_2SYLL, DictType.EN_VERB_1SYLL),
                    new Pair<>(DictType.EN_ADVERB_1SYLL, DictType.EN_VERB_2SYLL)
            ))
    ),

    ADJECTIVE_FIRST_NAME(
            unmodifiableList(asList(
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.FIRST_NAME_FEMALE_AMERICAN)
            ))
    ),

    ADJECTIVE_LAST_NAME(
            unmodifiableList(asList(
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.LAST_NAME_AMERICAN),
                    new Pair<>(DictType.EN_ADJECTIVE_2SYLL, DictType.LAST_NAME_AMERICAN)
            ))
    ),

    ADJECTIVE_FIRST_NAME_MALE(
            Collections.singletonList(
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.FIRST_NAME_MALE_AMERICAN)
            )
    ),

    ADJECTIVE_FIRST_NAME_FEMALE(
            Collections.singletonList(
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.FIRST_NAME_FEMALE_AMERICAN)
            )
    );

    private final List<Pair<DictType, DictType>> dictCombos;

    UserNameType(List<Pair<DictType, DictType>> dictCombos) {
        this.dictCombos = dictCombos;
    }

    public List<Pair<DictType, DictType>> getDictCombos() {
        return dictCombos;
    }
}
