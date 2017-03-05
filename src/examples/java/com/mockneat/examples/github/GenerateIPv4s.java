package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

import java.util.List;

import static com.mockneat.types.enums.IPv4Type.CLASS_A;
import static com.mockneat.types.enums.IPv4Type.CLASS_B;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateIPv4s {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String ipv4 = mock.ipv4s().val();
        System.out.println(ipv4);

        String ipClassA = mock.ipv4s().type(CLASS_A).val();
        System.out.println(ipClassA);

        String classAorB = mock.ipv4s().types(CLASS_A, CLASS_B).val();
        System.out.println(classAorB);

        List<String> ip4s = mock.ipv4s().list(10).val();
        System.out.println(ip4s);
    }
}
