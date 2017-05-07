package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

import static net.andreinc.mockneat.types.enums.DomainSuffixType.ALL;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateDomains {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String domain = mock.domains().val();
        String all = mock.domains().type(ALL).val();

        System.out.println(domain);
        System.out.println(all);
    }
}
