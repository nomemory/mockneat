package com.mockneat.types.enums;

import com.mockneat.types.Range;

/**
 * Created by andreinicolinciobanu on 20/12/2016.
 */
public enum IPv4Type {
    CLASS_A (new Range(1, 126), new Range(0, 255), new Range(0, 255), new Range(0, 255)),
    CLASS_A_LOOPBACK (new Range(127, 127), new Range(0, 255), new Range(0, 255), new Range(0, 255)),
    CLASS_A_PRIVATE (new Range(10, 10), new Range(0, 255), new Range(0, 255), new Range(0, 255)),
    CLASS_B (new Range(128, 191), new Range(0, 255), new Range(0, 255), new Range(0, 255)),
    CLASS_B_PRIVATE (new Range(172, 172), new Range(16, 31), new Range(0, 255), new Range(0, 255)),
    CLASS_C (new Range(192, 223), new Range(0, 255), new Range(0, 255), new Range(0, 255)),
    CLASS_C_PRIVATE (new Range(192, 192), new Range(168, 168), new Range(0, 255), new Range(0, 255)),
    CLASS_D (new Range(224, 239), new Range(0, 255), new Range(0, 255), new Range(0, 255)),
    CLASS_E (new Range(240, 254), new Range(0, 255), new Range(0, 255), new Range(0, 255)),
    NO_CONSTRAINT(new Range(0, 255), new Range(0, 255), new Range(0, 255), new Range(0, 255));

    private Range<Integer>[] octets;

    public Range<Integer>[] getOctets() {
        return octets;
    }

    IPv4Type(Range<Integer>... ocs) {
        this.octets = ocs;
    }
}
