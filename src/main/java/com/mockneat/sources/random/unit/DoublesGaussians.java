package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitDouble;

import java.util.Random;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class DoublesGaussians implements RandUnitDouble {

    private Rand rand;
    private Random random;

    public DoublesGaussians(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    /**
     * Returns a (pseudo)random number between [0.0, 1.0).
     * <p>
     * All possible double numbers are supposedly produced with the same
     * probability.
     *
     * @return The name (pseudo)random double val.
     */
    public Double val() {
        return random.nextGaussian();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

}
