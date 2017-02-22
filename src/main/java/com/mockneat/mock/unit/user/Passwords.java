package com.mockneat.mock.unit.user;

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

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.PassStrengthType;

import java.util.List;
import java.util.function.Supplier;

import static com.mockneat.alphabets.Alphabets.*;
import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static com.mockneat.types.enums.DictType.*;
import static com.mockneat.types.enums.PassStrengthType.*;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 31/01/2017.
 */
public class Passwords implements MockUnitString {

    private MockNeat rand;

    public Passwords(MockNeat rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        PassStrengthType passStrengthType = rand.from(PassStrengthType.class).val();
        return () -> nextPassword(passStrengthType);
    }

    public MockUnitString type(PassStrengthType passStrengthType) {
        notNull(passStrengthType, INPUT_PARAMETER_NOT_NULL, "passStrengthType");
        Supplier<String> supplier = () -> nextPassword(passStrengthType);
        return () -> supplier;
    }

    public MockUnitString types(PassStrengthType... passStrengthTypes) {
        notEmpty(passStrengthTypes, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "passStrengthTypes");
        PassStrengthType passStrengthType = rand.from(passStrengthTypes).val();
        return type(passStrengthType);
    }

    protected String nextPassword(PassStrengthType passStrengthType) {
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

    protected String nextWeakPassword() {
        Integer minLength = WEAK.getLength().getLowerBound();
        Integer maxLength = WEAK.getLength().getUpperBound();
        DictType dictType = rand.from(EN_NOUN_2SYLL, EN_NOUN_1SYLL).val();
        String noun = rand.dicts().type(dictType).val();
        if (noun.length()>maxLength) {
            noun = noun.substring(0, maxLength);
        }
        StringBuilder resultBuff = new StringBuilder(noun);

        if (noun.length() < minLength) {
            // Add some objs numbers in the case the noun
            // is shorted than the minLength
            int diff = minLength - noun.length();
            while (diff-- > 0)
                resultBuff.append(rand.ints().range(0, 10).val());
        }

        return resultBuff.toString();
    }

    protected String nextMediumPassword() {
        Integer minLength = MEDIUM.getLength().getLowerBound();
        Integer maxLength = MEDIUM.getLength().getUpperBound();
        String noun = rand.dicts().type(EN_NOUN_3SYLL).val();
        if (noun.length()>maxLength) {
            noun = noun.substring(0, maxLength);
        }
        StringBuilder resultBuff = new StringBuilder(noun);

        if (noun.length() < minLength) {
            int diff = minLength - noun.length();
            while (diff-- > 0)
                resultBuff.append(rand.chars().digits().val());
        }

        // Create a objs uppercase character
        int randUpperCaseIdx = rand.ints().range(0, noun.length() - 1).val();
        char replChar = resultBuff.charAt(randUpperCaseIdx);
        resultBuff.setCharAt(randUpperCaseIdx, Character.toUpperCase(replChar));

        // Insert / Replace with a objs special character
        int randSpecialChrIdx = rand.ints().range(0, resultBuff.length()).val();
        char specialChar = rand.from(SPECIAL_CHARACTERS).val();
        if (resultBuff.length() < maxLength) {
            resultBuff.insert(randSpecialChrIdx, specialChar);
        } else {
            resultBuff.setCharAt(randSpecialChrIdx, specialChar);
        }


        return resultBuff.toString();
    }

    protected String nextStrongPassword() {
        Integer minLength = STRONG.getLength().getLowerBound();
        Integer maxLength = STRONG.getLength().getUpperBound();
        int passLength = rand.ints().range(minLength, maxLength).val();
        StringBuilder buff = new StringBuilder();
        List<Character> cAlph;
        while (passLength-- > 1) {
            cAlph = rand.from(SPECIAL_CHARACTERS, DIGITS, LETTERS).val();
            buff.append(rand.from(cAlph).val());
        }
        // Insert a special character to be 100% confident it exists
        int randSpecialChrIdx = rand.ints().range(0, buff.length()).val();
        buff.insert(randSpecialChrIdx, rand.from(SPECIAL_CHARACTERS).val());
        return buff.toString();
    }
}
