package net.andreinc.mockneat.unit.celebrities;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

public class Actresses extends MockUnitBase implements MockUnitString {

    public Actresses(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().type(DictType.ACTRESSES).supplier();
    }
}
