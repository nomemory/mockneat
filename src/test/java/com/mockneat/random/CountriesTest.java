package com.mockneat.random;

import com.mockneat.types.enums.StringFormatType;
import com.mockneat.utils.FunctUtils;
import org.junit.Test;

import static com.mockneat.random.RandTestConstants.RAND;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.util.Arrays.stream;
import static com.mockneat.utils.StringUtils.allLowerCase;
import static com.mockneat.utils.StringUtils.allUppercase;
import static org.junit.Assert.assertTrue;

public class CountriesTest {

    @Test
    public void testNextCountryName() throws Exception {
        FunctUtils.cycle(RandTestConstants.COUNTRIES_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .forEach(r -> {
                        String c = r.countries().names().val();
                        assertTrue(isUpperCase(c.charAt(0)));
                        assertTrue(isLowerCase(c.charAt(1)));
                    }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextCountryNameFailsToNulLFormatType() throws Exception {
        StringFormatType formatType = null;
        RAND.countries().names().format(formatType).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStreamNextCountryFailsToNulLFormatType() throws Exception {
        StringFormatType formatType = null;
        RAND.countries().names().format(formatType).val();
    }

    @Test
    public void testNextCountryNameUpperCaseFormat() throws Exception {
        FunctUtils.cycle(RandTestConstants.COUNTRIES_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .forEach(r -> {
                        String c = r.countries().names().format(StringFormatType.UPPER_CASE).val();
                        assertTrue(allUppercase(c));
                    }));
    }

    @Test
    public void testNextCountryNameLowerCaseFormat() throws Exception {
        FunctUtils.cycle(RandTestConstants.COUNTRIES_CYCLES, () ->
                stream(RandTestConstants.RANDS)
                    .forEach(r -> {
                        String c = r.countries().names().format(StringFormatType.LOWER_CASE).val();
                        assertTrue(allLowerCase(c));
                    }));
    }

    @Test
    public void testNextCountryNameCapitalizedFormat() throws Exception {
        FunctUtils.cycle(RandTestConstants.COUNTRIES_CYCLES, () ->
                stream(RandTestConstants.RANDS)
                    .forEach(r -> {
                        String c = r.countries().names().format(StringFormatType.CAPITALIZED).val();
                        assertTrue(isUpperCase(c.charAt(0)));
                        assertTrue(isLowerCase(c.charAt(1)));
                    }));
    }

    @Test
    public void testNextCountryISO2() throws Exception {
        FunctUtils.cycle(RandTestConstants.COUNTRIES_CYCLES, () ->
                stream(RandTestConstants.RANDS)
                    .forEach(r -> {
                        String c = r.countries().iso2().val();
                        assertTrue(isUpperCase(c.charAt(0)));
                        assertTrue(isUpperCase(c.charAt(1)));
                    }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextCountryISO2FailsToNulLFormatType() throws Exception {
        StringFormatType formatType = null;
        RAND.countries().iso2().format(formatType).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStreamNextCountryISO2FailsToNullFormatType() throws Exception {
        StringFormatType type = null;
        RAND.countries().names().format(type).val();
    }

    @Test
    public void testNextCountryISO2UpperCaseFormat() throws Exception {
        FunctUtils.cycle(RandTestConstants.COUNTRIES_CYCLES, () ->
                stream(RandTestConstants.RANDS)
                    .forEach(r -> {
                        String c = r.countries().iso2().format(StringFormatType.UPPER_CASE).val();
                        assertTrue(allUppercase(c));
                    }));
    }

    @Test
    public void testNextCountryISO2LowerCaseFormat() throws Exception {
        FunctUtils.cycle(RandTestConstants.COUNTRIES_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                .forEach(r -> {
                    String c = r.countries().iso2().format(StringFormatType.LOWER_CASE).val();
                    assertTrue(allLowerCase(c));
                }));
    }

    @Test
    public void testNextCountryISO2Capitalized() throws Exception {
        FunctUtils.cycle(RandTestConstants.COUNTRIES_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                .forEach(r -> {
                    String c = r.countries().iso2().format(StringFormatType.CAPITALIZED).val();
                    assertTrue(isUpperCase(c.charAt(0)));
                    assertTrue(isUpperCase(c.charAt(1)));
                }));
    }

}
