package com.mockneat.random;

import org.junit.Test;

import static com.mockneat.utils.FunctUtils.cycle;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.util.Arrays.stream;
import static org.junit.Assert.assertTrue;

public class CountriesTest {

    @Test
    public void testNextCountryName() throws Exception {
        cycle(RandTestConstants.COUNTRIES_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .forEach(r -> {
                        String c = r.countries().names().val();
                        assertTrue(isUpperCase(c.charAt(0)));
                        assertTrue(isLowerCase(c.charAt(1)));
                    }));
    }

    @Test
    public void testNextCountryISO2() throws Exception {
        cycle(RandTestConstants.COUNTRIES_CYCLES, () ->
                stream(RandTestConstants.RANDS)
                    .forEach(r -> {
                        String c = r.countries().iso2().val();
                        assertTrue(isUpperCase(c.charAt(0)));
                        assertTrue(isUpperCase(c.charAt(1)));
                    }));
    }
}
