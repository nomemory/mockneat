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

import java.time.DayOfWeek;
import java.time.format.TextStyle;

import static java.time.DayOfWeek.*;

public class GenerateDays {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        DayOfWeek day = mock.days().val();
        System.out.println(day);

        String dayStr = mock.days().display(TextStyle.SHORT).val();
        System.out.println(dayStr);

        DayOfWeek dayAfterThursday = mock.days().after(THURSDAY).val();
        System.out.println(dayAfterThursday);

        DayOfWeek dayBeforeThursday = mock.days().before(THURSDAY).val();
        System.out.println(dayBeforeThursday);

        DayOfWeek weekEnd = mock.days().rangeClosed(SATURDAY, SUNDAY).val();
        System.out.println(weekEnd);

    }
}
