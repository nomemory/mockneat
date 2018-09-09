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
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.utils.file.FileManager;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static net.andreinc.mockneat.utils.LoopsUtils.loop;

public class CitiesTest {

    private final FileManager fm =
            FileManager.getInstance();

    private final Set<String> allUsCities = new HashSet<>(fm.getLines(DictType.CITIES_US));

    private final Set<String> allCapitalCities = new HashSet<>(fm.getLines(DictType.CITIES_CAPITALS));


    @Test
    public void testUsCity() {
        loop(
                Constants.CITIES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.cities().us().val(),
                usCity -> {
                    Assert.assertTrue(StringUtils.isNotEmpty(usCity));
                    Assert.assertTrue(allUsCities.contains(usCity));
                }
        );
    }

    @Test
    public void testCapitalCity() {
        loop(
                Constants.CITIES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.cities().capitals().val(),
                capitalCity -> {
                    Assert.assertTrue(StringUtils.isNotEmpty(capitalCity));
                    Assert.assertTrue(allCapitalCities.contains(capitalCity));
                }
        );
    }
}
