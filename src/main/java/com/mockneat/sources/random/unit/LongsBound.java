package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitLong;

import java.util.Random;

import static com.mockneat.utils.NextUtils.checkLongBound;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class LongsBound implements RandUnitLong {

    private Rand rand;
    private Random random;
    private Long bound = Long.MAX_VALUE;

    public LongsBound(Rand rand, Long bound) {
        this.rand = rand;
        this.random = rand.getRandom();
        this.bound = bound;
    }

    public LongsBound(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    public LongsBound bound(Long bound) {
        this.bound = bound;
        return this;
    }

    @Override
    public Long val() {
        checkLongBound(bound);
        long b;
        long result;
        do {
            b = (random.nextLong() << 1) >>> 1;
            result = b % bound;
        } while (b-result+bound-1 < 0L);

        return result;
    }
}
