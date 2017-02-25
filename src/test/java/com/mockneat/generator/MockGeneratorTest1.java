package com.mockneat.generator;

import com.mockneat.generator.mockmodels.Catalog;
import com.mockneat.generator.mockmodels.Customer;
import com.mockneat.generator.mockmodels.Person;
import com.mockneat.mock.MockNeat;
import org.junit.Test;

import java.util.List;
import java.util.function.Supplier;

import static com.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static com.mockneat.types.enums.IPv4Type.CLASS_B;
import static com.mockneat.types.enums.MarkovChainType.KAFKA;
import static com.mockneat.types.enums.NameType.FIRST_NAME_MALE_AMERICAN;
import static com.mockneat.types.enums.StringFormatType.CAPITALIZED;
import static com.mockneat.types.enums.UserNameType.ADJECTIVE_NOUN;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class MockGeneratorTest1 {
    @Test
    public void randomPersonCorrectValues() throws Exception {
        MockNeat r = MockNeat.threadLocal();

        Supplier<String> names = r.names().supplier();

        Person p =
            r.objs(Person.class)
            .field("name", r.names())
            .field("email", r.emails())
            .field("age", 10)
            .field("description", r.strings().size(64))
            .field("catalog", r.objs(Catalog.class)
                                .field("x", r.ints())
                                .field("y", r.chars().digits())
                                .field("z", r.strings().size(128)))
            .field("integers", r.ints().list(10))
            .field("map", r.strings().mapVals(10, r.ints()::val))
            .val();

        Person p2 =
                r.objs(Person.class)
                 .constructor(r.names(), r.emails(), r.ints().range(18, 80))
                 .val();

        System.out.println(p);
        System.out.println(p2);


        List<Customer> customers = r.objs(Customer.class)
                                        .field("UUID", r.uuids())
                                        .field("firstName", r.names()
                                                             .type(FIRST_NAME_MALE_AMERICAN)
                                                             .format(CAPITALIZED))
                                        .field("lastName", r.names())
                                        .field("userName", r.users().type(ADJECTIVE_NOUN))
                                        .field("description", r.markovs().type(KAFKA))
                                        .field("country", r.countries().names())
                                        .field("lastIpAddress", r.ipv4s().type(CLASS_B))
                                        .field("registeredMacAddress", r.macs())
                                        .field("creditCardsCVVs", r.creditCards()
                                                                   .type(AMERICAN_EXPRESS)
                                                                   .mapVals(10, r.cvvs()::val))
                                        .list(100)
                                        .val();
        System.out.println(customers);
    }
}
