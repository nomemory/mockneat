package com.mockneat.annotations.validators.random;

import com.mockneat.annotations.types.random.RandUserName;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 29/12/2016.
 */
public class RandUserNameValidator implements AnnotationValidator<RandUserName> {

    @Override
    public Boolean validate(RandUserName annotation, Parameter parameter) {
        runValidationsInChain(annotation,
                RandValidUtils::randSourceExists);
        return true;
    }
}
