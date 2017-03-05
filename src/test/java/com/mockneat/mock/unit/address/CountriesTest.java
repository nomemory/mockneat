package com.mockneat.mock.unit.address;

import com.mockneat.mock.utils.file.FileManager;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mockneat.mock.Constants.COUNTRIES_CYCLES;
import static com.mockneat.mock.Constants.MOCKS;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.types.enums.DictType.COUNTRY_ISO_CODE_2;
import static com.mockneat.types.enums.DictType.COUNTRY_NAME;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static org.junit.Assert.assertTrue;

public class CountriesTest {

    private final FileManager fm = FileManager.getInstance();

    @Test
    public void testNextCountryName() throws Exception {
        Set<String> countries =
                new HashSet<>(fm.getLines(COUNTRY_NAME));
        loop(COUNTRIES_CYCLES,
                MOCKS,
                r -> r.countries().names().val(),
                c -> {
                    assertTrue(isUpperCase(c.charAt(0)));
                    assertTrue(isLowerCase(c.charAt(1)));
                    assertTrue(countries.contains(c));
                });
    }

    @Test
    public void testNextCountryISO2() throws Exception {
        Set<String> iso2 =
                new HashSet<>(fm.getLines(COUNTRY_ISO_CODE_2));
        loop(COUNTRIES_CYCLES,
                MOCKS,
                r -> r.countries().iso2().val(),
                c -> {
                    assertTrue(isUpperCase(c.charAt(0)));
                    assertTrue(isUpperCase(c.charAt(1)));
                    assertTrue(iso2.contains(c));
                });
    }
}
