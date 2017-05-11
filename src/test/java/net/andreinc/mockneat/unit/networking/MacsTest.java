package net.andreinc.mockneat.unit.networking;

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

import junit.framework.Assert;
import net.andreinc.mockneat.types.enums.MACAddressFormatType;
import org.junit.Test;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MacsTest {

    @Test(expected = NullPointerException.class)
    public void testNextMACAddressTypeNotNull() throws Exception {
        M.macs().type(null).val();
    }

    private void testNextMACAddress(MACAddressFormatType type,
                                      String separator,
                                      Integer macNumLength,
                                      Integer sectionLength) {
        loop(MAC_CYCLES, MOCKS, r -> r.macs().type(type).val(), mac ->
          testMACLength(mac, separator, macNumLength, sectionLength));
    }


    private void testMACLength(String mac, String separator, Integer macNumLength, Integer sectionLength) {
        String[] macNum = mac.split(separator);
        assertTrue(macNum.length==macNumLength);
        stream(macNum).forEach(s -> {
            assertTrue(sectionLength == s.length());
            range(0, s.length()).forEach(i -> {
                try { Integer.parseInt(s.charAt(i)+"", 16); }
                catch (NumberFormatException e) { Assert.fail(); }
            });
        });
    }

    @Test
    public void testDefaultMAC() throws Exception {
        loop(
                MAC_CYCLES,
                MOCKS,
                m -> m.macs().val(),
                mac -> testMACLength(mac, ":", 6, 2)
        );
    }

    @Test
    public void testNextMACAddress_DASH_EVERY_2_DIGITS() throws Exception {
        testNextMACAddress(MACAddressFormatType.DASH_EVERY_2_DIGITS, "-", 6, 2);
    }

    @Test
    public void testNextMACAddress_COLON_EVERY_2_DIGITS() throws Exception {
        testNextMACAddress(MACAddressFormatType.COLON_EVERY_2_DIGITS, ":", 6, 2);
    }

    @Test
    public void testNextMACAddress_DOT_EVERY_2_DIGITS() throws Exception {
        testNextMACAddress(MACAddressFormatType.DOT_EVERY_2_DIGITS, "\\.", 6, 2);
    }

    @Test
    public void testNextMACAddress_DOT_EVERY_4_DIGITS() throws Exception {
        testNextMACAddress(MACAddressFormatType.DOT_EVERY_4_DIGITS, "\\.", 3, 4);
    }
}
