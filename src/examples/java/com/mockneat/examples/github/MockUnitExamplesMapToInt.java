package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitInt;

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
