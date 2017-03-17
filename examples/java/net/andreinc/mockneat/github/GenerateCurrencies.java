package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateCurrencies {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        System.out.println(mock.currencies().name().val());

        System.out.println(mock.currencies().symbol().val());

        System.out.println(mock.currencies().code().val());

        System.out.println(mock.currencies().forexPair().val());
    }
}

