package com.mockneat.annotations.validators.random;

import com.mockneat.annotations.types.random.RandDict;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.reflect.Parameter;

public class RandDictValidator implements AnnotationValidator<RandDict> {

    @Override
    public Boolean validate(RandDict annotation, Parameter parameter) {

        runValidationsInChain(annotation,
                RandValidUtils::randSourceExists);

        return true;
    }
}
