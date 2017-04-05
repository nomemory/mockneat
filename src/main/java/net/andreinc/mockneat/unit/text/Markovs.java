package net.andreinc.mockneat.unit.text;

/*
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnitString;
import net.andreinc.mockneat.types.enums.MarkovChainType;
import net.andreinc.mockneat.unit.text.markov.MarkovUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.MarkovChainType.KAFKA;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Markovs implements MockUnitString {

    private static final Logger logger = LoggerFactory.getLogger(Markovs.class);

    private final Map<MarkovChainType, MarkovUnit> markovUnits =  new EnumMap<>(MarkovChainType.class);

    private final MockNeat mock;
    private int size = 512;

    public Markovs(MockNeat mock) {
        this.mock = mock;
    }

    private MarkovUnit get(MarkovChainType markovChainType) throws IOException {
        if (!markovUnits.containsKey(markovChainType)) {
            logger.info("Loading MarkovUnit in memory '{}'." , markovChainType.getFile());
            markovUnits.put(markovChainType, MarkovUnit.internal(mock, markovChainType, 2));
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
        notEmptyOrNullValues(types, "types");
        MarkovChainType type = mock.from(types).val();
        return type(type);
    }

    public MockUnitString type(MarkovChainType type) {
        notNull(type, "type");
        Supplier<String> supp = () -> {
            MarkovUnit unit = null;
            try {
                unit = get(type);
                return unit.generateText(size);
            } catch (IOException e) {
                logger.error("Cannot load MarkovUnit chain of type '{}'.", type.name(), e);
                throw new UncheckedIOException(e);
            }
        };
        return () -> supp;
    }
}
