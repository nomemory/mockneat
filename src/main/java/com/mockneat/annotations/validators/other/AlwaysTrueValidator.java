package com.mockneat.annotations.validators.other;

import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public class AlwaysTrueValidator implements AnnotationValidator {

    public AlwaysTrueValidator() {
        super();
    }

    @Override
    public Boolean validate(Annotation annotation, Parameter parameter) {
        return true;
    }

}
