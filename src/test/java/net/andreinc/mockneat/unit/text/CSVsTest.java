package net.andreinc.mockneat.unit.text;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.utils.LoopsUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.andreinc.mockneat.Constants.*;

public class CSVsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testLineNoColumns() {
        M.csvs().val();
    }

    @Test(expected = NullPointerException.class)
    public void testWithNulLValues() {
        M.csvs().column(null).val();
    }

    @Test
    public void testLine() {
        MockUnitString ms1 = M.fromStrings(new String[]{"ABC\","});
        String line = M.csvs()
                        .column(ms1)
                        .column("A,a")
                        .val();
        Assert.assertEquals("\"ABC\"\",\",\"A,a\"", line);
    }

    @Test
    public void testCsv() {
        String[] chars = new String[]{ "\"", ",", "'", "|" };
        LoopsUtils.loop(
                CSVS_CYCLES,
                MOCKS,
                m -> m.csvs().column(m.strings().size(5))
                             .column(m.ints().range(0, 10))
                             .column(m.fromStrings(chars).accumulate(5,""))
                             .column(m.fromStrings(chars).accumulate(5,""))
                             .column(m.days())
                             .column(m.names())
                             .column(m.names().firstAndFemale())
                             .separator(",")
                             .accumulate(1000, "\n")
                             .val(),
                csv -> {
                    CSVReader csvReader = new CSVReader(new StringReader(csv));
                    String[] line;
                    try {
                        while ((line = csvReader.readNext()) != null) {
                            Assert.assertEquals(7, line.length);
                        }
                    }
                    catch (CsvValidationException | IOException cve) {
                        cve.printStackTrace();
                        Assert.fail();
                    }
                }
        );
    }

    @Test
    public void testLineLoop() {
        MockUnit<Boolean> torf = M.bools();
        LoopsUtils.loop(
                CSVS_CYCLES,
                MOCKS,
                m ->
                    m.csvs().column("A")
                            .column(torf)
                            .column(m.names())
                            .val()
                ,
                s -> {
                    String[] split = s.split(",");
                    Assert.assertEquals(3, split.length);
                    Assert.assertEquals("A", split[0]);
                    Assert.assertTrue(split[1].equals("true") || split[1].equals("false"));
                }

        );
    }

    @Test(expected = NullPointerException.class)
    public void testWriteCsvNullPath() {
        M.csvs().column(M.strings()).write((Path) null, 10, true);
    }

    @Test(expected = NullPointerException.class)
    public void testWriteCsvNullStringPath() {
        M.csvs().column(M.strings()).write((Path) null, 10, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCsvZeroLinesStringPath() {
        String path = "test.csv";
        M.csvs().column(M.strings()).write(path, 0, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCsvZeroLinesPath() {
        Path path = Paths.get("test.csv");
        M.csvs().column(M.strings()).write(path, 0, true);
    }

    @Test
    public void testCsvWrite() {
        String[] chars = new String[]{ "\"", ",", "'", "|" };
        LoopsUtils.loop(
                CSVS_CYCLES,
                MOCKS,
                m -> {
                    try {
                        Path path = Files.createTempFile("temp" + M.uuids().val(), ".tmp");
                        m.csvs().column(m.strings().size(5))
                                .column(m.ints().range(0, 10))
                                .column(m.fromStrings(chars).accumulate(5,""))
                                .column(m.fromStrings(chars).accumulate(5,""))
                                .column(m.days())
                                .column(m.names())
                                .column(m.names().firstAndFemale())
                                .separator(",")
                                .write(path, 1000);
                        return path;
                    } catch (IOException e) {
                        Assert.fail();
                        throw new UncheckedIOException(e);
                    }
                },
                csvPath -> {
                    try {
                        CSVReader csvReader = new CSVReader(new FileReader(csvPath.toFile()));
                        String[] line;
                        while ((line = csvReader.readNext()) != null) {
                            Assert.assertEquals(7, line.length);
                        }
                        Files.delete(csvPath);
                    } catch (IOException | CsvValidationException ioe) {
                        ioe.printStackTrace();
                        Assert.fail();
                    }
                }
        );
    }
}
