package com.mockneat.annotations.validators;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by andreinicolinciobanu on 28/10/2016.
 */
public interface AnnotationValidator<T extends Annotation> {


    default void runValidationsInChain(Annotation annotation, Consumer<Annotation>... consumers)  {
        Arrays.stream(consumers).forEach(c -> c.accept(annotation));
    }

    Boolean validate(T annotation, Parameter parameter);
}
