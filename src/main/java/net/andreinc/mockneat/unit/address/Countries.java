package net.andreinc.mockneat.unit.address;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.DictType.COUNTRY_ISO_CODE_2;
import static net.andreinc.mockneat.types.enums.DictType.COUNTRY_NAME;

public class Countries extends MockUnitBase implements MockUnitString {

    public static Countries countries() {
        return MockNeat.threadLocal().countries();
    }

    public Countries(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * The method returns a new {@code MockUnitString} that can be used to generate country names. (Eg.: "Romania")
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString names() {
        return mockNeat.dicts().type(COUNTRY_NAME);
    }

    /**
     * The method returns a new {@code MockUnitString} that can be used to generate country ISO2 codes. (Eg.: "RO")
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString iso2() {
        return mockNeat.dicts().type(COUNTRY_ISO_CODE_2);
    }

    /**
     * @return A supplier {@code Supplier<String>} that when it's called returns an arbitrary country name. (Eg.: "France")
     */
    @Override
    public Supplier<String> supplier() {
        return names().supplier();
    }
}
