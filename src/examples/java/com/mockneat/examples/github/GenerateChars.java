package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateChars {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        Character chr = mock.chars().val();
        Character lowerLetter = mock.chars().lowerLetters().val();
        Character upperLetter = mock.chars().upperLetters().val();
        Character digit = mock.chars().digits().val();
        Character hex = mock.chars().hex().val();
    }
}
