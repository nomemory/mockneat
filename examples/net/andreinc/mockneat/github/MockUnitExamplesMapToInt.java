package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnitInt;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 11/03/2017.
 */
public class MockUnitExamplesMapToInt {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        MockUnitInt zeroOrOne = m.bools()
                                 .mapToInt((b) -> (b) ? 1 : 0);

        List<Integer> list = zeroOrOne.list(5).val();
        System.out.println(list);
    }
}
