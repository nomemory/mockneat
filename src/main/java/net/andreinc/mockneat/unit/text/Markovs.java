package net.andreinc.mockneat.unit.text;

import net.andreinc.markovneat.MChainText;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.MarkovChainType;
import net.andreinc.mockneat.utils.file.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.MarkovChainType.LOREM_IPSUM;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Markovs extends MockUnitBase implements MockUnitString {


    private static final FileManager fm = FileManager.getInstance();

    private static final Logger logger = LoggerFactory.getLogger(Markovs.class);

    private final Map<MarkovChainType, MChainText> markovUnits =  new EnumMap<>(MarkovChainType.class);

    private int size = 512;

    /**
     * <p>Returns a {@code Markovs} object that can be used to generate arbitrary text that can pass as almost valid using Markov Chains</p>
     *
     * <p>It can also be used to generate Lorem Ipsum text that is different each time the ending method is invoked</p>
     *
     * @return A re-usable {@code Markovs} object. The {@code Markovs} class implements {@code MockUnitString}
     */
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
            MChainText mChainText = new MChainText(2, mockNeat.getRandom());
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
                logger.error("Cannot load MarkovUnit chain of type '{}'.", type.name());
                throw new UncheckedIOException(e);
            }
        };
        return () -> supp;
    }

    public MockUnitString loremIpsum() {
        return type(LOREM_IPSUM);
    }
}
