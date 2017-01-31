package com.mockneat.types.enums;

import com.mockneat.types.Range;

/**
 * Created by andreinicolinciobanu on 22/12/2016.
 */
public enum PassStrengthType {
    WEAK_PASSWORD(new Range(6, 9)),
    MEDIUM_PASSWORD(new Range(8, 13)),
    STRONG_PASSWORD(new Range(12, 15));

    private Range<Integer> length;

    PassStrengthType(Range length) {
        this.length = length;
    }

    public Range<Integer> getLength() {
        return length;
    }
}
