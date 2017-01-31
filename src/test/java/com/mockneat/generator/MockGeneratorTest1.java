package com.mockneat.generator;

import com.mockneat.generator.mockmodels.Catalog;
import com.mockneat.generator.mockmodels.Person;
import com.mockneat.sources.random.Rand;
import com.mockneat.types.Value;
import com.mockneat.types.enums.NameType;
import org.junit.Test;

import java.util.stream.IntStream;

import static com.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static com.mockneat.types.enums.StringFormatType.UPPER_CASE;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class MockGeneratorTest1 {
    @Test
    public void randomPersonCorrectValues() throws Exception {
        Rand R = new Rand();
        MockGenerator mg = MockGenerator.forClass(Person.class);
        mg.field("firstName", R.names().ofType(NameType.FIRST_NAME).format(UPPER_CASE).stream())
            .field("lastName", Value.from("Smith"))
            .field("age", R.ints().inRange(18,80).stream())
            .field("country", R.countries().names().stream())
//            .field("description", R.streamString("abc", 100))
            .field("catalog", MockGenerator.forClass(Catalog.class)
                                    .field("x", R.ints().withBound(10).stream())
                                    .field("y", R.chars().from("abc").stream())
                                    .field("z", R.ccs().ofType(AMERICAN_EXPRESS).stream()));

        IntStream.rangeClosed(0, 10).forEach((i) -> System.out.println(mg.newInstance()));

        Character c =  R.objs().from(new Character[]{'a'}).val();

      //  Character c2 = R.objs().nextObject(Arrays.asList('c'));
    }
}
