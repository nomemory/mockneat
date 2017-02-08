package com.mockneat.generator;

import com.mockneat.generator.mockmodels.Person;
import com.mockneat.random.Rand;
import com.mockneat.types.Pair;
import org.junit.Test;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class MockGeneratorTest1 {
    @Test
    public void randomPersonCorrectValues() throws Exception {
        Rand r = new Rand();

        System.out.println(r.names().val());

        Person p = r.compose(
                Pair.of(r.names()::val, String.class),
                Pair.of(r.emails()::val, String.class),
                Pair.of(r.ints().range(18,89)::val, Integer.class)
        ).object(Person.class).val();

        System.out.println(p);
    }
}
