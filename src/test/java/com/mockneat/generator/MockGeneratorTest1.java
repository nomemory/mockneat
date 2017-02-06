package com.mockneat.generator;

import com.mockneat.generator.mockmodels.Catalog;
import com.mockneat.generator.mockmodels.Person;
import com.mockneat.sources.random.Rand;
import com.mockneat.types.Value;
import com.mockneat.types.enums.MarkovChainType;
import com.mockneat.types.enums.NameType;
import org.junit.Test;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static com.mockneat.types.enums.NameType.FIRST_NAME;
import static com.mockneat.types.enums.StringFormatType.CAPITALIZED;
import static com.mockneat.types.enums.StringFormatType.UPPER_CASE;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class MockGeneratorTest1 {
    @Test
    public void randomPersonCorrectValues() throws Exception {
        Rand r = new Rand();

        MockGenerator mg = MockGenerator.forClass(Person.class);
        mg
                .field("name", r.names().ofType(FIRST_NAME)::val)
                .field("email", r.emails().format(UPPER_CASE)::val)
                .field("age", r.ints().inRange(18, 99)::val)
                .field("description", r.markovs()::val);

        IntStream.range(0,5).forEach(i -> System.out.println(mg.newInstance()));

    }
}
