package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitLong;

import java.util.Random;

import static com.mockneat.utils.NextUtils.checkLongAlphabet;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class LongsFrom implements RandUnitLong {
    private Rand rand;
    private Random random;
    private long[] alphabet;

    public LongsFrom(Rand rand, long[] alphabet) {
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
    public Long val() {
        checkLongAlphabet(alphabet);
        int randIdx = rand.ints().withBound(alphabet.length).val();
        return alphabet[randIdx];
    }
}
