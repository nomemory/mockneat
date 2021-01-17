package net.andreinc.mockneat.unit.celebrities;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

public class Actresses extends MockUnitBase implements MockUnitString {

    /**
     * <p>Returns a {@code Actresses} object that can be used to generate arbitrary actresses names (e.g.: Elizabeth Taylor)</p>
     *
     * @return a re-usable {@code Actresses} instance. This class implements {@code MockUnitString}
     */
    public static Actresses actresses() {
        return MockNeat.threadLocal().actresses();
    }

    public Actresses(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * The method returns a {@code Supplier<String>} that can be used to generate full actress names (Eg.: Tilda Swinton")
     *
     * @return A new {@code Supplier<String>}
     */
    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts()
                        .type(DictType.ACTRESSES)
                        .supplier();
    }
}
