package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitInt;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;
import com.mockneat.sources.random.utils.markov.MarkovUnit;
import com.mockneat.types.enums.MarkovChainType;

import java.io.IOException;

/**
 * Created by andreinicolinciobanu on 02/02/2017.
 */
public class MarkovsOfTypes implements RandUnitString {

    private final Integer DEFAULT_MAX_LENGTH = 500;

    private Rand rand;
    private MarkovsOfTypesMax markovsOfTypesMax;

    public MarkovsOfTypes(Rand rand, MarkovChainType... types) {
        this.markovsOfTypesMax = new MarkovsOfTypesMax(rand, DEFAULT_MAX_LENGTH, types);
    }

    @Override
    public String val() {
        return markovsOfTypesMax.val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
