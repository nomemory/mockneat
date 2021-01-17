package net.andreinc.mockneat.unit.misc;


import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.DictType.MIME_TYPE;

public class Mimes extends MockUnitBase implements MockUnitString {

    /**
     * <p>Returns a {@code Mimes} object that can be used to generate arbitrary Mime Types (Eg.: "text/html", "image/x-icon", "text/calendar")</p>
     *
     * @return A re-usable {@code Mimes} object. The {@code Mimes} class implements {@code MockUnitString}.
     */
    public static Mimes mimes() {
        return MockNeat.threadLocal().mimes();
    }

    protected Mimes() {
    }

    public Mimes(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().type(MIME_TYPE).supplier();
    }
}
