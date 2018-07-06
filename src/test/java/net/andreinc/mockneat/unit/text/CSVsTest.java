package net.andreinc.mockneat.unit.text;

import com.opencsv.CSVReader;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.utils.LoopsUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static net.andreinc.mockneat.Constants.*;

public class CSVsTest {

    @Test
    public void testLine() {
        MockUnitString ms1 = M.fromStrings(new String[]{"ABC\","});
        String line = M.csvs()
                        .column(ms1)
                        .column("A,a")
                        .val();
        Assert.assertTrue(line.equals("\"ABC\"\",\",\"A,a\""));
    }

    @Test
    public void testCsv() {
        String[] chars = new String[]{ "\"", ",", "'", "|" };
        LoopsUtils.loop(
                CVVS_CYCLES,
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
                            Assert.assertTrue(line.length == 7);
                        }
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
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
                    Assert.assertTrue(split.length == 3);
                    Assert.assertTrue(split[0].equals("A"));
                    Assert.assertTrue(split[1].equals("true") || split[1].equals("false"));
                }

        );
    }

    @Test(expected = NullPointerException.class)
    public void testLineNullMockUnit() throws Exception {
        M.csvs().column(null).val();
    }

    @Test
    public void testWriteCsv() {

    }
}
