package net.andreinc.mockneat.unit.user;

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

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.Pair;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.UserNameType;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Users extends MockUnitBase implements MockUnitString {

    private static final Double UNDERSCORE = 0.15;

    public Users(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return types(UserNameType.values()).supplier();
    }

    public MockUnitString type(UserNameType type) {
        notNull(type, "type");
        Supplier<String> supplier = () -> generateUserName(type);
        return () -> supplier;
    }

    public MockUnitString types(UserNameType... types) {
        notEmptyOrNullValues(types, "types");
        UserNameType type = mockNeat.from(types).val();
        return type(type);
    }

    private String generateUserName(UserNameType type) {
        Pair<DictType, DictType> pair = mockNeat.from(type.getDictCombos()).val();

        String part1 =
                mockNeat.dicts().type(pair.getFirst()).format(LOWER_CASE).val();
        String part2 =
                mockNeat.dicts().type(pair.getSecond()).format(LOWER_CASE).val();

        if (mockNeat.bools().probability(UNDERSCORE).val()) {
            part1 += "_";
        }
        return part1 + part2;
    }
}
