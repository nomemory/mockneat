package com.mockneat.alphabets;

import java.util.List;

import static java.util.Arrays.asList;
import static com.mockneat.utils.ArrayUtils.concat;
import static java.util.Collections.unmodifiableList;

public class Alphabets {

    /**
     * ---------------------------------------------------------
     * SPECIAL CHARACTERS
     * ---------------------------------------------------------
     */
    protected static final Character[] SPECIAL_CHARACTERS_ARR_CHR = {
            '!', '@', '#', '$', '%', '^', '&', '(', ')', '+', '=',
            '`', '~', '[', '{', '}', ']', ':', ';', '.', '\'', '"',
            '|', '<', '>', '?', '/'
    };
    public static final List<Character> SPECIAL_CHARACTERS =
            unmodifiableList(asList(SPECIAL_CHARACTERS_ARR_CHR));


    protected static final String[] SPECIAL_CHARACTERS_ARR_STR = {
            "!", "@", "#", "$", "%", "^", "&", "(", ")", "+", "=",
            "`", "~", "[", "{", "}", "]", ":", ";", ".", "'", "\"",
            "|", "<", ">", "?", "/"
    };
    public static final List<String> SPECIAL_CHARACTERS_STR
            = unmodifiableList(asList(SPECIAL_CHARACTERS_ARR_STR));

    /**
     * ---------------------------------------------------------
     * LOWERCASE LETTERS
     * ---------------------------------------------------------
     */
    protected static final Character[] LETTERS_LOWERCASE_ARR_CHR = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };
    public static final List<Character> LETTERS_LOWERCASE =
            unmodifiableList(asList(LETTERS_LOWERCASE_ARR_CHR));

    protected static final String[] LETTERS_LOWERCASE_ARR_STR = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"
    };

    public static final List<String> LETTER_LOWERCASE_LST_STR =
            unmodifiableList(asList(LETTERS_LOWERCASE_ARR_STR));


    /**
     * ---------------------------------------------------------
     * UPPERCASE LETTERS
     * ---------------------------------------------------------
     */
    protected static final Character[] LETTERS_UPPERCASE_ARR_CHR = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    public static final List<Character> LETTERS_UPPERCASE =
            unmodifiableList(asList(LETTERS_UPPERCASE_ARR_CHR));

    protected static final String[] LETTERS_UPPERCASE_ARR_STR = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    public static final List<String> LETTERS_UPPERCASE_STR =
            unmodifiableList(asList(LETTERS_UPPERCASE_ARR_STR));


    /**
     * ---------------------------------------------------------
     * DIGITS
     * ---------------------------------------------------------
     */
    protected static final Character[] DIGITS_ARR_CHR = {
            '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9'
    };

    public static final List<Character> DIGITS =
            unmodifiableList(asList(DIGITS_ARR_CHR));

    protected static final String[] DIGITS_ARR_STR = {
            "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9"
    };

    public static final List<String> DIGITS_STR =
            unmodifiableList(asList(DIGITS_ARR_STR));


    /**
     * ---------------------------------------------------------
     *  LETTERS
     * ---------------------------------------------------------
     */
    protected static final Character[] LETTERS_ARR_CHR = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static final List<Character> LETTERS =
            unmodifiableList(asList(LETTERS_ARR_CHR));

    protected static final String[] LETTERS_ARR_STR = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"
    };

    public static final List<String> LETTERS_STR =
            unmodifiableList(asList(LETTERS_ARR_STR));

    /**
     * ---------------------------------------------------------
     * ALPHA NUMERIC
     * ---------------------------------------------------------
     */
    protected static final Character[] ALPHA_NUMERIC_ARR_CHR =
            concat(concat(LETTERS_LOWERCASE_ARR_CHR, LETTERS_UPPERCASE_ARR_CHR), DIGITS_ARR_CHR);

    public static final List<Character> ALPHA_NUMERIC =
            unmodifiableList(asList(ALPHA_NUMERIC_ARR_CHR));

    protected static final String[] ALPHA_NUMERIC_ARR_STR =
            concat(concat(LETTERS_LOWERCASE_ARR_STR, LETTERS_UPPERCASE_ARR_STR), DIGITS_ARR_STR);

    public static final List<String> ALPHA_NUMERIC_STR =
            unmodifiableList(asList(ALPHA_NUMERIC_ARR_STR));

    private Alphabets() {}
}
