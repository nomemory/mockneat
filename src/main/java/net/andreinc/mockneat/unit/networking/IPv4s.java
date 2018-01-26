package net.andreinc.mockneat.unit.networking;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.Range;
import net.andreinc.mockneat.types.enums.IPv4Type;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;
import static net.andreinc.mockneat.types.enums.IPv4Type.NO_CONSTRAINT;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

/**
 * Copyright 2017, Andrei N. Ciobanu
 * <p>
 * Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

public class IPv4s extends MockUnitBase implements MockUnitString {

    public IPv4s(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return type(NO_CONSTRAINT).supplier();
    }

    /**
     * <p>This method can be used to narrow down the IPv4 generation process to a few types.</p>
     *
     * @param types The types array (var-arg).
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString types(IPv4Type... types) {
        notEmptyOrNullValues(types, "types");
        IPv4Type type = mockNeat.from(types).val();
        return type(type);
    }

    /**
     * <p>This method can be used to narrow down the IPv4 generation process to a certain type.</p>
     *
     * @param type The type of array to be generated.
     * @return A new {@code MockUnitString}.
     */
    //TODO modify the way IPV4 are generated
    public MockUnitString type(IPv4Type type) {
        notNull(type, "type");
        Range<Integer>[] oc = type.getOctets();
        Supplier<String> supp = () -> {
            StringBuilder buff = new StringBuilder();
            Arrays.stream(oc).forEach(range -> {
                int low = range.getLowerBound();
                int up = range.getUpperBound();
                if (range.isConstant()) buff.append(low).append(".");
                else {
                    int result = mockNeat.ints().range(low, up + 1).val();
                    buff.append(result).append(".");
                }
            });
            buff.deleteCharAt(buff.length() - 1);
            String result = buff.toString();
            return type.isPrivateAllowed() || !isPrivate(result) ? result : type(type).val() ;
        };
        return () -> supp;
    }

    private boolean isPrivate(String ip) {
        List<Integer> numbers = 
                Arrays.stream(ip.split("\\."))
                    .map(Integer::parseInt)
                    .collect(toList());
        return (
                numbers.get(0) == 10 ||
                (numbers.get(0) == 172 && numbers.get(1) >= 16 && numbers.get(1) < 32) ||
                (numbers.get(0) == 192 && numbers.get(1) == 168)
        );
            
    }

}
