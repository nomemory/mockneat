package com.mockneat;

import com.mockneat.mock.MockNeat;

import java.util.*;

import static com.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static com.mockneat.types.enums.MarkovChainType.KAFKA;
import static com.mockneat.types.enums.NameType.FIRST_NAME_FEMALE_AMERICAN;

/**
 * Created by andreinicolinciobanu on 15/01/2017.
 */
public class Main {

    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

//        List<Map<Integer, Map<String, Map<Integer, String>>>> c =
//                   m.names()
//                    .mapKeys(10, m.ints().supplier()) // Map<Integer, String>
//                    .mapKeys(10, m.strings().supplier()) //Map<String, Map<Integer, String>>
//                    .mapKeys(10, m.ints().supplier())
//                    .list(100)
//                    .val();
//

        Map<Integer, List<Elev>> elevi = m.objs(Elev.class)
                            .field("name", m.names().type(FIRST_NAME_FEMALE_AMERICAN))
                            .field("email", m.emails())
                            .field("varsta", m.ints().range(18, 90))
                            .field("creditCard", m.creditCards().type(AMERICAN_EXPRESS))
                            .map(e -> { e.setEmail("xxx"); return e; })
                            .list(LinkedList.class, 2)
                            .mapKeys(10, m.intSeq().supplier())
                            .val();

        Elev e = m.objs(Elev.class)
                .constructor(
                    m.strings(),
                    m.emails().withDomain("deloittece.com"),
                    m.ints(),
                    m.strings()
                )
                .val();

        System.out.println(e);

        System.out.println(elevi);

        List<Set<Map<Integer, Boolean>>> str = m.bools()
                                                .probability(25.0)
                                                .mapKeys(10, m.ints().range(100, 1000).supplier())
                                                .set(HashSet.class, 5)
                                                .list(10)
                                                .val();

        System.out.println(m.markovs().size(2056).type(KAFKA).val());
    }
}
