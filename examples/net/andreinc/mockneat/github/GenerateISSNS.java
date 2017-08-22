package andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 21/08/17.
 */
public class GenerateISSNS {
    public static void main(String[] args) {
        MockNeat mockNeat = MockNeat.threadLocal();

        String issn  = mockNeat.issns().val();
        System.out.println(issn);
    }
}
