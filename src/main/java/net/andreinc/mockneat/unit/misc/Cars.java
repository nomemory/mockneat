package net.andreinc.mockneat.unit.misc;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.SpaceType.*;
import static net.andreinc.mockneat.types.enums.StringFormatType.CAPITALIZED;

public class Cars extends MockUnitBase implements MockUnitString {

    /**
     * <p> Returns a {@code Cars} object that can be used to generate car brands and models.</p>
     *
     * @return A re-usable {@code Cars} instance. The class implements {@code MockUnitString}.
     */
    public static final Cars cars() {
        return MockNeat.threadLocal().cars();
    }

    public Cars(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {

        MockUnitString nameGenerator =
                mockNeat.probabilites(String.class)
                        .add(0.2, mockNeat.space()
                                                .types(PLANETS, GALAXIES, MOONS, CONSTELLATIONS, STARS)
                                .format(CAPITALIZED))
                        .add(0.1, mockNeat.names().firstAndMale())
                        .add(0.1, mockNeat.names().last())
                        .add(0.3, mockNeat.creatures())
                        .add(0.3, mockNeat.names().firstAndFemale())
                        .mapToString();

        MockUnitString prefixGenerator =
                mockNeat.fmt("#{L}#{D}")
                        .param("L", mockNeat.chars().letters().mapToString().format(CAPITALIZED))
                        .param("D", mockNeat.ints().range(0, 999).mapToString());

        MockUnitString modelNameGenerator =
                mockNeat.probabilites(String.class)
                        .add(0.3, prefixGenerator)
                        .add(0.7, nameGenerator)
                        .mapToString();

        return mockNeat.fmt("#{brand} #{model}")
                        .param("brand", brands())
                        .param("model", modelNameGenerator)
                        .supplier();
    }

    /**
     * <p> Returns a {@code MockUnitString} object that can be used to generate only car brands (e.g.: "Honda")</p>
     *
     * @return {@code MockUnitString}.
     */
    public MockUnitString brands() {
        return mockNeat.dicts().type(DictType.CARS_BRANDS);
    }
}
