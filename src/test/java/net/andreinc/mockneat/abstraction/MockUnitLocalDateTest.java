package net.andreinc.mockneat.abstraction;

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
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.*;

public class MockUnitLocalDateTest {

    @Test
    public void testToUtilDateNullValue() {
        LocalDate[] localDates = { null };
        MockUnitLocalDate mockUnitLocalDate = () -> () -> M.from(localDates).val();
        Date date = mockUnitLocalDate.mapToDate().val();
        assertNull(date);
    }

    @Test
    public void testToUtilDate() {

        final LocalDate start = LocalDate.of(1990, 1, 1);
        final LocalDate stop = LocalDate.of(2000, 1, 1);
        final Date startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        final Date stopDate = Date.from(stop.atStartOfDay(ZoneId.systemDefault()).toInstant());

        loop(
                Constants.LOCAL_DATES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.localDates().between(start, stop).mapToDate().val(),
                utilDate -> assertFalse(utilDate.before(startDate) || utilDate.after(stopDate))
        );
    }

    @Test(expected = NullPointerException.class)
    public void testDisplayNullFormat() {
        String fmt = null;
        M.localDates().display(fmt);
    }

    @Test(expected = NullPointerException.class)
    public void testDisplayNullFormatOverloaded() {
        String fmt = null;
        M.localDates().display(fmt, Locale.ENGLISH);
    }

    @Test(expected = NullPointerException.class)
    public void testDisplayNullLocale() {
        M.localDates().display("", null);
    }

    @Test
    public void testDisplayInvalidFormat() {
        String fmt = M.localDates().display("123").val();
        assertEquals("123", fmt);
    }

    @Test
    public void testDisplayInvalidFormat2() {
        String fmt = M.localDates().display("").val();
        assertEquals("", fmt);
    }

    @Test
    public void testDisplay() {
        final LocalDate start = LocalDate.of(1990, 1, 1);
        final LocalDate stop = LocalDate.of(2000, 1, 1);
        loop(
                Constants.LOCAL_DATES_CYCLES,
                Constants.MOCKS,
                mock -> mock.localDates().between(start, stop).display("yyyy:MM:dd").val(),
                date -> {
                    String[] split = date.split(":");

                    assertEquals(3, split.length);

                    int year = Integer.parseInt(split[0]);
                    int month = Integer.parseInt(split[1]);
                    int day = Integer.parseInt(split[2]);

                    assertTrue(1990 <= year && year < 2000);
                    assertTrue( 1 <= month && month <= 12);
                    assertTrue( 1<= day && day <= 31);
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testDisplayWithDtfNullDtf() {
        DateTimeFormatter dtf = null;
        M.localDates().display(dtf, Locale.GERMAN);
    }

    @Test(expected = NullPointerException.class)
    public void testDisplayWithOnlyDtfNullDtf() {
        DateTimeFormatter dtf = null;
        M.localDates().display(dtf);
    }

    @Test(expected = NullPointerException.class)
    public void testDisplayWithDtfNullLocale() {
        M.localDates().display(DateTimeFormatter.BASIC_ISO_DATE, null);
    }

    @Test
    public void testDisplayDtf() {
        final LocalDate start = LocalDate.of(1990, 1, 1);
        final LocalDate stop = LocalDate.of(2000, 1, 1);
        loop(
                Constants.LOCAL_DATES_CYCLES,
                Constants.MOCKS,
                mock -> {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy:MM:dd");
                    return mock.localDates().between(start, stop).display(dtf).val();
                },
                date -> {
                    String[] split = date.split(":");

                    assertEquals(3, split.length);

                    int year = Integer.parseInt(split[0]);
                    int month = Integer.parseInt(split[1]);
                    int day = Integer.parseInt(split[2]);

                    assertTrue(1990 <= year && year < 2000);
                    assertTrue( 1 <= month && month <= 12);
                    assertTrue( 1<= day && day <= 31);
                }
        );
    }
}
