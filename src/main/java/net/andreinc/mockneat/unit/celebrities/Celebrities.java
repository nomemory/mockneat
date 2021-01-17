package net.andreinc.mockneat.unit.celebrities;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.CelebrityType;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static org.apache.commons.lang3.Validate.notNull;

public class Celebrities extends MockUnitBase implements MockUnitString {

    public static Celebrities celebrities() {
        return MockNeat.threadLocal().celebrities();
    }

    private final JazzArtists jazzArtists;
    private final RockStars rockStars;
    private final USPresidents usPresidents;
    private final UKPrimeMinisters ukPrimeMinisters;
    private final Actors actors;
    private final Actresses actresses;

    public Celebrities(MockNeat mockNeat) {
        super(mockNeat);
        this.rockStars = new RockStars(mockNeat);
        this.usPresidents = new USPresidents(mockNeat);
        this.ukPrimeMinisters = new UKPrimeMinisters(mockNeat);
        this.jazzArtists = new JazzArtists(mockNeat);
        this.actors = new Actors(mockNeat);
        this.actresses = new Actresses(mockNeat);
    }

    /**
     * The method returns a {@code JazzArtists} object that can be used to generate famous Jazz musicians name.
     * @return The {@code JazzArtists} object of the {@code Celebrities} instance.
     */
    public JazzArtists jazzArtists() { return jazzArtists; }

    /**
     * The method returns a {@code RockStars} object that can be used to generate a famous Rock star name.
     * @return The {@code RockStars} object of the {@code Celebrities} instance.
     */
    public RockStars rockStars() {
        return rockStars;
    }

    /**
     * The method returns a {@code UKPrimeMinisters} object that can be used to generate an UK Prime-minister name.
     * @return The {@code UKPrimeMinisters} object of the {@code Celebrities} instance.
     */
    public UKPrimeMinisters ukPrimeMinisters() {
        return ukPrimeMinisters;
    }

    /**
     * The method returns a {@code USPresidents} object that can be used to generate US Presidents names.
     * @return The {@code USPresidents} object of the {@code Celebrities} instance.
     */
    public USPresidents usPresidents() {
        return usPresidents;
    }

    /**
     * The method returns a {@code Actors} object that can be used to generate famous Actors names.
     * @return The {@code Actors} object of the {@code Celebrities} instance.
     */
    public Actors actors() { return actors; }

    /**
     * The method returns a {@code Actresses} object that can be used to generate famous Actresses names.
     * @return The {@code Actresses} object of the {@code Celebrities} instance.
     */
    public Actresses actresses() { return actresses; }

    /**
     * The method returns a random celebrity object of the specified {@code CelebrityType} celebrity type.
     * @param celebrityType The type of the celebrity (e.g.: {@code CelebrityType.ACTORS}, {@code CelebrityType.ROCK_STARS}, etc.)
     * @return The {@code MockUnitString} capable of generating celebrity names based on the specified type.
     */
    public MockUnitString type(CelebrityType celebrityType) {
        notNull(celebrityType, "celebrityType");
        DictType dictType = mockNeat.from(celebrityType.getDictionaries()).val();
        return () -> mockNeat.dicts().type(dictType)::get;
    }

    /**
     * The method returns a random celebrity from the specified types.
     * @param celebrityTypes A var-arg array of {@code CelebrityType} objects.
     * @return The {@code MockUnitString} capable of generating celebrity names based on the specified types.
     */
    public MockUnitString types(CelebrityType... celebrityTypes) {
        notEmptyOrNullValues(celebrityTypes, "celebrityTypes");
        return () -> {
            CelebrityType type = mockNeat.from(celebrityTypes).get();
            return type(type).supplier();
        };
    }

    /**
     * By default the {@code Celebrities} {@code MockUnitString} generates a random celebrity by picking a random {@code CelebrityType}
     * @return A {@code Supplier<String>} that when invoked returns a random celebrity name.
     */
    @Override
    public Supplier<String> supplier() {
        return () -> {
            CelebrityType celebrityType = mockNeat.from(CelebrityType.class).get();
            return type(celebrityType).get();
        };
    }
}
