package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitInt;

import java.util.Random;
import java.util.function.Supplier;

import static com.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class Ints implements RandUnitInt {

    private Random random;

    public Ints(Rand rand) {
        this.random = rand.getRandom();
    }

    @Override
    public Supplier<Integer> supplier() {
        return random::nextInt;
    }

    public RandUnitInt bound(int bound) {
        isTrue(bound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        Supplier<Integer> supp = () -> random.nextInt(bound);
        return () -> supp;
    }

    public RandUnitInt range(int lowerBound, int upperBound) {
        notNull(lowerBound, INPUT_PARAMETER_NOT_NULL, "lowerBound");
        notNull(upperBound, INPUT_PARAMETER_NOT_NULL, "upperBound");
        isTrue(lowerBound>=0, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);
        Supplier<Integer> supp = () -> random.nextInt(upperBound - lowerBound) + lowerBound;
        return () -> supp;
    }

    public RandUnitInt from(int[] alphabet) {
        notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<Integer> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
