package com.mockneat.annotations.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Will mark a constructor as an entry point for random generation of objs.
 *
 * @author Andrei N. CIOBANU
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.PARAMETER})
public @interface Generator {
    String DEFAULT_NAME = "DEFAULT_OBJ_GENERATOR_CONSTRUCTOR";

    // Annotation properties
    String value() default DEFAULT_NAME;
}
