package net.andreinc.mockneat.github;


import net.andreinc.mockneat.MockNeat;

import java.time.DayOfWeek;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by andreinicolinciobanu on 10/03/2017.
 */
public class GenerateFrom {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        List<Double> list = asList(0.0, 0.1, 0.5, 0.6);
        Double x = m.from(list).val();

        System.out.println(x);

        String[] arr = {"abc", "acd", "adf" };
        String s = m.from(arr).val();

        System.out.println(s);

        DayOfWeek d = m.from(DayOfWeek.class).val();
        System.out.println(d);
    }
}
