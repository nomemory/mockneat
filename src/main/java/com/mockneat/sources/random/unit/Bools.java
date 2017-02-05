package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.FromAlphabetGenericUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnit;

import java.util.Random;
import java.util.stream.Stream;

import static com.mockneat.utils.NextUtils.checkProbability;

/**
 * Created by andreinicolinciobanu on 02/01/2017.
 */
public class Bools implements RandUnit<Boolean>, FromAlphabetGenericUnit<Boolean> {

    private Rand rand;
    private Random random;

    public Bools(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    public Boolean val() {
        return random.nextBoolean();
    }

    public RandUnit<Boolean> probability(Double probability) {
        return new BoolsProbability(rand, probability);
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}

