package net.andreinc.mockneat.unit.celebrities;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

public class Actors extends MockUnitBase implements MockUnitString {

    /**
     * <p>Returns a {@code Actors} object that can be used to generate arbitrary actor names (e.g.: Michael Douglas)</p>
     *
     * @return a re-usable {@code Actors} instance. This class implements {@code MockUnitString}
     */
    public static Actors actors() {
        return MockNeat.threadLocal().actors();
    }

    public Actors(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * The method returns a {@code Supplier<String>} that can be used to generate full actor names (Eg.: "Adrien Brody")
     *
     * @return A new {@code Supplier<String>}
     */
    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts()
                        .type(DictType.ACTORS)
                        .supplier();
    }
}
