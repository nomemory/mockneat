package net.andreinc.mockneat.unit.text;

/**
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

import net.andreinc.markovneat.MChainText;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.MarkovChainType;
import net.andreinc.mockneat.unit.text.markov.MarkovUnit;
import net.andreinc.mockneat.utils.ValidationUtils;
import net.andreinc.mockneat.utils.file.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static net.andreinc.mockneat.types.enums.MarkovChainType.KAFKA;
import static net.andreinc.mockneat.types.enums.MarkovChainType.LOREM_IPSUM;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Markovs extends MockUnitBase implements MockUnitString {


    private static final FileManager fm = FileManager.getInstance();

    private static final Logger logger = LoggerFactory.getLogger(Markovs.class);

    private final Map<MarkovChainType, MChainText> markovUnits =  new EnumMap<>(MarkovChainType.class);

    private int size = 512;

    public static Markovs markovs() {
        return MockNeat.threadLocal().markovs();
    }

    public Markovs(MockNeat mockNeat) {
        super(mockNeat);
    }

    protected Markovs() {}

    private MChainText get(MarkovChainType markovChainType) throws IOException {
        if (!markovUnits.containsKey(markovChainType)) {
            logger.info("Loading MarkovUnit in memory '{}'." , markovChainType.getFile());
            MChainText mChainText = new MChainText(2);
            List<String> lines = fm.read(markovChainType);
            mChainText.train(lines);
            markovUnits.put(markovChainType, mChainText);
        }
        return markovUnits.get(markovChainType);
    }

    public Markovs size(int size) {
        Markovs markovs = new Markovs(mockNeat);
        markovs.size = size;
        return markovs;
    }

    @Override
    public Supplier<String> supplier() {
        return type(LOREM_IPSUM).supplier();
    }

    public MockUnitString types(MarkovChainType... types) {
        notEmptyOrNullValues(types, "types");
        return () -> {
            MarkovChainType type = mockNeat.from(types).val();
            return type(type).supplier();
        };
    }

    public MockUnitString type(MarkovChainType type) {
        notNull(type, "type");
        Supplier<String> supp = () -> {
            MChainText unit;
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
