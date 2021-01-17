package net.andreinc.mockneat.unit.celebrities;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

public class JazzArtists extends MockUnitBase implements MockUnitString {

    /**
     * <p>Returns a {@code JazzArtists} object that can be used to generate arbitrary jazz artists names</p>
     *
     * @return a re-usable {@code JazzArtists} object. The {@code JazzArtists} class implements {@code MockUnitString}
     */
    public static JazzArtists jazzArtists() {
        return MockNeat.threadLocal().jazzArtists();
    }

    public JazzArtists(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * The method returns a {@code Supplier<String>} that can be used to generate jazz artists names (Eg.: "Duke Ellington")
     *
     * @return A new {@code Supplier<String>}
     */
    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts()
                        .type(DictType.JAZZ_ARTISTS)
                        .supplier();
    }
}
