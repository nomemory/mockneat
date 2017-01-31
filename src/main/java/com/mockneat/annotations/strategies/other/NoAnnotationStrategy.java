package com.mockneat.annotations.strategies.other;

import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.types.other.NoAnnotation;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 28/10/2016.
 */
public class NoAnnotationStrategy implements Strategy<NoAnnotation> {
    @Override
    public Object generate(Parameter p, NoAnnotation a) {
        return null;
    }
}
