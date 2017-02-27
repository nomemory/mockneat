package com.mockneat.readmegithub;

import com.mockneat.mock.MockNeat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static com.mockneat.types.enums.CreditCardType.MASTERCARD;
import static com.mockneat.types.enums.MarkovChainType.KAFKA;
import static com.mockneat.types.enums.StringType.HEX;

/**
 * Created by andreinicolinciobanu on 22/02/2017.
 */
public class Example01 {

    public void Example01() {

        // Create a instance of the mocking object
        MockNeat m = MockNeat.threadLocal();

        // Generate a list of 10 credit card numbers.
        // Limit the types of credit cards to AMEX and MasterCard.
        List<String> creditCards =
                m.creditCards()
                    .types(AMERICAN_EXPRESS, MASTERCARD)
                    .list(10)
                    .val();
        System.out.println(creditCards);

        // Generate a complex data structure
        List<Map<String, Map<List<String>, Set<Integer>>>> complexStructure
                    = m.ints().bound(10)
                        // Create a Set<Integer>
                        .set(10)
                        // Create a Map<List<String>, Set<Integer>>
                        .mapKeys(20, m.strings().size(3).list(10)::val)
                        // Creates a Map<String, Map<List<String>, Set<Integer>>>
                        .mapKeys(10, m.strings().size(5).type(HEX)::val) //
                        // Creates a List<Map<String, Map<List<String>, Set<Integer>>>>
                        .list(ArrayList.class, 100)
                        .val();
        System.out.println(complexStructure);

        // Generate markov text based on the literature of
        // Franz Kafk (It's experimental).
        String literature = m.markovs().size(1024).type(KAFKA).val();
        System.out.println(literature);


    }
}
