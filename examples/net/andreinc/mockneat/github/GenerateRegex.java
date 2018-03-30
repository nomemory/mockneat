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

public class GenerateRegex {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String lolRegex = "LOo{3}L{10,15}!";
        String lol = mock.regex(lolRegex).val();
        System.out.println(lol);

        String numberRegex = "\\d{3,10}";
        String number = mock.regex(numberRegex).val();
        System.out.println(number);

        String codeRegex = "[A-Z]{2}-\\d{5}-[a-z]{5}";
        String code = mock.regex(codeRegex).val();
        System.out.println(code);

        mock.regex("LO{1,15}L!").consume(10, (i, s) -> System.out.println(s));
    }
}
