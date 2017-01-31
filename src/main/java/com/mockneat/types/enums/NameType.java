package com.mockneat.types.enums;

/**
 * Created by andreinicolinciobanu on 16/01/2017.
 */
public enum NameType {

    FIRST_NAME(new DictType[]{
            DictType.FIRST_NAME_MALE_AMERICAN,
            DictType.FIRST_NAME_FEMALE_AMERICAN
    }),
    FIRST_NAME_MALE(new DictType[]{
            DictType.FIRST_NAME_MALE_AMERICAN
    }),
    FIRST_NAME_FEMALE(new DictType[]{
            DictType.FIRST_NAME_FEMALE_AMERICAN
    }),
    FIRST_NAME_MALE_AMERICAN(new DictType[]{
            DictType.FIRST_NAME_MALE_AMERICAN
    }),
    FIRST_NAME_FEMALE_AMERICAN(new DictType[]{
            DictType.FIRST_NAME_FEMALE_AMERICAN
    });

    NameType(DictType[] dictionaries) {
        this.dictionaries = dictionaries;
    }

    private DictType[] dictionaries;

    public DictType[] getDictionaries() {
        return dictionaries;
    }
}
