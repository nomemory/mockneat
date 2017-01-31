package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitInt;

import java.util.Random;

import static com.mockneat.utils.NextUtils.checkIntegerAlphabet;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class IntsFrom implements RandUnitInt {
    private Rand rand;
    private Random random;
    private int[] alphabet;

    public IntsFrom(Rand rand, int[] alphabet) {
        this.rand = rand;
        this.random = rand.getRandom();
        this.alphabet = alphabet;
    }

    /**
     * Returns a (pseudo)random val from the rChars array.
     * All possible Integer stream (from the array) are produced with aproximately the same probability.
     *
     * @return The name possible (pseudo)random number, uniformly distributed from the rChars array.
     */
    public Integer val() {
        checkIntegerAlphabet(alphabet);
        int randIdx = rand.ints().withBound(alphabet.length).val();
        return alphabet[randIdx];
    }
}
