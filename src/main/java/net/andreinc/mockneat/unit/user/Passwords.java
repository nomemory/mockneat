package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.PassStrengthType;

import java.util.List;
import java.util.function.Supplier;

import static java.lang.Character.toUpperCase;
import static java.util.Arrays.asList;
import static net.andreinc.mockneat.alphabets.Alphabets.*;
import static net.andreinc.mockneat.types.enums.DictType.*;
import static net.andreinc.mockneat.types.enums.PassStrengthType.*;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Passwords extends MockUnitBase implements MockUnitString {

    /**
     * <p>Returns a {@code Passwords} object that can be used to generate arbitrary user passwords.</p>
     *
     * @return A re-usable {@code Passwords} object. The {@code Passwords} class implements {@code MockUnitString}.
     */
    public static Passwords passwords() {
        return MockNeat.threadLocal().passwords();
    }

    public Passwords(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        PassStrengthType passStrengthType = mockNeat.from(PassStrengthType.class).val();
        return () -> nextPassword(passStrengthType);
    }

    /**
     * <p>This method returns a new {@code MockUnitString} that can be used to generate passwords of a certain type.</p>
     *
     * @param passStrengthType The type of password.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString type(PassStrengthType passStrengthType) {
        notNull(passStrengthType, "passStrengthType");
        Supplier<String> supplier = () -> nextPassword(passStrengthType);
        return () -> supplier;
    }

    /**
     * <p>This method returns a new {@code MockUnitString} that can be used to generate passwords of certain type(s).</p>
     *
     * @param types A var-arg array containing the selected password types.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString types(PassStrengthType... types) {
        notEmptyOrNullValues(types, "types");
        Supplier<String> supplier = () -> {
            PassStrengthType passStrengthType = mockNeat.from(types).val();
            return type(passStrengthType).get();
        };
        return () -> supplier;
    }

    /**
     * <p>This method returns a new {@code MockUnitString} that can be used to generate weak passwords (short english nouns).</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString weak() {
        return type(WEAK);
    }

    /**
     * <p>This method returns a new {@code MockUnitString} that can be used to generate passwords with a medium security risk.</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString medium() {
        return type(MEDIUM);
    }

    /**
     * <p>This method returns a new {@code MockUnitString} that can be used to generate strong passwords.</p>
     *
     * @return A new {@code MockUnitString}.
     */
    //TODO document
    public MockUnitString strong() {
        return type(STRONG);
    }

    private String nextPassword(PassStrengthType passStrengthType) {
        switch (passStrengthType) {
            case WEAK:
                return nextWeakPassword();
            case MEDIUM:
                return nextMediumPassword();
            case STRONG:
                return nextStrongPassword();
            default: throw new IllegalArgumentException("Invalid PassStrengthType");
        }
    }

    private String nextWeakPassword() {
        Integer minLength = WEAK.getLength().getLowerBound();
        Integer maxLength = WEAK.getLength().getUpperBound();
        DictType dictType = mockNeat.from(new DictType[]{EN_NOUN_2SYLL, EN_NOUN_1SYLL}).val();
        String noun = mockNeat.dicts().type(dictType).val();
        if (noun.length()>maxLength) {
            noun = noun.substring(0, maxLength);
        }
        StringBuilder resultBuff = new StringBuilder(noun);

        if (noun.length() < minLength) {
            // Add some objs numbers in the case the noun
            // is shorted than the minLength
            int diff = minLength - noun.length();
            while (diff-- > 0)
                resultBuff.append(mockNeat.ints().range(0, 10).val());
        }

        return resultBuff.toString();
    }

    private String nextMediumPassword() {
        Integer minLength = MEDIUM.getLength().getLowerBound();
        Integer maxLength = MEDIUM.getLength().getUpperBound();
        String noun = mockNeat.dicts().type(EN_NOUN_3SYLL).val();
        if (noun.length()>maxLength) {
            noun = noun.substring(0, maxLength);
        }
        StringBuilder resultBuff = new StringBuilder(noun);

        if (noun.length() < minLength) {
            int diff = minLength - noun.length();
            while (diff-- > 0)
                resultBuff.append(mockNeat.chars().digits().val());
        }

        // Create a objs uppercase character
        int randUpperCaseIdx = mockNeat.ints().range(0, noun.length() - 1).val();
        char replChar = resultBuff.charAt(randUpperCaseIdx);
        resultBuff.setCharAt(randUpperCaseIdx, toUpperCase(replChar));

        // Insert / Replace with a objs special character
        int randSpecialChrIdx = mockNeat.ints().range(0, resultBuff.length()).val();
        char specialChar = mockNeat.from(SPECIAL_CHARACTERS).val();
        if (resultBuff.length() < maxLength) {
            resultBuff.insert(randSpecialChrIdx, specialChar);
        } else {
            resultBuff.setCharAt(randSpecialChrIdx, specialChar);
        }


        return resultBuff.toString();
    }
    
    private String nextStrongPassword() {
        Integer minLength = STRONG.getLength().getLowerBound();
        Integer maxLength = STRONG.getLength().getUpperBound();
        int passLength = mockNeat.ints().range(minLength, maxLength).val();
        StringBuilder buff = new StringBuilder();
        List<Character> cAlph;
        List<List<Character>> lists = asList(SPECIAL_CHARACTERS, DIGITS, LETTERS);
        while (passLength-- > 1) {
            cAlph = mockNeat.from(lists).val();
            buff.append(mockNeat.from(cAlph).val());
        }
        // Insert a special character to be 100% confident it exists
        int randSpecialChrIdx = mockNeat.ints().range(0, buff.length()).val();
        buff.insert(randSpecialChrIdx, mockNeat.from(SPECIAL_CHARACTERS).val());
        return buff.toString();
    }
}
