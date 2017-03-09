package com.mockneat.examples.github;

import com.mockneat.examples.github.model.Test;
import com.mockneat.mock.MockNeat;

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
    }
}
