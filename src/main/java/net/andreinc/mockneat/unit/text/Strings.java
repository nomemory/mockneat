package net.andreinc.mockneat.unit.text;

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

    public static Strings strings() {
        return MockNeat.threadLocal().strings();
    }

    protected Strings() {
        super();
        this.random = mockNeat.getRandom();
    }

    public Strings(MockNeat mockNeat) {
        super(mockNeat);
        this.random = mockNeat.getRandom();
    }

    public Strings size(int size) {
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
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
        return () -> {
            StringType type = mockNeat.from(types).val();
            return type(type).supplier();
        };
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
