package com.mockneat.annotations.validators.random;

import com.mockneat.annotations.types.random.RandIPv4Address;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 20/12/2016.
 */
public class RandIPv4AddressValidator implements AnnotationValidator<RandIPv4Address> {
    @Override
    public Boolean validate(RandIPv4Address annotation, Parameter parameter) {
        runValidationsInChain(annotation,
                RandValidUtils::randSourceExists);

        return true;
    }
}
