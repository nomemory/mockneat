package net.andreinc.mockneat.abstraction;

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
        M.localDates().display((String) null);
    }

    @Test(expected = NullPointerException.class)
    public void testDisplayNullFormatOverloaded() {
        M.localDates().display((String) null, Locale.ENGLISH);
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
        M.localDates().display((DateTimeFormatter) null, Locale.GERMAN);
    }

    @Test(expected = NullPointerException.class)
    public void testDisplayWithOnlyDtfNullDtf() {
        M.localDates().display((DateTimeFormatter) null);
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
