package net.andreinc.mockneat.unit.misc;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnitString;

import java.util.function.Supplier;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * Created by andreinicolinciobanu on 26/03/17.
 */
public class ISSNS implements MockUnitString {

    private static final String ISSN_PREFIX = "ISSN";

    private MockNeat mock;

    public ISSNS(MockNeat mockNeat) {
        this.mock = mockNeat;
    }

    @Override
    public Supplier<String> supplier() {
        return () -> {
            Integer[] array = mock.ints().range(0, 10).array(7).val();
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
