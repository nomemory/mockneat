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
import net.andreinc.mockneat.abstraction.MockUnitInt;

import java.util.List;

public class GenerateInts {
    public static void main(String[] args) {

        // Internally uses a ThreadLocalRandom implementation
        MockNeat mock = MockNeat.threadLocal();
        Integer x = mock.ints().range(100, 200).val();
        System.out.println(x);


        Integer i1 = mock.ints().val();
        System.out.println(i1);

        Integer bounded = mock.ints().bound(10).val();
        System.out.println(bounded);

        Integer ranged = mock.ints().range(10, 20).val();
        System.out.println(ranged);

        MockNeat mockNeat = MockNeat.threadLocal();
        MockUnitInt intUnit = mockNeat.ints().range(0, 100);

        int[] arr1 = intUnit.arrayPrimitive(100).val();
        Integer[] arr2 = intUnit.array(100).val();
        List<Integer> list1 = intUnit.list(100).val();
        List<List<Integer>> list2 = intUnit.list(100).list(100).val();
    }
}
