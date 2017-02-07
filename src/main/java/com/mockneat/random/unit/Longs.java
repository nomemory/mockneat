package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitLong;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static com.mockneat.utils.NextUtils.*;

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
        return () -> random.nextLong();
    }

    public RandUnitLong bound(Long bound) {
        checkLongBound(bound);
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

    public RandUnitLong range(Long lowerBound, Long upperBound) {
        Supplier<Long> supplier = () -> {
            checkLongBounds(lowerBound, upperBound);
            if (random instanceof ThreadLocalRandom) {
                // Use the native implementation that is only available for ThreadLocalRandoms
                return ((ThreadLocalRandom) random).nextLong(lowerBound, upperBound);
            }
            return rand.longs().bound(upperBound - lowerBound).val() + lowerBound;
        };
        return () -> supplier;
    }

    public RandUnitLong from(long[] alphabet) {
        Supplier<Long> supp = () -> {
            checkLongAlphabet(alphabet);
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }

}
