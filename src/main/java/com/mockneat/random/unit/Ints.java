package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.FromAlphabetIntUnit;
import com.mockneat.random.unit.interfaces.RandUnitInt;

import java.util.Random;

public class Ints implements RandUnitInt, FromAlphabetIntUnit {

    private Rand rand;
    private Random random;

    public Ints(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    @Override
    public Integer val() {
        return random.nextInt();
    }

    public RandUnitInt withBound(Integer bound) {
        return new IntsBound(rand, bound);
    }

    public RandUnitInt withBound() {
        return new IntsBound(rand);
    }

    public RandUnitInt inRange(Integer lowerBound, Integer upperBound) {
        return new IntsRange(rand, lowerBound, upperBound);
    }

    public RandUnitInt inRange() {
        return new IntsRange(rand);
    }

    public RandUnitInt from(int[] alphabet) {
        return new IntsFrom(rand, alphabet);
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
