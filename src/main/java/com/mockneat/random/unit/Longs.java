package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitLong;

import java.util.Random;
import java.util.function.Supplier;

import static com.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 02/01/2017.
 */
public class Longs implements RandUnitLong {

    private Rand rand;
    private Random random;

    public Longs(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    @Override
    public Supplier<Long> supplier() {
        return random::nextLong;
    }

    public RandUnitLong bound(long bound) {
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

    public RandUnitLong range(long lowerBound, long upperBound) {
        notNull(lowerBound, INPUT_PARAMETER_NOT_NULL, "lowerBound");
        notNull(upperBound, INPUT_PARAMETER_NOT_NULL, "upperBound");
        isTrue(lowerBound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);
        Supplier<Long> supplier = () ->
                rand.longs().bound(upperBound - lowerBound).val() + lowerBound;
        return () -> supplier;
    }

    public RandUnitLong from(long[] alphabet) {
        notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<Long> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }

}
