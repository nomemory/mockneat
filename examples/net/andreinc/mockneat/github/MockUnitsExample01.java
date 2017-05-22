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
import net.andreinc.mockneat.abstraction.MockUnit;

import java.util.ArrayList;
import java.util.List;

public class MockUnitsExample01 {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        // A mockUnit that can generate integers
        MockUnit<Integer> intMockUnit = m.ints().bound(100);
        // A mockUnit that can generate list of integers
        MockUnit<List<Integer>> listMockUnit = intMockUnit.list(100);

        // Generating an integer with the mockUnit
        int integer = intMockUnit.val();

        // Generating a list of integers
        List<Integer> list = listMockUnit.val();

        // Generating another list of integers
        List<Integer> list2 = listMockUnit.val();


        List<Integer[]> listArray = m.ints()
                                     .range(0, 100)
                                     .array(100)
                                     .list(ArrayList.class, 100)
                                     .val();

        List<String> l = m.fmt("{ username: #{user}, email: #{email} }")
         .param("user", m.users())
         .param("email", m.emails().domain("gameloft.com"))
         .list(100)
         .val();
        System.out.println(l);
    }
}
