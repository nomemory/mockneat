package net.andreinc.mockneat.github;


import net.andreinc.mockneat.MockNeat;

import static net.andreinc.mockneat.types.enums.PassStrengthType.MEDIUM;

/**
 * Created by andreinicolinciobanu on 04/03/2017.
 */
public class GeneratePasswords {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String medium = mock.passwords().type(MEDIUM).val();
        System.out.println(medium);
    }
}
