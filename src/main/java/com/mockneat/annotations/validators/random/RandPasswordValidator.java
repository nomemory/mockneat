package com.mockneat.annotations.validators.random;

import com.mockneat.annotations.types.random.RandPassword;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.reflect.Parameter;

public class RandPasswordValidator implements AnnotationValidator<RandPassword> {

    @Override
    public Boolean validate(RandPassword annotation, Parameter parameter) {
        runValidationsInChain(annotation,
        RandValidUtils::randSourceExists);
        return true;
    }

}
