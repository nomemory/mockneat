package com.mockneat.mock.unit.networking;

import com.mockneat.types.Range;
import com.mockneat.types.enums.IPv4Type;
import junit.framework.Assert;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
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
        loop(IPV4S_CYCLES, MOCKS, r -> r.ipv4s().type(t).val(), ip -> testIp(ip, t));
    }

    @Test
    public void testNextIPv4AddressClassA() throws Exception {
        testIpCycle(IPv4Type.CLASS_A);
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
        testIpCycle(IPv4Type.CLASS_B);
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
        M.ipv4s().type(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextIPv4AddressTypesNotNull() throws Exception {
        IPv4Type[] types = null;
        M.ipv4s().types(types).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextIPv4AddressTypesNotEmpty() throws Exception {
        M.ipv4s().types().val();
    }

    @Test
    public void testIPv4AddressesWithINetValidator() {
        loop(IPV4S_CYCLES, MOCKS,
            r -> {
                IPv4Type type = r.from(IPv4Type.class).val();
                return r.ipv4s().type(type).val();
            },
            u -> assertTrue(IAV.isValidInet4Address(u)));
    }
}
