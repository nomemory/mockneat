package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateIPv6s {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String ipv6 = mock.iPv6s().val();
        System.out.println(ipv6);
    }
}
