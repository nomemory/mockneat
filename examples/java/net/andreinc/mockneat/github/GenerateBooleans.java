package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateBooleans {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();
        boolean b = m.bools().val();
        boolean almostAlwaysTrue = m.bools().probability(99.99).val();
    }
}
