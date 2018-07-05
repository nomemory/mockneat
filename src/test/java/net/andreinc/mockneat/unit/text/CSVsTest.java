package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.utils.LoopsUtils;
import org.junit.Assert;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.CSVS_CYCLES;
import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.Constants.MOCKS;

public class CSVsTest {

    @Test
    public void testLine() {
        MockUnitString ms1 = M.fromStrings(new String[]{"ABC\","});
        String line = M.csvs()
                        .addColumn(ms1)
                        .addColumn("A,a")
                        .val();
        Assert.assertTrue(line.equals("\"ABC\"\",\",\"A,a\""));
    }

    @Test
    public void testLineLoop() {
        MockUnit<Boolean> torf = M.bools();
        LoopsUtils.loop(
                CSVS_CYCLES,
                MOCKS,
                m -> {
                    return m.csvs()
                            .addColumn("A")
                            .addColumn(torf)
                            .addColumn(m.names())
                            .val();
                },
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
        M.csvs().addColumn(null).val();
    }
}
