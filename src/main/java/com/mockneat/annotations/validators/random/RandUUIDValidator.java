package com.mockneat.annotations.validators.random;

import com.mockneat.annotations.types.random.RandUUID;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 20/12/2016.
 */
public class RandUUIDValidator implements AnnotationValidator<RandUUID> {

    @Override
    public Boolean validate(RandUUID annotation, Parameter parameter) {
        runValidationsInChain(annotation,
                RandValidUtils::randSourceExists);

        return true;
    }
}
