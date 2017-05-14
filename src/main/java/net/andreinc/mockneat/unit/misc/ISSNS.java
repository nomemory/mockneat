package net.andreinc.mockneat.unit.misc;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.function.Supplier;

import static org.apache.commons.lang3.StringUtils.join;

public class ISSNS extends MockUnitBase implements MockUnitString {

    private static final String ISSN_PREFIX = "ISSN";

    public ISSNS(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return () -> {
            Integer[] array = mockNeat.ints().range(0, 10).array(7).val();
            int sum = 0;
            for(int i = 0, j = 8; i < array.length; i++, j--) {
                sum += j * array[i];
            }
            int remainder = sum % 11;
            if (remainder != 0) { remainder = 11 - remainder; }
            return ISSN_PREFIX + " " + join(array, "", 0, 4)
                               + "-" + join(array, "", 4, 7)
                               + ((remainder == 10) ? "X" : remainder + "");
        };
    }
}
