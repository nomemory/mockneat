package com.mockneat.random.unit;


import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;

import java.util.Random;
import java.util.function.Supplier;

import static org.apache.commons.lang3.Validate.inclusiveBetween;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 02/01/2017.
 */
public class Bools implements RandUnit<Boolean> {

    private Rand rand;
    private Random random;

    public Bools(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    protected Boolean withProb(Double lower, Double trigger, Double upper) {
        return rand.doubles().range(lower, upper).val() < trigger;
    }

    public RandUnit<Boolean> probability(double probability) {
        inclusiveBetween(0.0, 100.0, probability);
        Supplier<Boolean> supp = () -> withProb(0.0, probability, 100.0);
        return () -> supp;
    }

    @Override
    public Supplier<Boolean> supplier() {
        return random::nextBoolean;
    }
}

