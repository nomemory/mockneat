package com.mockneat.types.enums;

import static com.mockneat.types.enums.DictType.DOMAIN_TOP_LEVEL_ALL;
import static com.mockneat.types.enums.DictType.DOMAIN_TOP_LEVEL_POPULAR;

/**
 * Created by andreinicolinciobanu on 16/02/2017.
 */
public enum DomainSuffixType {

    ALL(DOMAIN_TOP_LEVEL_ALL), POPULAR(DOMAIN_TOP_LEVEL_POPULAR);

    DomainSuffixType(DictType dictType) {
        this.dictType = dictType;
    }
    private DictType dictType;

    public DictType getDictType() {
        return dictType;
    }
}
