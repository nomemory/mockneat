package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.MarkovChainType;

import static com.mockneat.types.enums.MarkovChainType.KAFKA;

/**
 * Created by andreinicolinciobanu on 02/02/2017.
 */
public class Markovs implements RandUnitString {

    private Rand rand;

    public Markovs(Rand rand) {
        this.rand = rand;
    }

    public RandUnitString ofTypes(MarkovChainType... types) {
        return new MarkovsOfTypes(rand, types);
    }

    public RandUnitString ofType(MarkovChainType type) {
        return new MarkovsOfTypes(rand, type);
    }

    @Override
    public String val() {
        return rand.markovs().ofType(KAFKA).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
