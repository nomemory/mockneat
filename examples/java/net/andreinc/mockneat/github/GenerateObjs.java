package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.github.model.Test;
import net.andreinc.mockneat.github.model.TestFactory;

/**
 * Created by andreinicolinciobanu on 04/03/2017.
 */
public class GenerateObjs {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        Test t = mock.reflect(Test.class)
                        .field("x", mock.strings().size(10))
                        .field("y", mock.ints().range(100, 200))
                        .field("z", mock.bools())
                        .val();

        System.out.println(t);

        Test t2 = mock.constructor(Test.class)
                        .params(
                            mock.strings().size(10),
                            mock.ints().range(0, 10),
                            mock.bools()
                        )
                        .val();

        System.out.println(t2);

        Test t3 = mock.factory(Test.class, TestFactory.class)
                      .method("buildTest")
                      .params(mock.strings(), 1, true)
                      .val();

        System.out.println(t3);
    }
}
