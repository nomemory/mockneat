package com.mockneat.annotations.strategies.random;

import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.sources.random.Rand;
import com.mockneat.annotations.types.random.RandInteger;
import com.mockneat.annotations.generator.SourcesRepository;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 03/11/2016.
 */
public class RandIntegerStrategy implements Strategy<RandInteger> {

    @Override
    public Object generate(Parameter p, RandInteger a) {
        Rand rand = SourcesRepository.getInstance().getRandSource(a.randSource());

        int lowerBound = a.lowerBound();
        int upperBound = a.upperBound();
        int[] fromValues = a.fromValues();

        // If fromValues is not empty override lowerBound / upperBound constraints
        // and obtain the random number from the int[] array supplied in the annotation
        if (0 != fromValues.length) {
            return rand.ints().from(fromValues).val();
        } else {
            return rand.ints().inRange(lowerBound, upperBound).val();
        }
    }

}
