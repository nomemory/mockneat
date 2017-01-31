package com.mockneat.annotations.validators.random;

import com.mockneat.annotations.types.random.RandBoolean;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.reflect.Parameter;

import static com.mockneat.annotations.validators.random.RandValidUtils.randSourceExists;

/**
 * Created by andreinicolinciobanu on 02/11/2016.
 */
public class RandBooleanValidator implements AnnotationValidator<RandBoolean> {

    @Override
    public Boolean validate(RandBoolean annotation, Parameter parameter) {
        randSourceExists(annotation);
        return true;
    }
}
