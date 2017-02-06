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

//        MockGenerator mg = MockGenerator.forClass(Person.class);
//
//        mg.field("name", r.names().ofType(FIRST_NAME)::val)
//                .field("email", r.emails().format(UPPER_CASE)::val)
//                .field("age", r.ints().inRange(18, 90)::val)
//                .field("catalog", MockGenerator.forClass(Catalog.class)
//                                                .field("x", r.ints()::val)
//                                                .field("y", r.chars().lowerLetters()::val)
//                                                .field("z", r.emails()::val))
//                .field("integers", r.ints().list(2)::val)
//                .field("map", r.ccs().ofType(AMERICAN_EXPRESS).mapWithValues(2, r.ints()::val)::val);
//
//        IntStream.range(0,100).forEach(i -> System.out.println(mg.newInstance().get()));
//
//        r.emails().val();
//        r.emails().list(10).val();
        //System.out.println(r.emails().format(UPPER_CASE).list(10).mapWithKeys(10, r.ints()::val).val());
        //System.out.println(r.emails().format(CAPITALIZED).list(3).mapWithValues(10, r.doubles().inRange(10.0, 20.0)::val).val());
        //System.out.println(r.ccs().ofType(AMERICAN_EXPRESS).cut(10).list(5).list(5).list(5).val());
        //System.out.println(r.chars().letters().list(20).stream().limit(5).map(l -> l+"").collect(Collectors.toList()));

        MockGenerator mg = MockGenerator.forClass(Person.class);
        mg
                .field("name", r.names().ofType(FIRST_NAME)::val)
                .field("email", r.emails().format(UPPER_CASE)::val)
                .field("age", r.ints().inRange(18, 99)::val);

        IntStream.range(0,5).forEach(i -> System.out.println(mg.newInstance()));

    }
}
