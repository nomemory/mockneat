package com.mockneat.sources.random.rand;

import com.mockneat.types.enums.StringFormatType;
import com.mockneat.utils.FunctUtils;
import org.junit.Test;

import static com.mockneat.sources.random.rand.RandTestConstants.*;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.util.Arrays.stream;
import static com.mockneat.utils.StringUtils.allLowerCase;
import static com.mockneat.utils.StringUtils.allUppercase;
import static org.junit.Assert.assertTrue;

public class CountriesTest {

    /**
     * Tests if the method:
     * > String name()
     * Always returns a capitalized string.
     * @throws Exception
     */
    @Test
    public void testNextCountryName() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
            stream(RANDS)
                    .forEach(r -> {
                        String c = r.countries().names().val();
                        System.out.println(c);
                        assertTrue(isUpperCase(c.charAt(0)));
                        assertTrue(isLowerCase(c.charAt(1)));
                    }));
    }

    /**
     * Test if the method:
     * > r.names()
     * Returns capitalized Strings
     * @throws Exception
     */
    @Test
    public void streamCountryName() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
            stream(RANDS)
                .map(r -> r.countries()
                                .names()
                                .stream()
                                .limit(COUNTRY_STREAMS_LIMIT))
                .forEach(s ->
                        s.forEach(c -> {
                                    assertTrue(isUpperCase(c.charAt(0)));
                                    assertTrue(isLowerCase(c.charAt(1)));
                        })));
    }

    /**
     * Test if the method:
     * > String name(StringFormatType type)
     * Doesn't accept a null format type
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextCountryNameFailsToNulLFormatType() throws Exception {
        StringFormatType formatType = null;
        RAND.countries().names().format(formatType).val();
    }

    /**
     * Test if the method:
     * > String names(StringFormattype type)
     * Doesn't accept a null format type
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testStreamNextCountryFailsToNulLFormatType() throws Exception {
        StringFormatType formatType = null;
        RAND.countries().names().format(formatType).val();
    }

    /**
     * Test it the method:
     * > String name(StringFormatType type)
     * Returns correctly formatted strings
     * @throws Exception
     */
    @Test
    public void testNextCountryNameUpperCaseFormat() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
            stream(RANDS)
                    .forEach(r -> {
                        String c = r.countries().names().format(StringFormatType.UPPER_CASE).val();
                        assertTrue(allUppercase(c));
                    }));
    }

    /**
     * Tests if the method:
     * > Stream<String> names(StringFormatType type)
     * Returns correct UPPERCASE stream
     * @throws Exception
     */
    @Test
    public void testStreamCountryNameUpperCaseFormat() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
            stream(RANDS)
                .map(r -> r.countries().names().format(StringFormatType.UPPER_CASE).stream().limit(COUNTRY_STREAMS_LIMIT))
                .forEach(s -> s.forEach(c -> assertTrue(allUppercase(c)))));
    }

    /**
     * Test it the method:
     * > String name(StringFormatType type)
     * Returns correctly formatted strings
     * @throws Exception
     */
    @Test
    public void testNextCountryNameLowerCaseFormat() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
                stream(RANDS)
                    .forEach(r -> {
                        String c = r.countries().names().format(StringFormatType.LOWER_CASE).val();
                        assertTrue(allLowerCase(c));
                    }));
    }

    /**
     * Tests if the method:
     * > Stream<String> names(StringFormatType type)
     * Returns correct LOWERCASE stream
     * @throws Exception
     */
    @Test
    public void testStreamCountryNameLowerCaseFormat() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
                stream(RANDS)
                    .map(r -> r.countries().names().format(StringFormatType.LOWER_CASE).stream().limit(COUNTRY_STREAMS_LIMIT))
                    .forEach(s -> s.forEach(c -> assertTrue(allLowerCase(c)))));
    }

    /**
     * Test it the method:
     * > String name(StringFormatType type)
     * Returns correctly formatted strings
     * @throws Exception
     */
    @Test
    public void testNextCountryNameCapitalizedFormat() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
                stream(RANDS)
                    .forEach(r -> {
                        String c = r.countries().names().format(StringFormatType.CAPITALIZED).val();
                        assertTrue(isUpperCase(c.charAt(0)));
                        assertTrue(isLowerCase(c.charAt(1)));
                    }));
    }

    /**
     * Test if the method:
     * > Stream<String> names(StringFormatType type)
     * Returns correctly formatted strings
     * @throws Exception
     */
    @Test
    public void testStreamCountryNameCapitalizedFormat() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
                stream(RANDS)
                    .map(r -> r.countries().names().format(StringFormatType.CAPITALIZED).stream().limit(COUNTRY_STREAMS_LIMIT))
                    .forEach(s -> {
                        s.forEach(c -> {
                            assertTrue(isUpperCase(c.charAt(0)));
                            assertTrue(isLowerCase(c.charAt(1)));
                        });
                    }));
    }

    /**
     * Test if the method:
     * > String nextCountryISO2()
     * Returns a correct val
     * @throws Exception
     */
    @Test
    public void testNextCountryISO2() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
                stream(RANDS)
                    .forEach(r -> {
                        String c = r.countries().iso2().val();
                        assertTrue(isUpperCase(c.charAt(0)));
                        assertTrue(isUpperCase(c.charAt(1)));
                    }));
    }

    /**
     * Tests if the method:
     * > Stream<String> streamCountryISO2()
     * Returns strings correctly formatted
     * @throws Exception
     */
    @Test
    public void testStreamNextCountryISO2() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () -> {
            stream(RANDS)
                    .map(r -> r.countries().iso2().stream().limit(COUNTRY_STREAMS_LIMIT))
                    .forEach(s -> {
                        s.forEach(c -> {
                            assertTrue(isUpperCase(c.charAt(0)));
                            assertTrue(isUpperCase(c.charAt(1)));
                        });
                    });
        });
    }

    /**
     * Test if the method:
     * > String nextCountryISO2(StringFormatType type)
     * Doesn't accept null as a type
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextCountryISO2FailsToNulLFormatType() throws Exception {
        StringFormatType formatType = null;
        RAND.countries().iso2().format(formatType).val();
    }

    /**
     * Test if the method:
     * > Stream<String> streamCountryISO2(StringFormatType type)
     * Doesn't accept null as a type
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testStreamNextCountryISO2FailsToNullFormatType() throws Exception {
        StringFormatType type = null;
        RAND.countries().names().format(type).val();
    }

    /**
     * Test if the method:
     * > String nextCountryISO2(StringFormatType type)
     * Returns correctly formatted strings
     * @throws Exception
     */
    @Test
    public void testNextCountryISO2UpperCaseFormat() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
                stream(RANDS)
                    .forEach(r -> {
                        String c = r.countries().iso2().format(StringFormatType.UPPER_CASE).val();
                        assertTrue(allUppercase(c));
                    }));
    }

    /**
     * Test is the method:
     * Stream<String> streamCountryISO2(StringFormatType formatType)
     * Returns correctly formatted strings
     * @throws Exception
     */
    @Test
    public void testStreamCountryISO2UpperCaseFormat() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () -> {
            stream(RANDS)
                    .map(r -> r.countries().iso2().format(StringFormatType.UPPER_CASE).stream().limit(COUNTRY_STREAMS_LIMIT))
                    .forEach(s ->
                        s.forEach(c -> assertTrue(allUppercase(c))));
        });
    }

    /**
     * Test if the method:
     * > String nextCountryISO2(StringFormatType type)
     * Returns correctly formatted strings
     * @throws Exception
     */
    @Test
    public void testNextCountryISO2LowerCaseFormat() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
            stream(RANDS)
                .forEach(r -> {
                    String c = r.countries().iso2().format(StringFormatType.LOWER_CASE).val();
                    assertTrue(allLowerCase(c));
                }));
    }

    /**
     * Test is the method:
     * Stream<String> streamCountryISO2(StringFormatType formatType)
     * Returns correctly formatted strings
     * @throws Exception
     */
    @Test
    public void testStreamCountryISO2LowerCaseFormat() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () -> {
            stream(RANDS)
                    .map(r -> r.countries().iso2().format(StringFormatType.LOWER_CASE).stream().limit(COUNTRY_STREAMS_LIMIT))
                    .forEach(s ->
                            s.forEach(c -> assertTrue(allLowerCase(c))));
        });
    }


    /**
     * Test if the method:
     * > String nextCountryISO2(StringFormatType type)
     * Returns correctly formatted strings
     * @throws Exception
     */
    @Test
    public void testNextCountryISO2Capitalized() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () ->
            stream(RANDS)
                .forEach(r -> {
                    String c = r.countries().iso2().format(StringFormatType.CAPITALIZED).val();
                    assertTrue(isUpperCase(c.charAt(0)));
                    assertTrue(isLowerCase(c.charAt(1)));
                }));
    }

    /**
     * Test is the method:
     * Stream<String> streamCountryISO2(StringFormatType formatType)
     * Returns correctly formatted strings
     * @throws Exception
     */
    @Test
    public void testStreamCountryISO2Capitalized() throws Exception {
        FunctUtils.cycle(NEXT_COUNTRY_CYCLES, () -> {
            stream(RANDS)
                    .map(r -> r.countries().iso2().format(StringFormatType.CAPITALIZED).stream().limit(COUNTRY_STREAMS_LIMIT))
                    .forEach(s ->
                            s.forEach(c -> {
                                assertTrue(isUpperCase(c.charAt(0)));
                                assertTrue(isLowerCase(c.charAt(1)));
                            }));
        });
    }
}
