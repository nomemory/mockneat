package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

import java.time.Month;

import static java.time.Month.AUGUST;
import static java.time.Month.JUNE;

/**
 * Created by andreinicolinciobanu on 04/03/2017.
 */
public class GenerateMonths {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        Month m = mock.months().val();
        System.out.println(m);

        Month summer = mock.months().rangeClosed(JUNE, AUGUST).val();
        System.out.println(summer);

        Month beforeSummer = mock.months().before(JUNE).val();
        System.out.println(beforeSummer);
    }
}
