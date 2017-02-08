package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;

import java.util.Random;
import java.util.function.Supplier;

import static com.mockneat.utils.ValidationUtils.*;
import static com.mockneat.utils.ValidationUtils.UPPER_BOUND_BIGGER_LOWER_BOUND;
import static com.mockneat.utils.ValidationUtils.UPPER_BOUND_BIGGER_THAN_ZERO;
import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.Validate.notNaN;

public class Floats implements RandUnit<Float> {
    private Random random;

    public Floats(Rand rand) {
        this.random = rand.getRandom();
    }

    @Override
    public Supplier<Float> supplier() {
        return random::nextFloat;
    }

    public RandUnit<Float> range(float lowerBound, float upperBound) {
        notNull(lowerBound, INPUT_PARAMETER_NOT_NULL, "lowerBound");
        notNull(upperBound, INPUT_PARAMETER_NOT_NULL, "upperBound");
        finite(lowerBound);
        finite(upperBound);
        notNaN(lowerBound);
        notNaN(upperBound);
        isTrue(lowerBound>=0.0f, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0.0f, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);

        Supplier<Float> supp = () -> random.nextFloat() * (upperBound - lowerBound) + lowerBound;
        return () -> supp;
    }

    public RandUnit<Float> bound(float bound) {
        return range(0f, bound);
    }

    public RandUnit<Float> from(float[] alphabet) {
        notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<Float> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
