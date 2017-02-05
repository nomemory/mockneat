package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitDouble;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.mockneat.utils.NextUtils.checkDoubleBounds;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class DoublesRange implements RandUnitDouble {

    private Rand rand;
    private Random random;
    private Double lowerBound = Double.MIN_VALUE;
    private Double upperBound = Double.MAX_VALUE;

    public DoublesRange(Rand rand, Double lowerBound, Double upperBound) {
        this.rand = rand;
        this.random = rand.getRandom();
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public DoublesRange(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    public DoublesRange lower(Double lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    public DoublesRange upper(Double upperBound) {
        this.upperBound = upperBound;
        return this;
    }

    /**
     * Returns a (pseudo)random number between [lowerBound, upperBound)
     * <p>
     * All possible double numbers from the interval are supposedly produced with the same
     * probability
     *
     * @return
     */
    public Double val() {

        checkDoubleBounds(lowerBound, upperBound);

        if (this.random instanceof ThreadLocalRandom)
            return ((ThreadLocalRandom) random).nextDouble(lowerBound, upperBound);
        else if (Double.valueOf(upperBound - lowerBound).isInfinite()) {
            throw new IllegalArgumentException("Infinite bound difference.");
        } else {
            return random.nextDouble() * (upperBound - lowerBound) + lowerBound;
        }
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
