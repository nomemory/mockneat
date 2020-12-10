package net.andreinc.mockneat.unit.misc;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

public class Creatures extends MockUnitBase implements MockUnitString {

    protected Creatures() {
    }

    public Creatures(MockNeat mockNeat) {
        super(mockNeat);
    }

    public static Creatures creatures() {
        return MockNeat.threadLocal().creatures();
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().type(DictType.CREATURES).supplier();
    }
}
