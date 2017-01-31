package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.FromAlphabetDoubleUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitDouble;

import java.util.Random;

/**
 * Created by andreinicolinciobanu on 02/01/2017.
 */
public class Doubles implements RandUnitDouble, FromAlphabetDoubleUnit {

    private Rand rand;
    private Random random;

    public Doubles(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    @Override
    public Rand getRand() {
        return this.rand;
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
        return random.nextDouble();
    }

    public DoublesGaussians gaussians() {
        return new DoublesGaussians(rand);
    }

    public DoublesRange inRange() {
        return new DoublesRange(rand);
    }

    public DoublesRange inRange(Double lowerBound, Double upperBound) {
        return new DoublesRange(rand, lowerBound, upperBound);
    }

    public DoublesBound withBound(Double bound) {
        return new DoublesBound(rand, bound);
    }

    public DoublesBound withBound() {
        return new DoublesBound(rand);
    }

    public DoublesFrom from(double[] alphabet) {
        return new DoublesFrom(rand, alphabet);
    }
}
