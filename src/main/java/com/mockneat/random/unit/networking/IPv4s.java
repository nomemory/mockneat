package com.mockneat.random.unit.networking;

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
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.mockneat.random.Rand;
import com.mockneat.random.interfaces.RandUnitString;
import com.mockneat.types.Range;
import com.mockneat.types.enums.IPv4Type;
import java.util.Arrays;
import java.util.function.Supplier;
import static com.mockneat.types.enums.IPv4Type.NO_CONSTRAINT;
import static com.mockneat.random.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.random.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class IPv4s implements RandUnitString {

    private Rand rand;

    public IPv4s(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return type(NO_CONSTRAINT).supplier();
    }

    public RandUnitString types(IPv4Type... types) {
        notEmpty(types, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        IPv4Type type = rand.from(types).val();
        return type(type);
    }

    public RandUnitString type(IPv4Type type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
        Range<Integer>[] oc = type.getOctets();
        Supplier<String> supp = () -> {
            StringBuilder buff = new StringBuilder();
            Arrays.stream(oc).forEach(range -> {
                int low = range.getLowerBound();
                int up = range.getUpperBound();
                if (range.isConstant()) buff.append(range.getLowerBound()).append(".");
                else {
                    int result = rand.ints().range(range.getLowerBound(), range.getUpperBound() + 1).val();
                    buff.append(result).append(".");
                }
            });
            buff.deleteCharAt(buff.length() - 1);
            return buff.toString();
        };
        return () -> supp;
    }

}
