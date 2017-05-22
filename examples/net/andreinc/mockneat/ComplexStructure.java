package net.andreinc.mockneat;

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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComplexStructure {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        Map<String, List<Map<Set<Integer>, List<Integer>>>> result
                = m.ints()
                    // List<Integer>
                    .list(2)
                    // Map<Set<Integer>, List<Integer>>
                    .mapKeys(2, m.ints().set(3)::val)
                    // List<Map<Set<Integer>, List<Integer>>>
                    .list(LinkedList.class, 2)
                    // Map<String, List<Map<Set<Integer>, List<Integer>>>>
                    .mapKeys(4, m.strings()::val)
                    .val();
    }
}
