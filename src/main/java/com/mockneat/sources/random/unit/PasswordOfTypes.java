package com.mockneat.sources.random.unit;

import com.mockneat.sources.alphabets.Alphabets;
import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.PassStrengthType;
import com.mockneat.utils.NextUtils;

import static com.mockneat.sources.random.RandConstants.POSSIBLE_CHARACTERS;
import static com.mockneat.types.enums.PassStrengthType.WEAK_PASSWORD;
import static com.mockneat.utils.NextUtils.checkTypes;

/**
 * Created by andreinicolinciobanu on 31/01/2017.
 */
public class PasswordOfTypes implements RandUnitGeneric<String> {

    private Rand rand;
    private PassStrengthType[] types;

    public PasswordOfTypes(Rand rand, PassStrengthType... types) {
        this.rand = rand;
        this.types = types;
    }

    @Override
    public String val() {
        checkTypes(types);
        PassStrengthType passStrengthType = rand.objs().from(types).val();
        switch (passStrengthType) {
            case WEAK_PASSWORD : return nextWeakPassword();
            case MEDIUM_PASSWORD: return nextMediumPassword();
            case STRONG_PASSWORD: return nextStrongPassword();
            default: return "123456";
        }
    }

    protected String nextWeakPassword() {
        Integer minLength = WEAK_PASSWORD.getLength().getLowerBound();
        Integer maxLength = WEAK_PASSWORD.getLength().getUpperBound();
        String noun = rand.dicts().from(DictType.EN_NOUN_2SYLL).val();
        noun = noun.substring(0, maxLength);
        StringBuilder resultBuff = new StringBuilder(noun);

        if (noun.length() < minLength) {
            // Add some random numbers in the case the noun
            // is shorted than the minLength
            int diff = minLength - noun.length();
            while (diff-- > 0)
                resultBuff.append(rand.ints().inRange(0, 10).val());
        }

        return resultBuff.toString();
    }

    protected String nextMediumPassword() {
        Integer minLength = PassStrengthType.MEDIUM_PASSWORD.getLength().getLowerBound();
        Integer maxLength = PassStrengthType.MEDIUM_PASSWORD.getLength().getUpperBound();
        String noun = rand.dicts().from(DictType.EN_NOUN_3SYLL).val();
        StringBuilder resultBuff = new StringBuilder(noun);

        if (noun.length() < minLength) {
            int diff = minLength - noun.length();
            while (diff-- > 0)
                resultBuff.append(rand.ints().inRange(0, 10).val());
        }

        int randUpperCase = rand.ints().inRange(0, noun.length() - 1).val();
        char replChar = resultBuff.charAt(randUpperCase);

        resultBuff.setCharAt(randUpperCase, Character.toUpperCase(replChar));

        if (resultBuff.length() < maxLength) {
            int randSpecialChrIdx = rand.ints().inRange(0, resultBuff.length()).val();
            resultBuff.insert(randSpecialChrIdx,
                    rand.objs().from(Alphabets.SPECIAL_CHARACTERS_ARR_CHR).val());
        }

        return resultBuff.toString();
    }

    protected String nextStrongPassword() {
        Integer minLength = PassStrengthType.STRONG_PASSWORD.getLength().getLowerBound();
        Integer maxLength = PassStrengthType.STRONG_PASSWORD.getLength().getUpperBound();
        int passLength = rand.ints().inRange(minLength, maxLength).val();
        StringBuilder buff = new StringBuilder();
        Character[] alphabet;
        while (passLength-- > 0) {
            alphabet = rand.objs().from(POSSIBLE_CHARACTERS).val();
            buff.append(rand.objs().from(alphabet).val());
        }
        return buff.toString();
    }
}
