package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class RandUnitDoubleFromImpl implements RandUnitDouble {
    private Rand rand;
    private Double[] alphabet = null;
    private List<Double> alphabetList= null;

    public RandUnitDoubleFromImpl(Rand rand, Double[] alphabet) {
        this.rand = rand;
        this.alphabet = alphabet;
    }

    public RandUnitDoubleFromImpl(Rand rand, List<Double> alphabetList) {
        this.rand = rand;
        this.alphabetList = alphabetList;
    }

    @Override
    public Double val() {
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

