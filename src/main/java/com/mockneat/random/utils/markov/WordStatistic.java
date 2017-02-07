package com.mockneat.random.utils.markov;

import com.mockneat.random.Rand;
import com.mockneat.types.Pair;

import java.util.*;

/**
 * Created by andreinicolinciobanu on 31/01/2017.
 */
public class WordStatistic {

    private Double total = 0.0;
    private Pair<Double, String>[] associatedWords;
    private Rand rand;

    public WordStatistic(Map<String, Integer> rawWordCount) {
        this.associatedWords = new Pair[rawWordCount.size()];
        this.rand = new Rand();
        processRawWordCount(rawWordCount);
    }

    protected void processRawWordCount(Map<String, Integer> rawLine) {
        this.total = getTotal(rawLine);
        int i = 0;
        double cv = 0;
        for(Map.Entry<String, Integer> entry : rawLine.entrySet()) {
            cv+=entry.getValue();
            associatedWords[i] = new Pair<>(cv, entry.getKey());
            i++;
        }
        Arrays.sort(associatedWords, (v1, v2) -> v1.getFirst().compareTo(v2.getFirst()));
    }

    protected Double getTotal(Map<String, Integer> rawLine) {
        return (double) rawLine.values().stream().mapToInt(Integer::intValue).sum();
    }

    public String nextWord() {
        double rd = rand.doubles().bound(total).val();
        for(int i = 0; i < associatedWords.length; ++i) {
            if (rd < associatedWords[i].getFirst()) {
                return associatedWords[i].getSecond();
            }
        }
        return "";
    }
}
