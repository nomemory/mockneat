package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.github.model.Test;
import net.andreinc.mockneat.abstraction.MockUnit;

/**
 * Created by andreinicolinciobanu on 06/03/2017.
 */
public class GenerateMockUnitArray {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();
        MockUnit<Test> mockUnit = m
                                        .constructor(Test.class)
                                        .params(
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
