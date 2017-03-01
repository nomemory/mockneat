package com.mockneat.mock.unit.address;

import org.junit.Test;

import static com.mockneat.mock.Constants.COUNTRIES_CYCLES;
import static com.mockneat.mock.Constants.MOCKS;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static org.junit.Assert.assertTrue;

public class CountriesTest {

    @Test
    public void testNextCountryName() throws Exception {
        loop(COUNTRIES_CYCLES,
                MOCKS,
                r -> r.countries().names().val(),
                c -> {
                    assertTrue(isUpperCase(c.charAt(0)));
                    assertTrue(isLowerCase(c.charAt(1)));
                });
    }

    @Test
    public void testNextCountryISO2() throws Exception {
        loop(COUNTRIES_CYCLES,
                MOCKS,
                r -> r.countries().iso2().val(),
                c -> {
                    assertTrue(isUpperCase(c.charAt(0)));
                    assertTrue(isUpperCase(c.charAt(1)));
                });
    }
}
