package com.mockneat.random.unit.text;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.mockneat.random.Rand;
import com.mockneat.random.interfaces.RandUnitString;
import com.mockneat.random.utils.markov.MarkovUnit;
import com.mockneat.types.enums.MarkovChainType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.mockneat.types.enums.MarkovChainType.KAFKA;
import static com.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

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
        notEmpty(types, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        MarkovChainType type = rand.objs().from(types).val();
        return type(type);
    }

    public RandUnitString type(MarkovChainType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
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
