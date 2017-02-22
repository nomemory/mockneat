package com.mockneat.mock.unit.networking;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.junit.Test;

import static com.mockneat.mock.RandTestConstants.IPV6S_CYCLES;
import static com.mockneat.mock.RandTestConstants.RANDS;
import static com.mockneat.mock.utils.FunctUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 21/02/2017.
 */
public class IPv6sTest {
    private static final InetAddressValidator IAV = new InetAddressValidator();

    @Test
    public void testIPv6AddressesWithINetValidator() {
        loop(IPV6S_CYCLES, RANDS, r -> r.iPv6s().val(), i -> assertTrue(IAV.isValidInet6Address(i)));
    }
}
