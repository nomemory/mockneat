package net.andreinc.mockneat.unit.misc;


import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.DictType.MIME_TYPE;

public class Mimes extends MockUnitBase implements MockUnitString {

    public static Mimes mimes() {
        return MockNeat.threadLocal().mimes();
    }

    protected Mimes() {
    }

    public Mimes(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().type(MIME_TYPE).supplier();
    }
}
