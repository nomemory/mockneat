package net.andreinc.mockneat.unit.misc;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.DictType.MIME_TYPE;

/**
 * Created by andreinicolinciobanu on 25/04/17.
 */

// TODO document class and dictionary type
// TODO ADD JUNIT
public class Mimes implements MockUnitString {

    private MockNeat mock;

    public Mimes(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<String> supplier() {
        return mock.dicts().type(MIME_TYPE).supplier();
    }
}
