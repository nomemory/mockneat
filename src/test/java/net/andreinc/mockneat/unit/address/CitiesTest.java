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

    private final Set<String> allCapitalCitiesAfrica = new HashSet<>(fm.getLines(DictType.CITIES_CAPITALS_AFRICA));

    private final Set<String> allCapitalCitiesAsia = new HashSet<>(fm.getLines(DictType.CITIES_CAPITALS_ASIA));

    private final Set<String> allCapitalCitiesAmerica = new HashSet<>(fm.getLines(DictType.CITIES_CAPITALS_AMERICA));

    private final Set<String> allCapitalCitiesAustraliaAndOceania = new HashSet<>(fm.getLines(DictType.CITIES_CAPITALS_AUSTRALIA_AND_OCEANIA));

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

    @Test
    public void testCapitalsAfrica() {
        loop(
                Constants.CITIES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.cities().capitalsAfrica().get(),
                capitalCity -> {
                    Assert.assertTrue(StringUtils.isNotEmpty(capitalCity));
                    Assert.assertTrue(allCapitalCitiesAfrica.contains(capitalCity));
                }
        );
    }

    @Test
    public void testCapitalsAsia() {
        loop(
                Constants.CITIES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.cities().capitalsAsia().get(),
                capitalCity -> {
                    Assert.assertTrue(StringUtils.isNotEmpty(capitalCity));
                Assert.assertTrue(allCapitalCitiesAsia.contains(capitalCity));
                }
        );
    }

    @Test
    public void testCapitalsAmerica() {
        loop(
                Constants.CITIES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.cities().capitalsAmerica().get(),
                capitalCity -> {
                    Assert.assertTrue(StringUtils.isNotEmpty(capitalCity));
                    Assert.assertTrue(allCapitalCitiesAmerica.contains(capitalCity));
                }
        );
    }

    @Test
    public void testCapitalsAustraliaAndOceania() {
        loop(
                Constants.CITIES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.cities().capitalsAustraliaAndOceania().get(),
                capitalCity -> {
                    Assert.assertTrue(StringUtils.isNotEmpty(capitalCity));
                    Assert.assertTrue(allCapitalCitiesAustraliaAndOceania.contains(capitalCity));
                }
        );
    }
}
