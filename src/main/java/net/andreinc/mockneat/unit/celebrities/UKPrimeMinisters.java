package net.andreinc.mockneat.unit.celebrities;


import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

public class UKPrimeMinisters extends MockUnitBase implements MockUnitString {

    public UKPrimeMinisters(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * The method returns a {@code Supplier<String>} that can be used to generate British prime-minister names (Eg.: "William Lamb")
     *
     * @return A new {@code Supplier<String>}
     */
    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts()
                        .type(DictType.UK_PRIME_MINISTERS)
                        .supplier();
    }
}
