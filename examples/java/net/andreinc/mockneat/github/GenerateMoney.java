package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

import static java.util.Locale.GERMANY;
import static java.util.Locale.JAPAN;

/**
 * Created by andreinicolinciobanu on 04/03/2017.
 */
public class GenerateMoney {
    public static void main(String[] args) {
        MockNeat mockNeat = MockNeat.threadLocal();

        String japanFortune = mockNeat.money()
                                      .locale(JAPAN)
                                      .range(100, 200)
                                      .val();

        System.out.println(japanFortune);

        String millions  = mockNeat.money()
                                    .locale(GERMANY)
                                    .range(1000000, 10000000)
                                    .val();
        System.out.println(millions);
    }
}
