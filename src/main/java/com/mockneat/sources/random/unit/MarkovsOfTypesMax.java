package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitInt;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;
import com.mockneat.sources.random.utils.markov.MarkovUnit;
import com.mockneat.types.enums.MarkovChainType;

import java.io.IOException;

/**
 * Created by andreinicolinciobanu on 05/02/2017.
 */
public class MarkovsOfTypesMax implements RandUnitString {

    private Rand rand;
    private RandUnitInt idxGen;
    private MarkovUnit[] units;
    private Integer max;

    public MarkovsOfTypesMax(Rand rand, Integer max, MarkovChainType... types) {
        this.units = new MarkovUnit[types.length];
        this.idxGen = rand.ints().withBound(types.length);
        this.max = max;
        for(int i = 0; i < types.length; ++i) {
            try {
                units[i] = new MarkovUnit(rand, types[i].getPath(), 2);
            } catch (IOException e) {
                //TODO
                e.printStackTrace();
            }
        }
    }

    @Override
    public String val() {
        return units[idxGen.val()].generateText(max);
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
