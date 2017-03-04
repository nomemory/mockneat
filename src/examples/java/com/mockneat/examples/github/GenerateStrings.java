package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

import static com.mockneat.types.enums.StringType.LETTERS;

/**
 * Created by andreinicolinciobanu on 04/03/2017.
 */
public class GenerateStrings {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String str1 = mock.strings().size(15).val();
        System.out.println(str1);

        String onlyLetters = mock.strings().size(5).type(LETTERS).val();
    }
}
