package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitDouble;

import java.util.Random;

import static com.mockneat.utils.NextUtils.checkDoubleAlphabet;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class DoublesFrom implements RandUnitDouble {

    private Rand rand;
    private Random random;
    private double[] alphabet;

    public DoublesFrom(Rand rand, double[] alphabet) {
        this.rand = rand;
        this.random = rand.getRandom();
        this.alphabet = alphabet;
    }

    /**
     * Returns a (pseudo)random val from the rChars array.
     * All possible Double stream (from the array) are produced with approximately the same probability.
     *
     * @return The name possible (pseudo)random number, uniformly distributed from the rChars array.
     */
    @Override
    public Double val() {
        checkDoubleAlphabet(alphabet);
        int randIdx = random.nextInt(alphabet.length);
        return alphabet[randIdx];
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
