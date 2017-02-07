package com.mockneat.random;

import java.util.ArrayList;
import java.util.List;
import static com.mockneat.alphabets.Alphabets.*;
import static java.util.Collections.unmodifiableList;

/**
 * Created by andreinicolinciobanu on 03/01/2017.
 */
public class RandConstants {

    protected static final List<Character> POSSIBLE_CHARACTERS_MUTABLE = new ArrayList<>();

    static {
        POSSIBLE_CHARACTERS_MUTABLE.addAll(LETTERS_LOWERCASE);
        POSSIBLE_CHARACTERS_MUTABLE.addAll(LETTERS_UPPERCASE);
        POSSIBLE_CHARACTERS_MUTABLE.addAll(DIGITS);
        POSSIBLE_CHARACTERS_MUTABLE.addAll(SPECIAL_CHARACTERS);
    }

    public static final List<Character> POSSIBLE_CHARACTERS = unmodifiableList(POSSIBLE_CHARACTERS_MUTABLE);


}
