package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.FromAlphabetGenericUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;

import java.util.Random;
import java.util.stream.Stream;

import static com.mockneat.utils.NextUtils.checkProbability;

/**
 * Created by andreinicolinciobanu on 02/01/2017.
 */
public class Bools implements RandUnitGeneric<Boolean>, FromAlphabetGenericUnit<Boolean> {

    private Rand rand;
    private Random random;

    public Bools(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    /**
     * Returns a (pseudo)random val of TRUE or FALSE
     *
     * @return The name possible (pseudo)random val of TRUE or FALSE.
     */
    public Boolean val() {
        return random.nextBoolean();
    }

    /**
     * Returns a (pseudo)random names of stream of TRUE or FALSE
     * @return
     */
    public Stream<Boolean> stream() { return Stream.generate(this::val); }

    public BoolsProbability probability(Double probability) {
        return new BoolsProbability(rand, probability);
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}

