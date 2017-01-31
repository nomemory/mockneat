package com.mockneat.sources.random;

import com.mockneat.sources.alphabets.Alphabets;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.PassStrengthType;

import java.util.Random;
import java.util.stream.Stream;

import static com.mockneat.sources.random.RandConstants.POSSIBLE_CHARACTERS;

public class NextPassword {

    private Rand rand;
    private Random random;

    protected NextPassword(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    /**
     * Generate a random password
     * @param type The type of the password we want to generate
     * @return
     */
    protected String nextPassword(PassStrengthType type) {
        if (type == PassStrengthType.WEAK_PASSWORD)
            return nextWeakPassword();
        else if (type == PassStrengthType.MEDIUM_PASSWORD)
            return nextMediumPassword();
        else if (type == PassStrengthType.STRONG_PASSWORD)
            return nextStrongPassword();
        else throw new IllegalArgumentException("Password type doesn't exist:" + type);
    }

    /**
     * Generate a names of random passwords
     * @param type The type of the password we want to generate
     * @return
     */
    protected Stream<String> streamPassword(PassStrengthType type) {
        return Stream.generate(() -> nextPassword(type));
    }

    /**
     * Generate a weak random password
     * @return
     */
    protected String nextWeakPassword() {
        Integer minLength = PassStrengthType.WEAK_PASSWORD.getLength().getLowerBound();
        Integer maxLength = PassStrengthType.WEAK_PASSWORD.getLength().getUpperBound();
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

    /**
     * Generate a Stream of weak random passwords
     * @return
     */
    protected Stream<String> streamWeakPassword() {
        return Stream.generate(() -> nextWeakPassword());
    }

    /**
     * Generate a random medium password
     * @return
     */
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

    /**
     * Generate a names of random medium passwords
     * @return
     */
    protected Stream<String> streamMediumPassword() {
        return Stream.generate(() -> nextMediumPassword());
    }

    /**
     * Generate a random strong password
     * @return
     */
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

    /**
     * Generate a names random strong passwords
     * @return
     */
    protected Stream<String> streamStrongPassword() {
        return Stream.generate(() -> nextStrongPassword());
    }
}
