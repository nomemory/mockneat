package com.mockneat.annotations.types.other;

import java.lang.annotation.Annotation;

public class NoAnnotationBuilder {

    private static NoAnnotation instance =
            new NoAnnotation(){
                @Override
                public Class<? extends Annotation> annotationType() {
                    return NoAnnotation.class;
                }
            };

    private NoAnnotationBuilder() {}

    public static NoAnnotation noAnnotation() {
        return instance;
    }
}
