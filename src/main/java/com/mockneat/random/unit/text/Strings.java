package com.mockneat.random.unit.text;


import com.mockneat.random.Rand;
import com.mockneat.random.interfaces.RandUnitString;
import com.mockneat.types.enums.StringType;

import java.util.Random;
import java.util.function.Supplier;

import static com.mockneat.alphabets.Alphabets.HEXA_STR;
import static com.mockneat.alphabets.Alphabets.SPECIAL_CHARACTERS_STR;
import static com.mockneat.random.utils.ValidationUtils.*;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.Validate.notEmpty;

public class Strings implements RandUnitString {

    private Rand rand;
    private Random random;
    private int size = 64;

    public Strings(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    public Strings size(int size) {
        isTrue(size>0, SIZE_BIGGER_THAN_ZERO_STRICT);
        this.size = size;
        return this;
    }

    public RandUnitString type(StringType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
        switch (type) {
            case HEX: return () -> hex();
            case NUMBERS: return () ->  numbers();
            case LETTERS: return () -> letters();
            case NUMBERS_LETTERS: return () -> numbersAndLetters();
            case SPECIAL_CHARACTERS: return () -> specialChars();
            // Should never reach this
            default: return () -> numbersAndLetters();
        }
    }

    public RandUnitString types(StringType... types) {
        notEmpty(types, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        StringType type = rand.from(types).val();
        return type(type);
    }

    protected Supplier<String> numbers() {
        return () -> random(size, 0, 0, false, true, null, random);
    }

    protected Supplier<String> letters() {
        return () -> random(size, 0, 0, true, false, null, random);
    }

    protected Supplier<String> numbersAndLetters() {
        return () -> random(size, 0, 0, true, true, null, random);
    }

    protected Supplier<String> hex() {
        return () -> rand.fromStrings(HEXA_STR)
                            .stream().val()
                            .limit(size)
                            .collect(joining());
    }

    protected Supplier<String> specialChars() {
        return () -> rand.fromStrings(SPECIAL_CHARACTERS_STR)
                            .stream().val()
                            .limit(size)
                            .collect(joining());
    }

    @Override
    public Supplier<String> supplier() {
        return () -> random(size, 0, 0, true, true, null, random);
    }
}
