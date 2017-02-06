package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;

import java.util.List;

import static com.mockneat.utils.NextUtils.checkAlphabet;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class RandUnitFromImpl<T> implements RandUnit<T> {

    private Rand rand;
    private T[] alphabet = null;
    private List<T> alphabetList= null;

    public RandUnitFromImpl(Rand rand, T[] alphabet) {
        this.rand = rand;
        this.alphabet = alphabet;
    }

    public RandUnitFromImpl(Rand rand, List<T> alphabetList) {
        this.rand = rand;
        this.alphabetList = alphabetList;
    }

    @Override
    public T val() {
        if (alphabetList==null) {
            checkAlphabet(alphabet);
            int idx = rand.ints().withBound(alphabet.length).val();
            return alphabet[idx];
        } else {
            checkAlphabet(alphabetList);
            int idx = rand.ints().withBound(alphabetList.size()).val();
            return alphabetList.get(idx);
        }
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
