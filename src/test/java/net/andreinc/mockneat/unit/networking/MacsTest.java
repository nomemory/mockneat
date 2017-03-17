package net.andreinc.mockneat.unit.networking;

import junit.framework.Assert;
import net.andreinc.mockneat.types.enums.MACAddressFormatType;
import org.junit.Test;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 05/02/2017.
 */
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
