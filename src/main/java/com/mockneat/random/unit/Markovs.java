package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.random.utils.markov.MarkovUnit;
import com.mockneat.types.enums.MarkovChainType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.mockneat.types.enums.MarkovChainType.KAFKA;
import static com.mockneat.utils.CheckUtils.checkMarkovChainTypeNotNull;

/**
 * Created by andreinicolinciobanu on 02/02/2017.
 */
public class Markovs implements RandUnitString {

    private static Logger logger = LoggerFactory.getLogger(Markovs.class);

    private Map<MarkovChainType, MarkovUnit> markovUnits =  new EnumMap<>(MarkovChainType.class);

    private Rand rand;
    private int size = 512;

    public Markovs(Rand rand) {
        this.rand = rand;
    }

    protected MarkovUnit get(MarkovChainType markovChainType) throws IOException {
        if (!markovUnits.containsKey(markovChainType)) {
            markovUnits.put(markovChainType, new MarkovUnit(rand, markovChainType.getPath(), 2));
        }
        return markovUnits.get(markovChainType);
    }

    public Markovs size(int size) {
        Markovs markovs = new Markovs(rand);
        markovs.size = size;
        return markovs;
    }

    @Override
    public Supplier<String> supplier() {
        return type(KAFKA).supplier();
    }

    public RandUnitString types(MarkovChainType... types) {
        MarkovChainType type = rand.objs().from(types).val();
        return type(type);
    }

    public RandUnitString type(MarkovChainType type) {
        checkMarkovChainTypeNotNull(type);
        Supplier<String> supp = () -> {
            MarkovUnit unit = null;
            try {
                unit = get(type);
                return unit.generateText(size);
            } catch (IOException e) {
                logger.error("Cannot get Markov chain of type {}. Empty String will be returned instead.", type.name(), e);
            }
            return "";
        };
        return () -> supp;
    }
}
