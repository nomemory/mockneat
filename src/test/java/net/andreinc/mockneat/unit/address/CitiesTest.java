package net.andreinc.mockneat.unit.address;

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

    private final Set<String> allCapitalCitiesEurope = new HashSet<>(fm.getLines(DictType.CITIES_CAPITALS_EUROPE));


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

    @Test
    public void testCapitalsEurope() {
        loop(
                Constants.CITIES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.cities().capitalsEurope().get(),
                capitalCity -> {
                    Assert.assertTrue(StringUtils.isNotEmpty(capitalCity));
                    Assert.assertTrue(allCapitalCitiesEurope.contains(capitalCity));
                }
        );
    }
}
