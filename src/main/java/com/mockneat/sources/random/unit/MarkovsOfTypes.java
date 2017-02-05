package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.MarkovChainType;

/**
 * Created by andreinicolinciobanu on 02/02/2017.
 */
public class MarkovsOfTypes implements RandUnitString {

    private Rand rand;
    private String[] paths;

    public MarkovsOfTypes(MarkovChainType... types) {
        //TODO check types
        this.paths = new String[types.length];
        for(int i = 0; i < types.length; ++i) {
        }
    }

    @Override
    public String val() {
        return null;
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
