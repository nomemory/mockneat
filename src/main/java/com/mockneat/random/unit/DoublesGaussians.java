package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitDouble;

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

    @Override
    public Double val() {
        return random.nextGaussian();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

}
