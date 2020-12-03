package net.andreinc.mockneat.unit.financial;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.CVVType;
import net.andreinc.mockneat.utils.LoopsUtils;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.CVVType.CVV3;
import static net.andreinc.mockneat.types.enums.CVVType.CVV4;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class CVVS extends MockUnitBase implements MockUnitString {

    public static CVVS cvvs() { return new CVVS(); }

    protected CVVS() { }

    public CVVS(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return type(CVV3).supplier();
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate a 3-digit code.</p>
     *
     * @return A new {@code MockUnitString}
     */
    public MockUnitString cvv3() {
        return type(CVV3);
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate a 4-digit code.</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString cvv4() {
        return type(CVV4);
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate a CVV code based on the supplied type: {@link CVVType}</p>
     *
     * @param type The type of the CVV.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString type(CVVType type) {
        notNull(type, "type");
        Supplier<String> supplier = () -> {
            final StringBuilder builder = new StringBuilder();
            LoopsUtils.loop(type.getLength(), () ->
                    builder.append(mockNeat.chars().digits().val()));
            return builder.toString();
        };
        return () -> supplier;
    }

    /**
     * Returns a new {@code MockUnitString} that can be used to generate a CVV code based on the supplied types: {@link CVVType}.
     *
     * @param types A var-arg array containing the supplied types.
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString types(CVVType... types) {
        notEmptyOrNullValues(types, "types");
        return () -> () -> {
                CVVType type = mockNeat.from(types).val();
                return mockNeat
                        .cvvs()
                        .type(type)
                        .val();
        };
    }
}
