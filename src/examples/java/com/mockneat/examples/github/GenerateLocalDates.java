package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

import java.time.LocalDate;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateLocalDates {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        LocalDate localDate = mock.localDates().val();
        System.out.println(localDate);

        LocalDate min = LocalDate.of(1987, 1, 30);
        LocalDate past = mock.localDates().past(min).val();
        System.out.println(past);

        LocalDate max = LocalDate.of(2020, 1, 1);
        LocalDate future = mock.localDates().future(max).val();
        System.out.println(future);

        LocalDate start = LocalDate.of(1989, 1, 1);
        LocalDate stop = LocalDate.of(1993, 1, 1);
        LocalDate between = mock.localDates().between(start, stop).val();
        System.out.println(between);
    }
}
