package net.andreinc.mockneat.unit.celebrities;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.CelebrityType;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static org.apache.commons.lang3.Validate.notNull;

//TODO document
public class Celebrities extends MockUnitBase implements MockUnitString {

    public static Celebrities celebrities() { return MockNeat.threadLocal().celebrities(); }

    private JazzArtists jazzArtists;
    private RockStars rockStars;
    private USPresidents usPresidents;
    private UKPrimeMinisters ukPrimeMinisters;
    private Actors actors;
    private Actresses actresses;

    public Celebrities(MockNeat mockNeat) {
        super(mockNeat);
        this.rockStars = new RockStars(mockNeat);
        this.usPresidents = new USPresidents(mockNeat);
        this.ukPrimeMinisters = new UKPrimeMinisters(mockNeat);
        this.jazzArtists = new JazzArtists(mockNeat);
        this.actors = new Actors(mockNeat);
        this.actresses = new Actresses(mockNeat);
    }

    public JazzArtists jazzArtists() { return jazzArtists; }

    public RockStars rockStars() {
        return rockStars;
    }

    public UKPrimeMinisters ukPrimeMinisters() {
        return ukPrimeMinisters;
    }

    public USPresidents usPresidents() {
        return usPresidents;
    }

    public Actors actors() { return actors; }

    public Actresses actresses() { return actresses; }

    public MockUnitString type(CelebrityType celebrityType) {
        notNull(celebrityType, "celebrityType");
        DictType dictType = mockNeat.from(celebrityType.getDictionaries()).val();
        return () -> mockNeat.dicts().type(dictType)::val;
    }

    public MockUnitString types(CelebrityType... celebrityTypes) {
        notEmptyOrNullValues(celebrityTypes, "celebrityTypes");
        return () -> {
            CelebrityType type = mockNeat.from(celebrityTypes).val();
            return type(type).supplier();
        };
    }

    @Override
    public Supplier<String> supplier() {
        return null;
    }
}
