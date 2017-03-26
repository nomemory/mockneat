package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 26/03/17.
 */
public class GenerateCountries {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        String countryName = m.countries().names().val();
        System.out.println(countryName);

        String iso2 = m.countries().iso2().val();
        System.out.println(iso2);
    }
}
