package com.mockneat.sources.random.rand;

import com.mockneat.types.Range;
import com.mockneat.types.enums.IPv4Type;
import com.mockneat.utils.FunctUtils;
import junit.framework.Assert;
import com.mockneat.types.enums.MACAddressFormatType;
import org.junit.Test;


import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static com.mockneat.sources.random.rand.RandTestConstants.NEXT_NETWORKING_CYCLES;
import static com.mockneat.sources.random.rand.RandTestConstants.RAND;
import static com.mockneat.sources.random.rand.RandTestConstants.RANDS;
import static org.junit.Assert.assertTrue;

public class NextNetworkingTest {


    /**
     * Tests to see if a given String is an IP Address
     *
     * @param ip   The string to test if is an IP Address
     * @param type The class of the ip as a Type
     */
    protected void testIp(String ip, IPv4Type type) {
        Range[] bounds = type.getOctets();
        assertTrue(bounds.length == 4);

        try {
            List<Integer> numbers =
                    stream(ip.split("\\."))
                        .map(Integer::parseInt)
                        .collect(toList());

            assertTrue(numbers.size() == 4);

            IntStream
                    .range(0, bounds.length)
                    .forEach(i -> {
                        Integer low = (Integer) bounds[i].getLowerBound();
                        Integer up = (Integer) bounds[i].getUpperBound();
                        Integer value = numbers.get(i);
                        Assert.assertTrue(value >= low && value <= up);
                    });
        } catch (NumberFormatException e) {
            Assert.fail(e.getMessage());
        }
    }

    protected void testIpCycle(IPv4Type t) {
        FunctUtils.cycle(NEXT_NETWORKING_CYCLES, () ->
                stream(RANDS).forEach(r -> {
                    String ip = r.ipv4s().ofType(t).val();
                    testIp(ip, t);
                })
        );
    }

    /**
     * Tests if different ips from IPv4Type.CLASS_A are generated correctly
     *
     * @throws Exception
     */
    @Test
    public void testNextIPv4AddressClassA() throws Exception {
        testIpCycle(IPv4Type.CLASS_A);
    }

    /**
     * Tests if different ips from IPv4Type.CLASS_A_LOOPBACK are generated correctly
     *
     * @throws Exception
     */
    @Test
    public void testNextIPv4AddressClassALoopback() throws Exception {
        testIpCycle(IPv4Type.CLASS_A_LOOPBACK);
    }

    /**
     * Tests if different ips from IPv4Type.CLASS_A_PRIVATE are generated correctly
     *
     * @throws Exception
     */
    @Test
    public void testNextIPv4AddressClassAPrivate() throws Exception {
        testIpCycle(IPv4Type.CLASS_A_PRIVATE);
    }

    /**
     * Tests if different ips from IPv4Type.CLASS_B are generated correctly
     *
     * @throws Exception
     */
    @Test
    public void testNextIPv4AddressClassB() throws Exception {
        testIpCycle(IPv4Type.CLASS_B);
    }

    /**
     * Tests if different ips from IPv4Type.CLASS_B_PRIVATE are generated correctly
     *
     * @throws Exception
     */
    @Test
    public void testNextIPv4AddressClassBPrivate() throws Exception {
        testIpCycle(IPv4Type.CLASS_B_PRIVATE);
    }

    /**
     * Tests if different ips from IPv4Type.CLASS_C are generated correctly
     *
     * @throws Exception
     */
    @Test
    public void testNextIPv4AddressClassC() throws Exception {
        testIpCycle(IPv4Type.CLASS_C);
    }

    /**
     * Tests if different ips from IPv4Type.CLASS_C_PRIVATE are generated correctly
     *
     * @throws Exception
     */
    @Test
    public void testNextIPv4AddressClassCPrivate() throws Exception {
        testIpCycle(IPv4Type.CLASS_C_PRIVATE);
    }

    /**
     * Tests if different ips from IPv4Type.CLASS_D are generated correctly
     *
     * @throws Exception
     */
    @Test
    public void testNextIPv4AddressClassD() throws Exception {
        testIpCycle(IPv4Type.CLASS_D);
    }

    /**
     * Tests if different ips from IPv4Type.CLASS_E are generated correctly
     *
     * @throws Exception
     */
    @Test
    public void testNextIPv4AddressClassE() throws Exception {
        testIpCycle(IPv4Type.CLASS_E);
    }

    /**
     * Tests if different ips from IPv4Type.NO_CONSTRAINT are generated correctly
     *
     * @throws Exception
     */
    @Test
    public void testNextIPv4AddressClassNoConstraint() throws Exception {
        testIpCycle(IPv4Type.NO_CONSTRAINT);
    }

    /**
     * Test if the method:
     * > String nextIPv4Address(IPv4Type type)
     * Doesn't accept null type
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextIPv4Address_TypeNotNull() throws Exception {
        RAND.ipv4s().ofType(null).val();
    }

    /**
     * Test if the method:
     * > String nextMACAddress(MACAddressFormatType formatType)
     * Doesn't accept null type
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextMACAddress_TypeNotNull() throws Exception {
        RAND.macs().format(null).val();
    }

    protected void testNextMACAddress(MACAddressFormatType type,
                                      String separator,
                                      Integer macNumLength,
                                      Integer sectionLength) {
        FunctUtils.cycle(NEXT_NETWORKING_CYCLES, () -> {
            stream(RANDS)
                    .forEach(r -> {
                        String mac = r.macs().format(type).val();
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

    /**
     * Test if the method:
     * > String nextMACAddress(MACAddressFormatType formatType)
     * Is correctly generated for type: DASH_EVERY_2_DIGITS
     */
    @Test
    public void testNextMACAddress_DASH_EVERY_2_DIGITS() throws Exception {
        testNextMACAddress(MACAddressFormatType.DASH_EVERY_2_DIGITS, "-", 6, 2);
    }

    /**
     * Test if the method:
     * > String nextMACAddress(MACAddressFormatType formatType)
     * Is correctly generated for type: COLON_EVERY_2_DIGITS
     */
    @Test
    public void testNextMACAddress_COLON_EVERY_2_DIGITS() throws Exception {
        testNextMACAddress(MACAddressFormatType.COLON_EVERY_2_DIGITS, ":", 6, 2);
    }

    /**
     * Test if the method:
     * > String nextMACAddress(MACAddressFormatType formatType)
     * Is correctly generated for type: DOT_EVERY_2_DIGITS
     */
    @Test
    public void testNextMACAddress_DOT_EVERY_2_DIGITS() throws Exception {
        testNextMACAddress(MACAddressFormatType.DOT_EVERY_2_DIGITS, "\\.", 6, 2);
    }

    /**
     * Test if the method:
     * > String nextMACAddress(MACAddressFormatType formatType)
     * Is correctly generated for type: DOT_EVERY_4_DIGITS
     */
    @Test
    public void testNextMACAddress_DOT_EVERY_4_DIGITS() throws Exception {
        testNextMACAddress(MACAddressFormatType.DOT_EVERY_4_DIGITS, "\\.", 3, 4);
    }
}
