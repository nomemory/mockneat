package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

import java.time.DayOfWeek;
import java.time.format.TextStyle;

import static java.time.DayOfWeek.*;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
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
