package net.andreinc.mockneat.types.enums;

import java.util.Arrays;

public enum SpaceType {

    CONSTELLATIONS(new DictType[]{DictType.SPACE_CONSTELLATIONS}),
    GALAXIES(new DictType[]{DictType.SPACE_GALAXIES}),
    MOONS(new DictType[]{DictType.SPACE_MOONS}),
    NEBULAS(new DictType[]{DictType.SPACE_NEBULAS}),
    PLANETS(new DictType[]{DictType.SPACE_PLANETS}),
    STARS(new DictType[]{DictType.SPACE_STARS}),
    ALL(new DictType[]{
            DictType.SPACE_CONSTELLATIONS,
            DictType.SPACE_GALAXIES,
            DictType.SPACE_MOONS,
            DictType.SPACE_NEBULAS,
            DictType.SPACE_PLANETS,
            DictType.SPACE_STARS
    });

    SpaceType(DictType[] dictTypes) {
        this.dictTypes = dictTypes;
    }

    private final DictType[] dictTypes;

    public DictType[] getDictTypes() {
        return Arrays.copyOf(dictTypes, dictTypes.length);
    }
}
