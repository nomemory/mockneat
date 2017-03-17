package net.andreinc.mockneat.github;


import net.andreinc.mockneat.MockNeat;

import static net.andreinc.mockneat.types.enums.NameType.FIRST_NAME_FEMALE;

/**
 * Created by andreinicolinciobanu on 04/03/2017.
 */
public class GenerateNames {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String firstName = mock.names().type(FIRST_NAME_FEMALE).val();
        System.out.println(firstName);

        String first = mock.names().first().val();
        System.out.println(first);

        String last = mock.names().last().val();
        System.out.println(last);

        String full = mock.names().full().val();
        System.out.println(full);

        String fullWithInitial = mock.names().full(90).val();
        System.out.println(fullWithInitial);
    }
}
