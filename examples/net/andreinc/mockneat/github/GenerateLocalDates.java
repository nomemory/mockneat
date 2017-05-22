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

import java.time.LocalDate;

public class GenerateLocalDates {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        // Generates a random date between [1970-1-1, NOW)
        LocalDate localDate = mock.localDates().val();
        System.out.println(localDate);

        // Generates a random date in the past
        // but beore 1987-1-30
        LocalDate min = LocalDate.of(1987, 1, 30);
        LocalDate past = mock.localDates().past(min).val();
        System.out.println(past);

        LocalDate max = LocalDate.of(2020, 1, 1);
        LocalDate future = mock.localDates().future(max).val();
        System.out.println(future);

        // Generates a random date between 1989-1-1 and 1993-1-1
        LocalDate start = LocalDate.of(1989, 1, 1);
        LocalDate stop = LocalDate.of(1993, 1, 1);
        LocalDate between = mock.localDates().between(start, stop).val();
        System.out.println(between);
    }
}
