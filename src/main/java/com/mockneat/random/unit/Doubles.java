package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.FromAlphabetDoubleUnit;
import com.mockneat.random.unit.interfaces.RandUnitDouble;

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

    @Override
    public Double val() {
        return random.nextDouble();
    }

    public RandUnitDouble gaussians() {
        return new DoublesGaussians(rand);
    }

    public RandUnitDouble inRange() {
        return new DoublesRange(rand);
    }

    public RandUnitDouble inRange(Double lowerBound, Double upperBound) {
        return new DoublesRange(rand, lowerBound, upperBound);
    }

    public RandUnitDouble withBound(Double bound) {
        return new DoublesBound(rand, bound);
    }

    public RandUnitDouble withBound() {
        return new DoublesBound(rand);
    }

    public RandUnitDouble from(double[] alphabet) {
        return new DoublesFrom(rand, alphabet);
    }
}
