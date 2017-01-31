package com.mockneat.annotations.validators.random;

import com.mockneat.annotations.types.random.RandCVV;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.reflect.Parameter;

public class RandCVVValidator implements AnnotationValidator<RandCVV> {

    @Override
    public Boolean validate(RandCVV annotation, Parameter parameter) {
        runValidationsInChain(annotation,
                RandValidUtils::randSourceExists);

        return true;
    }

}
