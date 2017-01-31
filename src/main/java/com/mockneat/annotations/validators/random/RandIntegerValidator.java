package com.mockneat.annotations.validators.random;

import com.mockneat.annotations.types.random.RandInteger;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 02/11/2016.
 */
public class RandIntegerValidator implements AnnotationValidator<RandInteger> {

    @Override
    public Boolean validate(RandInteger annotation, Parameter parameter) {

        runValidationsInChain(annotation,
                RandValidUtils::randSourceExists,
                RandValidUtils::notNegativeLowerBound,
                RandValidUtils::notNegativeUpperBound,
                RandValidUtils::lowerBoundLessThanUpperBound);

        return true;
    }

}
