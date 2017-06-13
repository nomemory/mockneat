package net.andreinc.mockneat.types.enums;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.types.Range;

@SuppressWarnings("ImmutableEnumChecker")
public enum IPv4Type {
    CLASS_A (new Range<>(1, 126), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_A_LOOPBACK (new Range<>(127, 127), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_A_PRIVATE (new Range<>(10, 10), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_B (new Range<>(128, 191), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_B_PRIVATE (new Range<>(172, 172), new Range<>(16, 31), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_C (new Range<>(192, 223), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_C_PRIVATE (new Range<>(192, 192), new Range<>(168, 168), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_D (new Range<>(224, 239), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    CLASS_E (new Range<>(240, 254), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255)),
    NO_CONSTRAINT(new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255), new Range<>(0, 255));

    private final Range<Integer>[] octets;

    IPv4Type(Range<Integer> o1, Range<Integer> o2, Range<Integer> o3, Range<Integer> o4) {
        this.octets = (Range<Integer>[]) new Range[4];
        this.octets[0] = o1;
        this.octets[1] = o2;
        this.octets[2] = o3;
        this.octets[3] = o4;
    }

    public Range<Integer>[] getOctets() {
        return octets;
    }


}
