package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class RandUnitLongFromImpl implements RandUnitLong {

    private Rand rand;
    private Long[] alphabet = null;
    private List<Long> alphabetList= null;

    public RandUnitLongFromImpl(Rand rand, Long[] alphabet) {
        this.rand = rand;
        this.alphabet = alphabet;
    }

    public RandUnitLongFromImpl(Rand rand, List<Long> alphabetList) {
        this.rand = rand;
        this.alphabetList = alphabetList;
    }

    @Override
    public Long val() {
        if (alphabetList==null) {
            int idx = rand.ints().withBound(alphabet.length).val();
            return alphabet[idx];
        } else {
            int idx = rand.ints().withBound(alphabetList.size()).val();
            return alphabetList.get(idx);
        }
    }
}
