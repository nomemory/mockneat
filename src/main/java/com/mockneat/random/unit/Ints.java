package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitInt;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static com.mockneat.utils.NextUtils.*;

public class Ints implements RandUnitInt {

    private Random random;

    public Ints(Rand rand) {
        this.random = rand.getRandom();
    }

    @Override
    public Supplier<Integer> supplier() {
        return () -> random.nextInt();
    }

    public RandUnitInt bound(Integer bound) {
        Supplier<Integer> supp = () -> {
            checkIntegerBound(bound);
            checkIntegerBoundNotZero(bound);
            return random.nextInt(bound);
        };
        return () -> supp;
    }

    public RandUnitInt range(Integer lowerBound, Integer upperBound) {
        Supplier<Integer> supp = () -> {
            checkIntegerBounds(lowerBound, upperBound);
            if (random instanceof ThreadLocalRandom) {
                return ((ThreadLocalRandom) random).nextInt(lowerBound, upperBound);
            }
            return random.nextInt(upperBound - lowerBound) + lowerBound;
        };
        return () -> supp;
    }

    public RandUnitInt from(int[] alphabet) {
        Supplier<Integer> supp = () -> {
            checkIntegerAlphabet(alphabet);
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
