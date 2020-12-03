package net.andreinc.mockneat.unit.address;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

public class USStates extends MockUnitBase implements MockUnitString {

    public static USStates usStates() {
        return MockNeat.threadLocal().usStates();
    }

    protected USStates() { }

    public USStates(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * The method returns a {@code MockUnitString} that can be used to generate US state names (Eg.: "Indiana")
     * @return A new {@code MockUnitString}
     */
    @Override
    public Supplier<String> supplier() {
        return mockNeat
                .dicts()
                .type(DictType.US_STATES)
                .supplier();
    }

    /**
     * The method returns a {@code MockUnitString} that can be used to generate US State ISO2 names (Eg.: "LA")
     * @return A new {@code MockUnitString}
     */
    public MockUnitString iso2() {
        return () -> mockNeat
                        .dicts()
                        .type(DictType.US_STATES_IS_CODE_2)
                        .supplier();
    }
}
