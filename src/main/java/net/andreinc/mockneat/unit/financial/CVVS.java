package net.andreinc.mockneat.unit.financial;

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
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.CVVType;
import net.andreinc.mockneat.utils.LoopsUtils;
import net.andreinc.mockneat.utils.ValidationUtils;

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
        return () ->
               () ->
               {
                CVVType type = mockNeat.from(types).val();
                return mockNeat
                        .cvvs()
                        .type(type)
                        .val();
               };
    }
}
