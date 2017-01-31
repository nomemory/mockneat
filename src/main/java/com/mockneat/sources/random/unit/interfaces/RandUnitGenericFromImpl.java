package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.List;

import static com.mockneat.utils.NextUtils.checkAlphabet;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class RandUnitGenericFromImpl<T> implements RandUnitGeneric<T> {

    private Rand rand;
    private T[] alphabet = null;
    private List<T> alphabetList= null;

    public RandUnitGenericFromImpl(Rand rand, T[] alphabet) {
        this.rand = rand;
        this.alphabet = alphabet;
    }

    public RandUnitGenericFromImpl(Rand rand, List<T> alphabetList) {
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
}
