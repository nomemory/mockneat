package com.mockneat.alphabets;

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
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.apache.commons.lang3.ArrayUtils.addAll;

public class Alphabets {

    /**
     * ---------------------------------------------------------
     * SPECIAL CHARACTERS
     * ---------------------------------------------------------
     */
    private static final Character[] SPECIAL_CHARACTERS_ARR_CHR = {
            '!', '@', '#', '$', '%', '^', '&', '(', ')', '+', '=',
            '`', '~', '[', '{', '}', ']', ':', ';', '.', '\'', '"',
            '|', '<', '>', '?', '/'
    };
    public static final List<Character> SPECIAL_CHARACTERS =
            unmodifiableList(asList(SPECIAL_CHARACTERS_ARR_CHR));


    private static final String[] SPECIAL_CHARACTERS_ARR_STR = {
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
    private static final Character[] LETTERS_LOWERCASE_ARR_CHR = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };
    public static final List<Character> LETTERS_LOWERCASE =
            unmodifiableList(asList(LETTERS_LOWERCASE_ARR_CHR));

    private static final String[] LETTERS_LOWERCASE_ARR_STR = {
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
    private static final Character[] LETTERS_UPPERCASE_ARR_CHR = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    public static final List<Character> LETTERS_UPPERCASE =
            unmodifiableList(asList(LETTERS_UPPERCASE_ARR_CHR));

    private static final String[] LETTERS_UPPERCASE_ARR_STR = {
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
    private static final Character[] DIGITS_ARR_CHR = {
            '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9'
    };

    public static final List<Character> DIGITS =
            unmodifiableList(asList(DIGITS_ARR_CHR));

    private static final String[] DIGITS_ARR_STR = {
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
    private static final Character[] LETTERS_ARR_CHR = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static final List<Character> LETTERS =
            unmodifiableList(asList(LETTERS_ARR_CHR));

    private static final String[] LETTERS_ARR_STR = {
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
    private static final Character[] ALPHA_NUMERIC_ARR_CHR =
            addAll(addAll(LETTERS_LOWERCASE_ARR_CHR, LETTERS_UPPERCASE_ARR_CHR), DIGITS_ARR_CHR);

    public static final List<Character> ALPHA_NUMERIC =
            unmodifiableList(asList(ALPHA_NUMERIC_ARR_CHR));

    private static final String[] ALPHA_NUMERIC_ARR_STR =
            addAll(addAll(LETTERS_LOWERCASE_ARR_STR, LETTERS_UPPERCASE_ARR_STR), DIGITS_ARR_STR);

    public static final List<String> ALPHA_NUMERIC_STR =
            unmodifiableList(asList(ALPHA_NUMERIC_ARR_STR));

    private Alphabets() {}

    /**
     * ---------------------------------------------------------
     * HEXA LOWER
     * ---------------------------------------------------------
     */
    private static final Character[] HEXA_UPPER_ARR_CHR = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'
    };

    public static final List<Character> HEXA_UPPER =
            unmodifiableList(asList(HEXA_UPPER_ARR_CHR));

    private static final String[] HEXA_UPPER_ARR_STR = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F"
    };

    public static final List<String> HEXA_UPPER_STR =
            unmodifiableList(asList(HEXA_UPPER_ARR_STR));

    /**
     * ---------------------------------------------------------
     * HEXA
     * ---------------------------------------------------------
     */
    private static final Character[] HEXA_ARR_CHR = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static final List<Character> HEXA =
            unmodifiableList(asList(HEXA_ARR_CHR));

    private static final String[] HEXA_ARR_STR = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f"
    };

    public static final List<String> HEXA_STR =
            unmodifiableList(asList(HEXA_ARR_STR));
}
