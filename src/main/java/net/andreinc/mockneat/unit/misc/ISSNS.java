package net.andreinc.mockneat.unit.misc;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.function.Supplier;

import static org.apache.commons.lang3.StringUtils.join;

public class ISSNS extends MockUnitBase implements MockUnitString {

    private static final String ISSN_PREFIX = "ISSN";

    public static ISSNS issns() { return MockNeat.threadLocal().issns(); }

    protected ISSNS() { }

    public ISSNS(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return () -> {
            Integer[] array = mockNeat.ints().range(0, 10).array(7).val();
            int sum = 0;
            for(int i = 0, j = 8; i < array.length; i++, j--) {
                sum += j * array[i];
            }
            int remainder = sum % 11;
            if (remainder != 0) { remainder = 11 - remainder; }
            return ISSN_PREFIX + " " + join(array, "", 0, 4)
                               + "-" + join(array, "", 4, 7)
                               + ((remainder == 10) ? "X" : remainder + "");
        };
    }
}
