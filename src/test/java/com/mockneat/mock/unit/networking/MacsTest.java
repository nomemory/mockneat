package com.mockneat.mock.unit.networking;

import com.mockneat.types.enums.MACAddressFormatType;
import junit.framework.Assert;
import org.junit.Test;

import static com.mockneat.mock.Constants.IPV4S_CYCLES;
import static com.mockneat.mock.Constants.M;
import static com.mockneat.mock.Constants.MOCKS;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 05/02/2017.
 */
public class MacsTest {

    @Test(expected = NullPointerException.class)
    public void testNextMACAddressTypeNotNull() throws Exception {
        M.macs().type(null).val();
    }

    protected void testNextMACAddress(MACAddressFormatType type,
                                      String separator,
                                      Integer macNumLength,
                                      Integer sectionLength) {
        loop(IPV4S_CYCLES, MOCKS, r -> r.macs().type(type).val().split(separator), macNum -> {
            assertTrue(macNum.length==macNumLength);
            stream(macNum).forEach(s -> {
                assertTrue(sectionLength == s.length());
                range(0, s.length()).forEach(i -> {
                    try { Integer.parseInt(s.charAt(i)+"", 16); }
                    catch (NumberFormatException e) { Assert.fail(); }
                });
            });
        });
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
