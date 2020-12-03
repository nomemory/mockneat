package net.andreinc.mockneat.unit.types;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitLong;

import java.util.Random;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Longs extends MockUnitBase implements MockUnitLong {

    private final Random random;

    public static Longs longs() {
        return MockNeat.threadLocal().longs();
    }

    protected Longs() {
        this(MockNeat.threadLocal());
    }

    public Longs(MockNeat mockNeat) {
        super(mockNeat);
        this.random = mockNeat.getRandom();
    }

    @Override
    public Supplier<Long> supplier() {
        return random::nextLong;
    }

    /**
     * <p>This method returns a {@code MockUnitLong} that can be used to generate arbitrary numbers in the [0, bound) interval.</p>
     *
     * @param bound The interval's bound.
     * @return A new {@code MockUnitLong}.
     */
    public MockUnitLong bound(Long bound) {
        isTrue(bound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        Supplier<Long> supplier = () -> {
            long b;
            long result;
            do {
                b = (random.nextLong() << 1) >>> 1;
                result = b % bound;
            } while (b-result+bound-1 < 0L);

            return result;
        };
        return () -> supplier;
    }

    /**
     * <p>This method returns a {@code MockUnitLong} that can be used to generate arbitrary numbers in the [0, bound) interval.</p>
     *
     * @param upperBound The interval's bound.
     * @return A new {@code MockUnitLong}.
     */
    //TODO document
    public MockUnitLong upperBound(long upperBound) {
        return bound(upperBound);
    }

    /**
     * <p> This method can be used to generate arbitrary long value in [lowerBound, Long.MAX_VALUE]</p>
     *
     * @param lowerBound The lower bound. Should be different than Long.MAX_VALUE and bigger than 0.
     * @return A new {@code MockUnitLong}
     */
    //TODO document
    public MockUnitLong lowerBound(long lowerBound) {
        notNull(lowerBound, "lowerBound");
        isTrue(lowerBound >= 0, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(lowerBound != Integer.MAX_VALUE, LOWER_BOUND_DIFFERENT_THAN_LONG_MAX);
        return rangeClosed(lowerBound, Long.MAX_VALUE);
    }

    /**
     * <p>This method returns a {@code MockUnitLong} that can be used generate arbitrary numbers in the given range: [loweBound, upperBound)</p>
     *
     * @param lowerBound The lower bound of the interval.
     * @param upperBound The upper bound of the interval.
     * @return A new {@code MockUnitLong}.
     */
    public MockUnitLong range(Long lowerBound, Long upperBound) {
        notNull(lowerBound, "lowerBound");
        notNull(upperBound, "upperBound");
        isTrue(lowerBound >= 0, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound > 0, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound > lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);
        Supplier<Long> supplier = () ->
                mockNeat.longs().bound(upperBound - lowerBound).val() + lowerBound;
        return () -> supplier;
    }

    /**
     * <p>This method return a {@code MockUnitLong} that can be used to generate arbitrary numbers in the given closed range: [lowerBound, upperBound]</p>
     *
     * @param lowerBound The lower bound of the interval.
     * @param upperBound The upper bound of the interval.
     *
     * @return A new {@code MockUnitLong}.
     */
    //TODO document
    public MockUnitLong rangeClosed(long lowerBound, long upperBound) {
        notNull(lowerBound, "lowerBound");
        notNull(upperBound, "upperBound");
        isTrue(lowerBound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);
        Supplier<Long> supp = () -> mockNeat.longs().bound(upperBound - lowerBound + 1).val() + lowerBound;
        return () -> supp;
    }

    /**
     * <p>This method returns a {@code MockUnitLong} that can be used to generate arbitrary long numbers from the given alphabet.</p>
     *
     * @param alphabet The alphabet from which the values are selected.
     * @return A new {@code MockUnitLong}.
     */
    public MockUnitLong from(long[] alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<Long> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }

}
