package com.mockneat.types.enums;

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
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.mockneat.random.Rand;
import com.mockneat.types.TriConsumer;

public enum MACAddressFormatType {
    DASH_EVERY_2_DIGITS(MACAddressFormatType::line2Digits),
    COLON_EVERY_2_DIGITS(MACAddressFormatType::colon2Digits),
    DOT_EVERY_2_DIGITS(MACAddressFormatType::point2Digits),
    DOT_EVERY_4_DIGITS(MACAddressFormatType::point4Digits);

    private TriConsumer<Integer, StringBuilder, Rand> consumer;

    public TriConsumer<Integer, StringBuilder, Rand> getConsumer() {
        return consumer;
    }

    MACAddressFormatType(TriConsumer<Integer, StringBuilder, Rand> consumer) {
        this.consumer = consumer;
    }

    private static void everyDigits(Integer i, StringBuilder buff, Rand rand, String chr, Integer digits) {
        if (i % digits == 0) buff.append(chr);
        buff.append(Integer.toHexString(rand.ints().range(0, 16).val()));
    }

    private static void line2Digits(Integer i, StringBuilder buff, Rand rand) {
        everyDigits(i, buff, rand, "-", 2);
    }

    private static void colon2Digits(Integer i, StringBuilder buff, Rand rand) {
        everyDigits(i, buff, rand, ":", 2);
    }

    private static void point2Digits(Integer i, StringBuilder buff, Rand rand) {
        everyDigits(i, buff, rand, ".", 2);
    }

    private static void point4Digits(Integer i, StringBuilder buff, Rand rand) {
        everyDigits(i, buff, rand, ".", 4);
    }
}
