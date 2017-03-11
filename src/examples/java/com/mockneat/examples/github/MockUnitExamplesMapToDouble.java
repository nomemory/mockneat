package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitDouble;

/**
 * Created by andreinicolinciobanu on 11/03/2017.
 */
public class MockUnitExamplesMapToDouble {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        MockUnitDouble md = m.bools()
                    .mapToDouble((bool) -> {
                        if (bool)
                            return m.doubles().range(1.0, 2.0).val();
                        return m.doubles().range(2.0, 3.0).val();
                    });

        Double d = md.val();

        System.out.println(d);
    }
}
