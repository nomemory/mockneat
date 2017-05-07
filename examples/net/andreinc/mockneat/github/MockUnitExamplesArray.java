package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 11/03/2017.
 */
public class MockUnitExamplesArray {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        String[] names = m.names()
                            .first()
                            .array(String.class, 100)
                            .val();
    }
}
