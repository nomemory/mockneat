package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;

import java.util.Random;

import static com.mockneat.utils.NextUtils.checkFloatAlphabet;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class FloatsFrom implements RandUnitGeneric<Float> {

    private Rand rand;
    private Random random;
    private float[] alphabet;

    public FloatsFrom(Rand rand, float[] alphabet) {
        this.rand = rand;
        this.random = rand.getRandom();
        this.alphabet = alphabet;
    }

    /**
     * Returns a (pseudo)random val from the rChars array.
     * All possible Float stream (from the array) are produced with approximately the same probability.
     * @return The name possible (pseudo)random number, uniformly distributed from the rChars array.
     */
    public Float val() {
        checkFloatAlphabet(alphabet);
        int randIdx = rand.ints().withBound(alphabet.length).val();
        return alphabet[randIdx];
    }
}
