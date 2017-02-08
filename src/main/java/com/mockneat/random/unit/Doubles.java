package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitDouble;

import java.util.Random;
import java.util.function.Supplier;

import static com.mockneat.utils.CheckUtils.checkDoubleAlphabet;
import static com.mockneat.utils.CheckUtils.checkDoubleBounds;

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

    public RandUnitDouble range(Double lowerBound, Double upperBound) {
        Supplier<Double> supp = () -> {
            checkDoubleBounds(lowerBound, upperBound);
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

    public RandUnitDouble bound(Double bound) {
        return range(0.0, bound);
    }

    public RandUnitDouble from(double[] alphabet) {
        Supplier<Double> supp = () -> {
            checkDoubleAlphabet(alphabet);
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
