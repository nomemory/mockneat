package net.andreinc.mockneat.unit.misc;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

public class Creatures extends MockUnitBase implements MockUnitString {

    /**
     * <p> Returns a {@code Creatures} object that can be used to generate arbitrary creature names.</p>
     *
     * @return A re-usable {@code Creatures} instance. The class implements {@code MockUnitString}.
     */
    public static Creatures creatures() {
        return MockNeat.threadLocal().creatures();
    }

    public Creatures(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().type(DictType.CREATURES).supplier();
    }
}
