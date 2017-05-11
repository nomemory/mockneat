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
import net.andreinc.mockneat.interfaces.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.PassStrengthType;

import java.util.List;
import java.util.function.Supplier;

import static net.andreinc.mockneat.alphabets.Alphabets.*;
import static net.andreinc.mockneat.types.enums.DictType.*;
import static net.andreinc.mockneat.types.enums.PassStrengthType.*;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Passwords implements MockUnitString {

    private final MockNeat mock;

    public Passwords(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<String> supplier() {
        PassStrengthType passStrengthType = mock.from(PassStrengthType.class).val();
        return () -> nextPassword(passStrengthType);
    }

    public MockUnitString type(PassStrengthType passStrengthType) {
        notNull(passStrengthType, "passStrengthType");
        Supplier<String> supplier = () -> nextPassword(passStrengthType);
        return () -> supplier;
    }

    public MockUnitString types(PassStrengthType... types) {
        notEmptyOrNullValues(types, "types");
        PassStrengthType passStrengthType = mock.from(types).val();
        return type(passStrengthType);
    }

    private String nextPassword(PassStrengthType passStrengthType) {
        switch (passStrengthType) {
            case WEAK:
                return nextWeakPassword();
            case MEDIUM:
                return nextMediumPassword();
            case STRONG:
                return nextStrongPassword();
        }
        return "123456";
    }

    private String nextWeakPassword() {
        Integer minLength = WEAK.getLength().getLowerBound();
        Integer maxLength = WEAK.getLength().getUpperBound();
        DictType dictType = mock.from(new DictType[]{EN_NOUN_2SYLL, EN_NOUN_1SYLL}).val();
        String noun = mock.dicts().type(dictType).val();
        if (noun.length()>maxLength) {
            noun = noun.substring(0, maxLength);
        }
        StringBuilder resultBuff = new StringBuilder(noun);

        if (noun.length() < minLength) {
            // Add some objs numbers in the case the noun
            // is shorted than the minLength
            int diff = minLength - noun.length();
            while (diff-- > 0)
                resultBuff.append(mock.ints().range(0, 10).val());
        }

        return resultBuff.toString();
    }

    private String nextMediumPassword() {
        Integer minLength = MEDIUM.getLength().getLowerBound();
        Integer maxLength = MEDIUM.getLength().getUpperBound();
        String noun = mock.dicts().type(EN_NOUN_3SYLL).val();
        if (noun.length()>maxLength) {
            noun = noun.substring(0, maxLength);
        }
        StringBuilder resultBuff = new StringBuilder(noun);

        if (noun.length() < minLength) {
            int diff = minLength - noun.length();
            while (diff-- > 0)
                resultBuff.append(mock.chars().digits().val());
        }

        // Create a objs uppercase character
        int randUpperCaseIdx = mock.ints().range(0, noun.length() - 1).val();
        char replChar = resultBuff.charAt(randUpperCaseIdx);
        resultBuff.setCharAt(randUpperCaseIdx, Character.toUpperCase(replChar));

        // Insert / Replace with a objs special character
        int randSpecialChrIdx = mock.ints().range(0, resultBuff.length()).val();
        char specialChar = mock.from(SPECIAL_CHARACTERS).val();
        if (resultBuff.length() < maxLength) {
            resultBuff.insert(randSpecialChrIdx, specialChar);
        } else {
            resultBuff.setCharAt(randSpecialChrIdx, specialChar);
        }


        return resultBuff.toString();
    }

    @SuppressWarnings("unchecked")
    private String nextStrongPassword() {
        Integer minLength = STRONG.getLength().getLowerBound();
        Integer maxLength = STRONG.getLength().getUpperBound();
        int passLength = mock.ints().range(minLength, maxLength).val();
        StringBuilder buff = new StringBuilder();
        List<Character> cAlph;
        while (passLength-- > 1) {
            cAlph = mock.from(new List[]{SPECIAL_CHARACTERS, DIGITS, LETTERS}).val();
            buff.append(mock.from(cAlph).val());
        }
        // Insert a special character to be 100% confident it exists
        int randSpecialChrIdx = mock.ints().range(0, buff.length()).val();
        buff.insert(randSpecialChrIdx, mock.from(SPECIAL_CHARACTERS).val());
        return buff.toString();
    }
}
