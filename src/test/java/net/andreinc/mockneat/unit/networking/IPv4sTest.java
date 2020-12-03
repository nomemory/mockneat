package net.andreinc.mockneat.unit.networking;

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.types.Range;
import net.andreinc.mockneat.types.enums.IPv4Type;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.junit.Test;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static net.andreinc.mockneat.types.enums.IPv4Type.CLASS_A;
import static net.andreinc.mockneat.types.enums.IPv4Type.CLASS_B;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.*;

public class IPv4sTest {

    private static final InetAddressValidator IAV = new InetAddressValidator();

    protected void testIp(String ip, IPv4Type type) {
        
        if(stream(new IPv4Type[] {IPv4Type.CLASS_A_PRIVATE, IPv4Type.CLASS_B_PRIVATE, IPv4Type.CLASS_C_PRIVATE}).anyMatch(x -> x == type)) {
            try {
                assertTrue(isPrivate(ip));
            }catch(UnknownHostException e) {
                fail(e.getMessage());
            }
        }
        
        if(stream(new IPv4Type[] {IPv4Type.CLASS_A_NONPRIVATE, IPv4Type.CLASS_B_NONPRIVATE, IPv4Type.CLASS_C_NONPRIVATE}).anyMatch(x -> x == type)) {
            try {
                assertFalse(isPrivate(ip));
            }catch(UnknownHostException e) {
                fail(e.getMessage());
            }
        }
        
        
        Range<Integer>[] bounds = type.getOctets();
        assertEquals(4, bounds.length);

        try {
            List<Integer> numbers =
                    stream(ip.split("\\."))
                        .map(Integer::parseInt)
                        .collect(toList());

            assertEquals(4, numbers.size());

            IntStream
                    .range(0, bounds.length)
                    .forEach(i -> {
                        Integer low = bounds[i].getLowerBound();
                        Integer up = bounds[i].getUpperBound();
                        Integer value = numbers.get(i);
                        assertTrue(value >= low && value <= up);
                    });
        } catch (NumberFormatException e) {
           fail(e.getMessage());
        }
    }

    protected void testIpCycle(IPv4Type t) {
        loop(Constants.IPV4S_CYCLES, Constants.MOCKS, r -> r.ipv4s().type(t).val(), ip -> testIp(ip, t));
    }
    
    protected boolean isPrivate(String ip) throws UnknownHostException {
        Inet4Address addr = (Inet4Address) Inet4Address.getByName(ip);
        return addr.isSiteLocalAddress();
    }

    @Test
    public void testIPv4TypesMethod() {
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
    public void testNextIPv4AddressClassA() {
        testIpCycle(CLASS_A);
    }

    @Test
    public void testNextIPv4AddressClassALoopback() {
        testIpCycle(IPv4Type.CLASS_A_LOOPBACK);
    }

    @Test
    public void testNextIPv4AddressClassAPrivate() {
        testIpCycle(IPv4Type.CLASS_A_PRIVATE);
    }

    @Test
    public void testNextIPv4AddressClassB() {
        testIpCycle(CLASS_B);
    }

    @Test
    public void testNextIPv4AddressClassBPrivate() {
        testIpCycle(IPv4Type.CLASS_B_PRIVATE);
    }

    @Test
    public void testNextIPv4AddressClassC() {
        testIpCycle(IPv4Type.CLASS_C);
    }

    @Test
    public void testNextIPv4AddressClassCPrivate() {
        testIpCycle(IPv4Type.CLASS_C_PRIVATE);
    }

    @Test
    public void testNextIPv4AddressClassD() {
        testIpCycle(IPv4Type.CLASS_D);
    }

    @Test
    public void testNextIPv4AddressClassE() {
        testIpCycle(IPv4Type.CLASS_E);
    }

    @Test
    public void testNextIPv4AddressClassNoConstraint() {
        testIpCycle(IPv4Type.NO_CONSTRAINT);
    }

    @Test(expected = NullPointerException.class)
    public void testNextIPv4AddressTypeNotNull() {
        Constants.M.ipv4s().type(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextIPv4AddressTypesNotNull() {
        Constants.M.ipv4s().types((IPv4Type[]) null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextIPv4AddressTypesNotEmpty() {
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
