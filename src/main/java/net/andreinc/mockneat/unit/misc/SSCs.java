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

import static net.andreinc.mockneat.utils.FormatUtils.prependZeroesToSize;

public class SSCs extends MockUnitBase implements MockUnitString {

    private static SSCs sscs() {
        return MockNeat.threadLocal().sscs();
    }

    protected SSCs() { }

    public SSCs(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * AAA-GG-SSSS
     */
    @Override
    public Supplier<String> supplier() {
        return () -> generateAAA() + "-" + generateGG() + "-" + generateSSSS();
    }

    private String generateAAA() {
        Integer number = mockNeat.ints().range(1, 900).val();
        // "078051120" - Woolworth Wallet Fiasco
        // "219099999" - Used in add
        // 666-xx-xxxx - Doesn't work
        while(number==666 || number==78 || number==219)
            number = mockNeat.ints().range(1, 900).val();
        return prependZeroesToSize(number.toString(), 3);
    }

    private String generateGG() {
        return prependZeroesToSize(mockNeat.ints().range(1, 100).valStr(), 2);
    }

    private String generateSSSS() {
        return prependZeroesToSize(mockNeat.ints().range(1, 10000).valStr(), 4);
    }
}
