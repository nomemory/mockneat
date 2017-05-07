package net.andreinc.mockneat.github;


import net.andreinc.mockneat.MockNeat;

import static net.andreinc.mockneat.types.enums.DictType.COUNTRY_NAME;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateDicts {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        System.out.println(mock.dicts().type(COUNTRY_NAME).val());
    }
}
