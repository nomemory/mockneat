package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitLong;

import static com.mockneat.utils.NextUtils.checkLongAlphabet;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class LongsFrom implements RandUnitLong {
    private Rand rand;
    private long[] alphabet;

    public LongsFrom(Rand rand, long[] alphabet) {
        this.rand = rand;
        this.alphabet = alphabet;
    }

    @Override
    public Long val() {
        checkLongAlphabet(alphabet);
        int randIdx = rand.ints().withBound(alphabet.length).val();
        return alphabet[randIdx];
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
