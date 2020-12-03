package net.andreinc.mockneat.unit.networking;

import net.andreinc.mockneat.Constants;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.junit.Test;

import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class IPv6sTest {
    private static final InetAddressValidator IAV = new InetAddressValidator();

    @Test
    public void testIPv6AddressesWithINetValidator() {
        loop(Constants.IPV6S_CYCLES,
                Constants.MOCKS,
                r -> r.iPv6s().val(),
                i -> assertTrue(IAV.isValidInet6Address(i)));
    }
}
