package com.mockneat.mock.unit.text;

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

import com.mockneat.mock.utils.ValidationUtils;
import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.mock.utils.markov.MarkovUnit;
import com.mockneat.types.enums.MarkovChainType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.mockneat.types.enums.MarkovChainType.KAFKA;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class Markovs implements MockUnitString {

    private static Logger logger = LoggerFactory.getLogger(Markovs.class);

    private Map<MarkovChainType, MarkovUnit> markovUnits =  new EnumMap<>(MarkovChainType.class);

    private MockNeat mock;
    private int size = 512;

    public Markovs(MockNeat mock) {
        this.mock = mock;
    }

    protected MarkovUnit get(MarkovChainType markovChainType) throws IOException {
        if (!markovUnits.containsKey(markovChainType)) {
            markovUnits.put(markovChainType, new MarkovUnit(mock, markovChainType.getPath(), 2));
        }
        return markovUnits.get(markovChainType);
    }

    public Markovs size(int size) {
        Markovs markovs = new Markovs(mock);
        markovs.size = size;
        return markovs;
    }

    @Override
    public Supplier<String> supplier() {
        return type(KAFKA).supplier();
    }

    public MockUnitString types(MarkovChainType... types) {
        notEmpty(types, ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        MarkovChainType type = mock.from(types).val();
        return type(type);
    }

    public MockUnitString type(MarkovChainType type) {
        notNull(type, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "type");
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
