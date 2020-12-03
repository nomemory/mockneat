package net.andreinc.mockneat.types.enums;

import net.andreinc.mockneat.types.Pair;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

@SuppressWarnings("ImmutableEnumChecker")
public enum HostNameType {
    ADJECTIVE_NOUN(
            unmodifiableList(asList(
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.EN_NOUN_1SYLL),
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.EN_NOUN_2SYLL),
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.EN_NOUN_3SYLL),

                    new Pair<>(DictType.EN_ADJECTIVE_2SYLL, DictType.EN_NOUN_1SYLL),
                    new Pair<>(DictType.EN_ADJECTIVE_2SYLL, DictType.EN_NOUN_2SYLL),
                    new Pair<>(DictType.EN_ADJECTIVE_2SYLL, DictType.EN_NOUN_3SYLL),

                    new Pair<>(DictType.EN_ADJECTIVE_3SYLL, DictType.EN_NOUN_1SYLL),
                    new Pair<>(DictType.EN_ADJECTIVE_3SYLL, DictType.EN_NOUN_2SYLL),
                    new Pair<>(DictType.EN_ADJECTIVE_3SYLL, DictType.EN_NOUN_3SYLL)
            ))
    ),

    ADVERB_VERB(
            unmodifiableList(asList(
                    new Pair<>(DictType.EN_ADVERB_1SYLL, DictType.EN_VERB_1SYLL),
                    new Pair<>(DictType.EN_ADVERB_1SYLL, DictType.EN_VERB_2SYLL),
                    new Pair<>(DictType.EN_ADVERB_1SYLL, DictType.EN_VERB_3SYLL),

                    new Pair<>(DictType.EN_ADVERB_2SYLL, DictType.EN_VERB_1SYLL),
                    new Pair<>(DictType.EN_ADVERB_2SYLL, DictType.EN_VERB_2SYLL),
                    new Pair<>(DictType.EN_ADVERB_2SYLL, DictType.EN_VERB_3SYLL),

                    new Pair<>(DictType.EN_ADVERB_3SYLL, DictType.EN_VERB_1SYLL),
                    new Pair<>(DictType.EN_ADVERB_3SYLL, DictType.EN_VERB_2SYLL),
                    new Pair<>(DictType.EN_ADVERB_3SYLL, DictType.EN_VERB_3SYLL)
            ))
    ),

    ADJECTIVE_FIRST_NAME(
            unmodifiableList(asList(
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(DictType.EN_ADJECTIVE_1SYLL, DictType.FIRST_NAME_FEMALE_AMERICAN),

                    new Pair<>(DictType.EN_ADJECTIVE_2SYLL, DictType.FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(DictType.EN_ADJECTIVE_2SYLL, DictType.FIRST_NAME_FEMALE_AMERICAN),

                    new Pair<>(DictType.EN_ADJECTIVE_3SYLL, DictType.FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(DictType.EN_ADJECTIVE_3SYLL, DictType.FIRST_NAME_FEMALE_AMERICAN)
            ))
    ),

    NOUN_FIRST_NAME(
            unmodifiableList(asList(
                    new Pair<>(DictType.EN_NOUN_1SYLL, DictType.FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(DictType.EN_NOUN_2SYLL, DictType.FIRST_NAME_MALE_AMERICAN),
                    new Pair<>(DictType.EN_NOUN_3SYLL, DictType.FIRST_NAME_MALE_AMERICAN),

                    new Pair<>(DictType.EN_NOUN_1SYLL, DictType.FIRST_NAME_FEMALE_AMERICAN),
                    new Pair<>(DictType.EN_NOUN_2SYLL, DictType.FIRST_NAME_FEMALE_AMERICAN),
                    new Pair<>(DictType.EN_NOUN_3SYLL, DictType.FIRST_NAME_FEMALE_AMERICAN)
            ))
    );

    private final List<Pair<DictType, DictType>> dictCombos;

    HostNameType(List<Pair<DictType, DictType>> dictCombos) {
        this.dictCombos = dictCombos;
    }

    public List<Pair<DictType, DictType>> getDictCombos() {
        return dictCombos;
    }
}
