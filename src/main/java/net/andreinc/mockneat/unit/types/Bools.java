package net.andreinc.mockneat.unit.types;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitBase;

import java.util.Random;
import java.util.function.Supplier;

import static org.apache.commons.lang3.Validate.inclusiveBetween;

public class Bools extends MockUnitBase implements MockUnit<Boolean> {

    private final Random random;

    public static Bools bools() {
        return MockNeat.threadLocal().bools();
    }

    public Bools(MockNeat mockNeat) {
        super(mockNeat);
        this.random = mockNeat.getRandom();
    }

    /**
     * <p>Returns a new {@code MockUnit<Boolean>} that returns arbitrary boolean values with a given probability.</p>
     *
     * @param probability The probability to obtain {@code true}. (Eg.: If the probability is 99.99, the {@code MockUnit<Boolean>} will generate {@code true} in 99.99% of the cases.
     * @return A new {@code MockUnit<Boolean>}
     */
    public MockUnit<Boolean> probability(double probability) {
        inclusiveBetween(0.0, 100.0, probability);
        Supplier<Boolean> supp = () -> mockNeat.doubles()
                                            .range(0.0, 100.0)
                                            .val() < probability;
        return () -> supp;
    }

    /**
     * <p>Returns the internal {@code Supplier<Boolean>} that is used to generate boolean values.</p>
     *
     * @return A {@code Supplier<Boolean>} used to generate arbitrary {@code Boolean} values.
     */
    @Override
    public Supplier<Boolean> supplier() {
        return random::nextBoolean;
    }
}

