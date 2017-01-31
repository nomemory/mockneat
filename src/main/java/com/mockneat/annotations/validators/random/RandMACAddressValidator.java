package com.mockneat.annotations.validators.random;

import com.mockneat.annotations.types.random.RandMacAddress;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.reflect.Parameter;

public class RandMACAddressValidator implements AnnotationValidator<RandMacAddress> {
    @Override
    public Boolean validate(RandMacAddress annotation, Parameter parameter) {
        runValidationsInChain(annotation,
                RandValidUtils::randSourceExists);
        return true;
    }
}
