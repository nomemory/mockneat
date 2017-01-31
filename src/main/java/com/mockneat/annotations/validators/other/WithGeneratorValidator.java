package com.mockneat.annotations.validators.other;

import com.mockneat.annotations.types.Generator;
import com.mockneat.annotations.types.other.WithGenerator;
import com.mockneat.annotations.validators.AnnotationValidator;
import com.mockneat.types.exceptions.AnnotationValidationException;
import com.mockneat.types.exceptions.AnnotationValidationExceptionType;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by andreinicolinciobanu on 03/11/2016.
 */
public class WithGeneratorValidator implements AnnotationValidator<WithGenerator> {

    @Override
    public Boolean validate(WithGenerator annotation, Parameter parameter) {
        String referencedGenerator = annotation.value();
        Class parameterType = parameter.getType();
        Set<String> generators = Arrays.stream(parameterType.getConstructors())
                                        .filter(c -> c.isAnnotationPresent(Generator.class))
                                        .map(c -> ((Generator) c.getAnnotation(Generator.class)).value())
                                        .collect(Collectors.toSet());

        if (!generators.contains(referencedGenerator))
            throw new AnnotationValidationException("", AnnotationValidationExceptionType.INVALID_GENERATOR_REFERENCE, referencedGenerator, parameter.getType().getSimpleName());

        return true;
    }

}
