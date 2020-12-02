package net.andreinc.mockneat.unit.address;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.utils.file.FileManager;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static net.andreinc.mockneat.types.enums.DictType.COUNTRY_ISO_CODE_2;
import static net.andreinc.mockneat.types.enums.DictType.COUNTRY_NAME;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class CountriesTest {

    private final FileManager fm = FileManager.getInstance();

    @Test
    public void testNextCountryName() {
        Set<String> countries =
                new HashSet<>(fm.getLines(COUNTRY_NAME));
        loop(Constants.COUNTRIES_CYCLES,
                Constants.MOCKS,
                r -> r.countries().names().val(),
                c -> {
                    assertTrue(isUpperCase(c.charAt(0)));
                    assertTrue(isLowerCase(c.charAt(1)));
                    assertTrue(countries.contains(c));
                });
    }

    @Test
    public void testNextCountryISO2() {
        Set<String> iso2 =
                new HashSet<>(fm.getLines(COUNTRY_ISO_CODE_2));
        loop(Constants.COUNTRIES_CYCLES,
                Constants.MOCKS,
                r -> r.countries().iso2().val(),
                c -> {
                    assertTrue(isUpperCase(c.charAt(0)));
                    assertTrue(isUpperCase(c.charAt(1)));
                    assertTrue(iso2.contains(c));
                });
    }
}
