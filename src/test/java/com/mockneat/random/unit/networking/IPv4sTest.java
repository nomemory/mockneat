package com.mockneat.random.unit.networking;

import com.mockneat.types.Range;
import com.mockneat.types.enums.IPv4Type;
import com.mockneat.random.utils.FunctUtils;
import junit.framework.Assert;
import org.junit.Test;


import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static com.mockneat.random.RandTestConstants.IPV4S_CYCLES;
import static com.mockneat.random.RandTestConstants.RAND;
import static com.mockneat.random.RandTestConstants.RANDS;
import static org.junit.Assert.assertTrue;

public class IPv4sTest {
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
        FunctUtils.cycle(IPV4S_CYCLES, () ->
                stream(RANDS).forEach(r -> {
                    String ip = r.ipv4s().type(t).val();
                    testIp(ip, t);
                })
        );
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
        RAND.ipv4s().type(null).val();
    }
}
