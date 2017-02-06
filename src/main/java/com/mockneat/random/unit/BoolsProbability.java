package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;

import static com.mockneat.utils.NextUtils.checkProbability;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class BoolsProbability implements RandUnit<Boolean> {

    private Rand rand;
    private Double probability;

    protected BoolsProbability(Rand rand, Double probability) {
        this.rand = rand;
        this.probability = probability;
    }

    /**
     * Not to be exposed as a public method
     * @param lower
     * @param trigger
     * @param upper
     * @return
     */
    protected Boolean val(Double lower, Double trigger, Double upper) {
        return rand.doubles().inRange(lower, upper).val() < trigger;
    }

    /**
     * Returns TRUE with a given probability%, FALSE otherwise (100.0-probability)%.
     * @return The name possible TRUE or FALSE respecting the given probability.
     */
    @Override
    public Boolean val() {
        checkProbability(probability);
        return val(0.0, probability, 100.0);
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
