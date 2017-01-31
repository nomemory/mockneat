package com.mockneat.annotations.validators.random;

import com.mockneat.annotations.types.random.RandEmail;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 29/12/2016.
 */
public class RandEmailValidator implements AnnotationValidator<RandEmail> {
    @Override
    public Boolean validate(RandEmail annotation, Parameter parameter) {
        runValidationsInChain(annotation,
                RandValidUtils::randSourceExists);
        return true;
    }
}
