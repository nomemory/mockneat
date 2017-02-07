package com.mockneat.generator;

import com.mockneat.generator.mockmodels.Person;
import com.mockneat.random.Rand;
import org.junit.Test;

import java.util.stream.IntStream;

import static com.mockneat.types.enums.NameType.FIRST_NAME;
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
                .field("name", r.names().type(FIRST_NAME)::val)
                .field("email", r.emails().format(UPPER_CASE)::val)
                .field("age", r.ints().range(18, 99)::val);
//                .field("description", r.markovs()::val);

        IntStream.range(0,5).forEach(i -> System.out.println(mg.newInstance()));

    }
}
