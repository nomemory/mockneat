package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 11/03/2017.
 */
public class MockUnitExamplesValStr {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        String alwaysTrue = m.bools().probability(100.0).valStr();

        String nullll = m.from(new String[]{ null, null, null})
                             .valStr("NULLLL");
        System.out.println(nullll);
    }
}
