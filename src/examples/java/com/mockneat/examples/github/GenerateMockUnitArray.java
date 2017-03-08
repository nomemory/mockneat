package com.mockneat.examples.github;

import com.mockneat.examples.github.model.Test;
import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnit;

/**
 * Created by andreinicolinciobanu on 06/03/2017.
 */
public class GenerateMockUnitArray {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();
        MockUnit<Test> mockUnit = m
                                        .objs(Test.class)
                                        .constructor(
                                            m.strings(),
                                            m.ints(),
                                            m.bools()
                                        );
        Test[] ex = { null };
        Test[] t = m.from(ex).array(Test.class, 10).val();
        for (Test ti : t) {
            System.out.println(ti);
        }
    }
}
