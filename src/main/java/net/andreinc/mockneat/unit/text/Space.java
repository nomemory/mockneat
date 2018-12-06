package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.SpaceType;

import java.util.function.Supplier;

//TODO document
public class Space extends MockUnitBase implements MockUnitString {

    public static Space space() {
        return MockNeat.threadLocal().space();
    }

    protected Space() {
    }

    public Space(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().types().supplier();
    }

    public MockUnitString type(SpaceType spaceType) {
        DictType[] types = spaceType.getDictTypes();
        return () -> mockNeat.dicts().types(types).supplier();
    }

    public MockUnitString types(SpaceType... spaceTypes) {
        return () -> {
            SpaceType spaceType = mockNeat.from(spaceTypes).val();
            return type(spaceType).supplier();
        };
    }

    public MockUnitString constellations() {
        return type(SpaceType.CONSTELLATIONS);
    }

    public MockUnitString galaxies() {
        return type(SpaceType.GALAXIES);
    }

    public MockUnitString moons() {
        return type(SpaceType.NEBULAS);
    }

    public MockUnitString planets() {
        return type(SpaceType.PLANETS);
    }

    public MockUnitString stars() {
        return type(SpaceType.STARS);
    }
}
