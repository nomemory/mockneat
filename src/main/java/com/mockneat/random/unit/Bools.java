package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.FromAlphabetGenericUnit;
import com.mockneat.random.unit.interfaces.RandUnit;

import java.util.Random;

import static com.mockneat.utils.NextUtils.checkProbability;

/**
 * Created by andreinicolinciobanu on 02/01/2017.
 */
public class Bools implements RandUnit<Boolean>, FromAlphabetGenericUnit {

    private Rand rand;
    private Random random;

    public Bools(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    @Override
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

