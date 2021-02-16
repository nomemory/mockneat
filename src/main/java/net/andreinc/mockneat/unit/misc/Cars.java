package net.andreinc.mockneat.unit.misc;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.SpaceType;
import net.andreinc.mockneat.types.enums.StringFormatType;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.SpaceType.*;
import static net.andreinc.mockneat.types.enums.StringFormatType.CAPITALIZED;

public class Cars extends MockUnitBase implements MockUnitString {

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
                        .add(0.5, mockNeat.space()
                                                .types(PLANETS, GALAXIES, MOONS, CONSTELLATIONS, STARS)
                                .format(CAPITALIZED))
                        .add(0.1, mockNeat.names().firstAndMale())
                        .add(0.4, mockNeat.names().firstAndFemale())
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

    public MockUnitString brands() {
        return mockNeat.dicts().type(DictType.CARS_BRANDS);
    }
}
