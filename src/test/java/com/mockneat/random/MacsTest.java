package com.mockneat.random;

import com.mockneat.types.enums.MACAddressFormatType;
import com.mockneat.utils.FunctUtils;
import junit.framework.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 05/02/2017.
 */
public class MacsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNextMACAddress_TypeNotNull() throws Exception {
        RandTestConstants.RAND.macs().format(null).val();
    }

    protected void testNextMACAddress(MACAddressFormatType type,
                                      String separator,
                                      Integer macNumLength,
                                      Integer sectionLength) {
        FunctUtils.cycle(RandTestConstants.IPV4S_CYCLES, () -> {
            stream(RandTestConstants.RANDS)
                    .forEach(r -> {
                        String mac = r.macs().type(type).val();
                        String[] macNum = mac.split(separator);

                        assertTrue(macNum.length==macNumLength);

                        stream(macNum).forEach(s -> {
                            assertTrue(sectionLength == s.length());
                            IntStream
                                    .range(0, s.length())
                                    .forEach(i -> {
                                        try { Integer.parseInt(s.charAt(i)+"", 16); }
                                        catch (NumberFormatException e) { Assert.fail(); }
                                    });
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

    public void testNextMACAddress_DOT_EVERY_2_DIGITS() throws Exception {
        testNextMACAddress(MACAddressFormatType.DOT_EVERY_2_DIGITS, "\\.", 6, 2);
    }

    @Test
    public void testNextMACAddress_DOT_EVERY_4_DIGITS() throws Exception {
        testNextMACAddress(MACAddressFormatType.DOT_EVERY_4_DIGITS, "\\.", 3, 4);
    }
}
