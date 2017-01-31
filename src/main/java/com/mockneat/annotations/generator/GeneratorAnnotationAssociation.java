package com.mockneat.annotations.generator;

import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.util.Set;

public class GeneratorAnnotationAssociation {

    private Strategy strategy;

    private Set<Class<?>> supportedTypes;

    private Set<AnnotationValidator> validators;

    protected GeneratorAnnotationAssociation(Strategy strategy, Set<Class<?>> supportedTypes, Set<AnnotationValidator> validators) {
        this.strategy = strategy;
        this.supportedTypes = supportedTypes;
        this.validators = validators;
    }

    protected Strategy getStrategy() {
        return strategy;
    }

    protected Set<Class<?>> getSupportedTypes() {
        return supportedTypes;
    }

    protected Set<AnnotationValidator> getValidators() {
        return validators;
    }
}
