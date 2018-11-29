package net.andreinc.mockneat.unit.text;

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
import net.andreinc.mockneat.abstraction.MockUnitInt;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.StringType;

import java.util.Random;
import java.util.function.Supplier;

import static net.andreinc.mockneat.alphabets.Alphabets.HEXA_STR;
import static net.andreinc.mockneat.alphabets.Alphabets.SPECIAL_CHARACTERS_STR;
import static java.util.stream.Collectors.joining;
import static net.andreinc.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.RandomStringUtils.random;

public class Strings extends MockUnitBase implements MockUnitString {

    private final Random random;
    private int size = 64;
    private MockUnitInt sizeUnit;

    public Strings() {
        super();
        this.random = mockNeat.getRandom();
    }

    public Strings(MockNeat mockNeat) {
        super(mockNeat);
        this.random = mockNeat.getRandom();
    }

    public Strings size(int size) {
        isTrue(size>0, SIZE_BIGGER_THAN_ZERO_STRICT);
        this.size = size;
        return this;
    }

    public Strings size(MockUnitInt sizeUnit) {
        notNull(sizeUnit, INPUT_PARAMETER_NOT_NULL, "sizeUnit");
        this.sizeUnit = sizeUnit;
        return this;
    }

    protected int getSize() {
        return sizeUnit != null ? sizeUnit.val() : size;
    }

    public MockUnitString type(StringType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
        switch (type) {
            case HEX: return this::hex;
            case NUMBERS: return this::numbers;
            case LETTERS: return this::letters;
            case ALPHA_NUMERIC: return this::alphaNumeric;
            case SPECIAL_CHARACTERS: return this::specialChars;
            default: throw new IllegalArgumentException("Invalid StringType");
        }
    }

    public MockUnitString types(StringType... types) {
        notEmptyOrNullValues(types, "types");
        StringType type = mockNeat.from(types).val();
        return type(type);
    }

    private Supplier<String> numbers() {
        return () ->  random(getSize(), 0, 0, false, true, null, random);
    }

    private Supplier<String> letters() {
        return () -> random(getSize(), 0, 0, true, false, null, random);
    }

    private Supplier<String> alphaNumeric() {
        return () -> random(getSize(), 0, 0, true, true, null, random);
    }

    private Supplier<String> hex() {
        return () -> mockNeat.fromStrings(HEXA_STR)
                            .stream()
                            .val()
                            .limit(getSize())
                            .collect(joining());
    }

    private Supplier<String> specialChars() {
        return () -> mockNeat.fromStrings(SPECIAL_CHARACTERS_STR)
                            .stream()
                            .val()
                            .limit(getSize())
                            .collect(joining());
    }

    @Override
    public Supplier<String> supplier() {
        return () -> random(getSize(), 0, 0, true, true, null, random);
    }
}
