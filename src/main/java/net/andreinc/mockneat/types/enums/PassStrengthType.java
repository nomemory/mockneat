package net.andreinc.mockneat.types.enums;

import net.andreinc.mockneat.types.Range;

@SuppressWarnings("ImmutableEnumChecker")
public enum PassStrengthType {
    WEAK(new Range<>(6, 9)),
    MEDIUM(new Range<>(8, 13)),
    STRONG(new Range<>(12, 15));

    private final Range<Integer> length;

    PassStrengthType(Range<Integer> length) {
        this.length = length;
    }

    public Range<Integer> getLength() {
        return length;
    }
}
