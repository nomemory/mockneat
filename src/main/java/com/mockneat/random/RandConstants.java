package com.mockneat.random;

import com.mockneat.alphabets.Alphabets;

/**
 * Created by andreinicolinciobanu on 03/01/2017.
 */
public interface RandConstants {

    Character[][] POSSIBLE_CHARACTERS = {
            Alphabets.LETTERS_LOWERCASE_ARR_CHR,
            Alphabets.LETTERS_UPPERCASE_ARR_CHR,
            Alphabets.DIGITS_ARR_CHR,
            Alphabets.SPECIAL_CHARACTERS_ARR_CHR
    };

}
