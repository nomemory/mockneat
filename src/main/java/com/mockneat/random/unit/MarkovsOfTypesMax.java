package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitInt;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.random.utils.markov.MarkovUnit;
import com.mockneat.types.enums.MarkovChainType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by andreinicolinciobanu on 05/02/2017.
 */
public class MarkovsOfTypesMax implements RandUnitString {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarkovsOfTypesMax.class);

    private Rand rand;
    private RandUnitInt idxGen;
    private MarkovUnit[] units;
    private Integer max;

    public MarkovsOfTypesMax(Rand rand, Integer max, MarkovChainType... types) {
        this.units = new MarkovUnit[types.length];
        this.idxGen = rand.ints().withBound(types.length);
        this.max = max;
        this.rand = rand;
        for(int i = 0; i < types.length; ++i) {
            try {
                units[i] = new MarkovUnit(rand, types[i].getPath(), 2);
            } catch (IOException e) {
                LOGGER.error("Cannot read markov file from disk. ",e);
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
