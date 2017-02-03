package com.mockneat.sources.random.utils.markov;

import com.mockneat.sources.random.Rand;

import java.util.*;

/**
 * Created by andreinicolinciobanu on 31/01/2017.
 */
public class WordStatistic {

    private Integer total = 0;
    private Map<Integer, String> associatedWords =
            new TreeMap<>();

    public WordStatistic(Map<String, Integer> rawWordCount) {
        processRawWordCount(rawWordCount);
    }

    protected void processRawWordCount(Map<String, Integer> rawLine) {
        this.total = getTotal(rawLine);
        int cv = 0;
        for(Map.Entry<String, Integer> entry : rawLine.entrySet()) {
            cv+=entry.getValue();
            associatedWords.put(cv, entry.getKey());
        }
    }

    protected Integer getTotal(Map<String, Integer> rawLine) {
        return rawLine.values().stream().mapToInt(Integer::intValue).sum();
    }

    public String nextWord() {
        Rand rand = new Rand();
        double rd = rand.doubles().withBound((double)total).val();
        for (Map.Entry<Integer, String> entry : associatedWords.entrySet()) {
            if (rd < (double) entry.getKey()) {
                return entry.getValue();
            }
        }
        return "";
    }
}
