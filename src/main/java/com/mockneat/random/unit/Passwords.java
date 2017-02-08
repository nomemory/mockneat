package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.PassStrengthType;

import java.util.function.Supplier;

import static com.mockneat.alphabets.Alphabets.SPECIAL_CHARACTERS;
import static com.mockneat.random.RandConstants.POSSIBLE_CHARACTERS;
import static com.mockneat.types.enums.DictType.EN_NOUN_3SYLL;
import static com.mockneat.types.enums.PassStrengthType.MEDIUM_PASSWORD;
import static com.mockneat.types.enums.PassStrengthType.STRONG_PASSWORD;
import static com.mockneat.types.enums.PassStrengthType.WEAK_PASSWORD;
import static com.mockneat.utils.CheckUtils.checkPasswordStrengthType;

/**
 * Created by andreinicolinciobanu on 31/01/2017.
 */
public class Passwords implements RandUnitString {

    private Rand rand;

    public Passwords(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        PassStrengthType passStrengthType = rand.objs().from(PassStrengthType.class).val();
        return () -> nextPassword(passStrengthType);
    }

    public RandUnitString type(PassStrengthType passStrengthType) {
        Supplier<String> supplier = () -> nextPassword(passStrengthType);
        return () -> supplier;
    }

    public RandUnitString types(PassStrengthType... passStrengthTypes) {
        PassStrengthType passStrengthType = rand.objs().from(passStrengthTypes).val();
        return type(passStrengthType);
    }

    protected String nextPassword(PassStrengthType passStrengthType) {
        checkPasswordStrengthType(passStrengthType);
        switch (passStrengthType) {
            case WEAK_PASSWORD:
                return nextWeakPassword();
            case MEDIUM_PASSWORD:
                return nextMediumPassword();
            case STRONG_PASSWORD:
                return nextStrongPassword();
        }
        return "123456";
    }

    protected String nextWeakPassword() {
        Integer minLength = WEAK_PASSWORD.getLength().getLowerBound();
        Integer maxLength = WEAK_PASSWORD.getLength().getUpperBound();
        String noun = rand.dicts().type(DictType.EN_NOUN_2SYLL).val();
        noun = noun.substring(0, maxLength);
        StringBuilder resultBuff = new StringBuilder(noun);

        if (noun.length() < minLength) {
            // Add some random numbers in the case the noun
            // is shorted than the minLength
            int diff = minLength - noun.length();
            while (diff-- > 0)
                resultBuff.append(rand.ints().range(0, 10).val());
        }

        return resultBuff.toString();
    }

    protected String nextMediumPassword() {
        Integer minLength = MEDIUM_PASSWORD.getLength().getLowerBound();
        Integer maxLength = MEDIUM_PASSWORD.getLength().getUpperBound();
        String noun = rand.dicts().type(EN_NOUN_3SYLL).val();
        StringBuilder resultBuff = new StringBuilder(noun);

        if (noun.length() < minLength) {
            int diff = minLength - noun.length();
            while (diff-- > 0)
                resultBuff.append(rand.ints().range(0, 10).val());
        }

        int randUpperCase = rand.ints().range(0, noun.length() - 1).val();
        char replChar = resultBuff.charAt(randUpperCase);

        resultBuff.setCharAt(randUpperCase, Character.toUpperCase(replChar));

        if (resultBuff.length() < maxLength) {
            int randSpecialChrIdx = rand.ints().range(0, resultBuff.length()).val();
            resultBuff.insert(randSpecialChrIdx,
                    rand.objs().from(SPECIAL_CHARACTERS).val());
        }

        return resultBuff.toString();
    }

    protected String nextStrongPassword() {
        Integer minLength = STRONG_PASSWORD.getLength().getLowerBound();
        Integer maxLength = STRONG_PASSWORD.getLength().getUpperBound();
        int passLength = rand.ints().range(minLength, maxLength).val();
        StringBuilder buff = new StringBuilder();
        while (passLength-- > 0) {
            buff.append(rand.objs().from(POSSIBLE_CHARACTERS).val());
        }
        return buff.toString();
    }
}
