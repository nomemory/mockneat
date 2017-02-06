package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class RandUnitIntFromImpl implements RandUnitInt {
    private Rand rand;
    private Integer[] alphabet = null;
    private List<Integer> alphabetList= null;

    public RandUnitIntFromImpl(Rand rand, Integer[] alphabet) {
        this.rand = rand;
        this.alphabet = alphabet;
    }

    public RandUnitIntFromImpl(Rand rand, List<Integer> alphabetList) {
        this.rand = rand;
        this.alphabetList = alphabetList;
    }

    @Override
    public Integer val() {
        if (alphabetList==null) {
            int idx = rand.ints().withBound(alphabet.length).val();
            return alphabet[idx];
        } else {
            int idx = rand.ints().withBound(alphabetList.size()).val();
            return alphabetList.get(idx);
        }
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
