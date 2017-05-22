package net.andreinc.mockneat.github;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.github.model.Test;
import net.andreinc.mockneat.github.model.Test2;
import net.andreinc.mockneat.github.model.TestFactory;

import java.util.List;

public class GenerateObjs {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        Test t = mock.reflect(Test.class)
                        .field("x", mock.strings().size(10))
                        .field("y", mock.ints().range(100, 200))
                        .field("z", mock.bools())
                        .val();

        System.out.println(t);

        List<Test> tR = mock.reflect(Test.class)
                        .useDefaults(true)
                        .list(5)
                        .val();

        System.out.println("tR: " + tR);

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

        Test2 t4 = mock.reflect(Test2.class)
                        .useDefaults(true)
                        .type(String.class, mock.strings().size(5))
                        .type(Short.class, (short) 5)
                        .val();

        System.out.println(t4);
    }

}
