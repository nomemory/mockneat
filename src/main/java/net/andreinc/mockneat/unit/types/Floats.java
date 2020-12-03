package net.andreinc.mockneat.unit.types;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitFloat;

import java.util.Random;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Floats extends MockUnitBase implements MockUnitFloat {

    private final Random random;

    public static Floats floats() {
        return MockNeat.threadLocal().floats();
    }

    protected Floats() {
        this(MockNeat.threadLocal());
    }

    public Floats(MockNeat mockNeat) {
        super(mockNeat);
        this.random = mockNeat.getRandom();
    }

    @Override
    public Supplier<Float> supplier() {
        return random::nextFloat;
    }

    /**
     * <p>Returns a new {@code MockUnitFloat} that can be used to generate random float values in a given range [{@code lowerBound}, {@code upperBound}).</p>
     *
     * @param lowerBound The upper bound of the interval.
     * @param upperBound The lower bound of the interval.
     * @return A new {@code MockUnitFloat}.
     */
    public MockUnitFloat range(float lowerBound, float upperBound) {
        notNull(lowerBound, "lowerBound");
        notNull(upperBound, "upperBound");
        isTrue(lowerBound>=0.0f, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0.0f, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);

        Supplier<Float> supp = () -> random.nextFloat() * (upperBound - lowerBound) + lowerBound;
        return () -> supp;
    }

    /**
     * <p>Returns a new {@code MockUnitFloat} that can be used to generate float values bounded by a specific value: [0, {@code bound}).</p>
     *
     * @param bound The float bound.
     * @return A new {@code MockUnitFloat}.
     */
    public MockUnitFloat bound(float bound) {
        return range(0f, bound);
    }

    /**
     * <p>Returns a new {@code MockUnitFloat} that can be used generate float values from a given "alphabet".</p>
     *
     * @param alphabet An array of values from where the values are randomly picked.
     * @return A new {@code MockUnitFloat}.
     */
    public MockUnitFloat from(float[] alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<Float> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
