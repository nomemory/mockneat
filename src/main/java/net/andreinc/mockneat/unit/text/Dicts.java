package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.utils.file.FileManager;

import java.util.Collections;
import java.util.List;

import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Dicts extends MockUnitBase {

    private final FileManager fm = FileManager.getInstance();

    /**
     * <p>Returns a {@code Dicts} object that can be used to generate data from the library existing dictionaries.</p>
     *
     * <p>A dictionary is an {@code enum} mapping a text file containing a set of data</p>
     *
     * <p>The file contents are loaded in memory after the first call.</p>
     *
     * <p>Check {@link DictType} to see the comprehensive list.</p>
     *
     * @return A re-usable {@code Dicts} object.
     */
    public static Dicts dicts() {
        return MockNeat.threadLocal().dicts();
    }

    protected Dicts() {}

    public Dicts(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate arbitrary values from a Dictionary ({@link DictType}.</p>
     *
     * @param type The type of the dictionary.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString type(DictType type) {
        notNull(type, "type");
        return () -> mockNeat.fromStrings(fm.getLines(type))::val;
    }

    /**
     * Returns a new {@code MockUnitString} that can be used to generate arbitrary values from a list of dictionaries {@link DictType}.
     *
     * @param types A var-arg array containing a list of possible {@code DictType}s.
     * @return A mew {@code MockUnitString}
     */
    public MockUnitString types(DictType... types) {
        notEmptyOrNullValues(types, "types");
        return () -> {
            DictType type = mockNeat.from(types).val();
            return mockNeat.fromStrings(fm.getLines(type))::val;
        };
    }

    /**
     * Returns all data from the dictionary as an immutable list.
     *
     * @param type the dictionary type we want to retrieve
     * @return The list of lines from the dict
     */
    public List<String> data(DictType type) {
        notNull(type, "type");
        return Collections.unmodifiableList(fm.getLines(type));
    }
}
