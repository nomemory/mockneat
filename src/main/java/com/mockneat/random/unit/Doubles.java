package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitDouble;

import java.util.Random;
import java.util.function.Supplier;

import static com.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.Validate.*;

/**
 * Created by andreinicolinciobanu on 02/01/2017.
 */
public class Doubles implements RandUnitDouble {

    private static final double DOUBLE_UNIT = 0x1.0p-53;

    private Random random;

    public Doubles(Rand rand) {
        this.random = rand.getRandom();
    }

    @Override
    public Supplier<Double> supplier() {
        return random::nextDouble;
    }

    public RandUnitDouble gaussians() {
        return () -> random::nextGaussian;
    }

    public RandUnitDouble range(double lowerBound, double upperBound) {
        notNull(lowerBound, INPUT_PARAMETER_NOT_NULL, "lowerBound");
        notNull(upperBound, INPUT_PARAMETER_NOT_NULL, "upperBound");
        finite(lowerBound);
        finite(upperBound);
        notNaN(lowerBound);
        notNaN(upperBound);
        isTrue(lowerBound>=0.0, LOWER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>0.0, UPPER_BOUND_BIGGER_THAN_ZERO);
        isTrue(upperBound>lowerBound, UPPER_BOUND_BIGGER_LOWER_BOUND);

        Supplier<Double> supp = () -> {
            // Algorithm implementation from the Java API
            double result = (random.nextLong() >>> 11) * DOUBLE_UNIT;
            if (lowerBound < upperBound) {
                result = result * (upperBound - lowerBound) + lowerBound;
                if (result >= upperBound)
                    result = Double.longBitsToDouble(Double.doubleToLongBits(upperBound) - 1);
            }
            return result;
        };
        return () -> supp;
    }

    public RandUnitDouble bound(double bound) {
        return range(0.0, bound);
    }

    public RandUnitDouble from(double[] alphabet) {
        notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<Double> supp = () -> {
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
