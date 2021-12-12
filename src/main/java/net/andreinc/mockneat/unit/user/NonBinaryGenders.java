package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

public class NonBinaryGenders extends MockUnitBase implements MockUnitString {

    public static NonBinaryGenders nonBinaryGenders() {
        return MockNeat.threadLocal().nonBinaryGenders();
    }

    public NonBinaryGenders(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().type(DictType.NON_BINARY_GENDERS).supplier();
    }


}
