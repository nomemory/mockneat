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
 */s

import net.andreinc.mockneat.MockNeat;

import java.util.HashMap;
import java.util.Map;

public class GenerateProbabilities {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        Map<String, Integer> mp = new HashMap<>();
        mp.put("A", 0);
        mp.put("B", 0);
        mp.put("C", 0);
        mp.put("D", 0);

        for (int i = 0; i < 1000000; i++) {

            String s = m.probabilites(String.class)
                    .add(0.1, "A")
                    .add(0.2, "B")
                    .add(0.5, "C")
                    .add(0.2, "D")
                    .val();
            mp.put(s, mp.get(s) + 1);
        }

        System.out.println(mp);

        Integer x = m.probabilites(Integer.class)
                            .add(0.2, m.ints().range(0, 100))
                            .add(0.5, m.ints().range(100, 200))
                            .add(0.3, m.ints().range(200, 300))
                            .val();

        System.out.println(x);
    }
}
