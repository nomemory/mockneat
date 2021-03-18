package net.andreinc.mockneat.unit.address;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.StringFormatType.CAPITALIZED;

public class Addresses extends MockUnitBase implements MockUnitString {

    private static String[] SUFFIXES = new String[]{
            "Ave",
            "Avenue",
            "St",
            "Street",
            "Blvd",
            "Rd",
            "Road",
            "Hill"
    };

    private static String[] PREFIXES = new String[]{
            "Line",
            "Suite",
            "Apartment",
            "Ap"
    };

    public static Addresses addresses() {
        return MockNeat.threadLocal().addresses();
    }

    public Addresses(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * <p>Returns a {@code MockUnitString} that can be used to generate the address lines</p>
     *
     * @return A new {@code MockUnitString}.
     */
    @Override
    public Supplier<String> supplier() {
        return mockNeat.fmt("#{line1} #{line2}")
                        .param("line1", line1())
                        .param("line2", line2())
                        .supplier();
    }

    /**
     * <p>Returns a {@code MockUnitString} that can be used to generate the first address line with the format:</p>
     * <p>number noun suffix (Ave, Avenue, Street, etc.)</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString line1() {
        return mockNeat.fmt("#{num} #{noun} #{end}")
                .param("num", mockNeat.ints().range(10, 20000))
                .param("noun", mockNeat.words().nouns().format(CAPITALIZED))
                .param("end", mockNeat.from(SUFFIXES));
    }

    /**
     * <p>Returns a {@code MockUnitString} that can be used to generate the second address line with the format:</p>
     * <p>prefix (Line, Suite, Apartment) number</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString line2() {
        return mockNeat.fmt("#{prefix} #{number}")
                                .param("prefix", mockNeat.from(PREFIXES))
                                .param("number", mockNeat.ints().range(1, 1000));
    }
}
