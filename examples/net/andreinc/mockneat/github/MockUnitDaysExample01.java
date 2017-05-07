package net.andreinc.mockneat.github;


import net.andreinc.mockneat.MockNeat;

import java.time.format.TextStyle;
import java.util.Locale;

import static java.time.DayOfWeek.FRIDAY;

/**
 * Created by andreinicolinciobanu on 11/03/2017.
 */
public class MockUnitDaysExample01 {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        String day = m.days()
                        .after(FRIDAY)
                        .display(TextStyle.FULL_STANDALONE, Locale.FRANCE)
                        .val();

        System.out.println(day);
    }
}
