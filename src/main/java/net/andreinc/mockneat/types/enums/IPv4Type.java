package net.andreinc.mockneat.types.enums;

import net.andreinc.mockneat.types.Range;

@SuppressWarnings("ImmutableEnumChecker")
public enum IPv4Type {
    CLASS_A (new Range<>(1, 126), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_A_LOOPBACK (new Range<>(127, 127), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_A_PRIVATE (new Range<>(10, 10), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_A_NONPRIVATE (new Range<>(1, 126), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255), false),
    CLASS_B (new Range<>(128, 191), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_B_PRIVATE (new Range<>(172, 172), new Range<>(16, 31), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_B_NONPRIVATE (new Range<>(128, 191), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255), false),
    CLASS_C (new Range<>(192, 223), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_C_PRIVATE (new Range<>(192, 192), new Range<>(168, 168), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_C_NONPRIVATE (new Range<>(192, 223), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255), false),
    CLASS_D (new Range<>(224, 239), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_E (new Range<>(240, 254), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    NO_CONSTRAINT(new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255));

    private final Range<Integer>[] octets;
    private final boolean privateAllowed;

    IPv4Type(Range<Integer> o1, Range<Integer> o2, Range<Integer> o3, Range<Integer> o4, boolean privateAllowed) {
        //noinspection unchecked
        this.octets = (Range<Integer>[]) new Range[4];
        this.octets[0] = o1;
        this.octets[1] = o2;
        this.octets[2] = o3;
        this.octets[3] = o4;
        this.privateAllowed = privateAllowed;
    }
    
    IPv4Type(Range<Integer> o1, Range<Integer> o2, Range<Integer> o3, Range<Integer> o4){
        this(o1,o2,o3,o4,true);
    }

    public Range<Integer>[] getOctets() {
        return octets;
    }

    public boolean isPrivateAllowed() {
        return privateAllowed;
    }

}
