package net.andreinc.mockneat.types.enums;

import static net.andreinc.mockneat.types.enums.DictType.DOMAIN_TOP_LEVEL_ALL;
import static net.andreinc.mockneat.types.enums.DictType.DOMAIN_TOP_LEVEL_POPULAR;

@SuppressWarnings("ImmutableEnumChecker")
public enum DomainSuffixType {

    ALL(DOMAIN_TOP_LEVEL_ALL), POPULAR(DOMAIN_TOP_LEVEL_POPULAR);

    private final DictType dictType;

    DomainSuffixType(DictType dictType) {
        this.dictType = dictType;
    }

    public DictType getDictType() {
        return dictType;
    }
}
