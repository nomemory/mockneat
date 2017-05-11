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
import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.types.Range;
import net.andreinc.mockneat.types.enums.IPv4Type;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static net.andreinc.mockneat.types.enums.IPv4Type.CLASS_A;
import static net.andreinc.mockneat.types.enums.IPv4Type.CLASS_B;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class IPv4sTest {

    private static final InetAddressValidator IAV = new InetAddressValidator();

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
           fail(e.getMessage());
        }
    }

    protected void testIpCycle(IPv4Type t) {
        loop(Constants.IPV4S_CYCLES, Constants.MOCKS, r -> r.ipv4s().type(t).val(), ip -> testIp(ip, t));
    }

    @Test
    public void testIPv4TypesMethod() throws Exception {
        loop(
                Constants.IPV4S_CYCLES,
                Constants.MOCKS,
                m -> {
                    IPv4Type[] types = { CLASS_A, CLASS_B };
                    String ip = m.ipv4s().types(types).val();
                    assertTrue(IAV.isValidInet4Address(ip));
                }
        );
    }

    @Test
    public void testNextIPv4AddressClassA() throws Exception {
        testIpCycle(CLASS_A);
    }

    @Test
    public void testNextIPv4AddressClassALoopback() throws Exception {
        testIpCycle(IPv4Type.CLASS_A_LOOPBACK);
    }

    @Test
    public void testNextIPv4AddressClassAPrivate() throws Exception {
        testIpCycle(IPv4Type.CLASS_A_PRIVATE);
    }

    @Test
    public void testNextIPv4AddressClassB() throws Exception {
        testIpCycle(CLASS_B);
    }

    @Test
    public void testNextIPv4AddressClassBPrivate() throws Exception {
        testIpCycle(IPv4Type.CLASS_B_PRIVATE);
    }

    @Test
    public void testNextIPv4AddressClassC() throws Exception {
        testIpCycle(IPv4Type.CLASS_C);
    }

    @Test
    public void testNextIPv4AddressClassCPrivate() throws Exception {
        testIpCycle(IPv4Type.CLASS_C_PRIVATE);
    }

    @Test
    public void testNextIPv4AddressClassD() throws Exception {
        testIpCycle(IPv4Type.CLASS_D);
    }

    @Test
    public void testNextIPv4AddressClassE() throws Exception {
        testIpCycle(IPv4Type.CLASS_E);
    }

    @Test
    public void testNextIPv4AddressClassNoConstraint() throws Exception {
        testIpCycle(IPv4Type.NO_CONSTRAINT);
    }

    @Test(expected = NullPointerException.class)
    public void testNextIPv4AddressTypeNotNull() throws Exception {
        Constants.M.ipv4s().type(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextIPv4AddressTypesNotNull() throws Exception {
        IPv4Type[] types = null;
        Constants.M.ipv4s().types(types).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextIPv4AddressTypesNotEmpty() throws Exception {
        Constants.M.ipv4s().types().val();
    }

    @Test
    public void testIPv4AddressesWithINetValidator() {
        loop(Constants.IPV4S_CYCLES, Constants.MOCKS,
            r -> {
                IPv4Type type = r.from(IPv4Type.class).val();
                return r.ipv4s().type(type).val();
            },
            u -> assertTrue(IAV.isValidInet4Address(u)));
    }
}
