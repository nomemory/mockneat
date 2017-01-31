package com.mockneat.annotations.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;
import static com.mockneat.annotations.generator.GeneratorAnnotationsMappings.areCompatible;
import static com.mockneat.annotations.types.other.NoAnnotationBuilder.noAnnotation;

/**
 * Created by andreinicolinciobanu on 08/11/2016.
 */
public class ParserUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(ParserUtils.class);

    private ParserUtils() {}

    public static Annotation getAnnotation(Parameter parameter) {
        Annotation[] annotations = parameter.getAnnotations();
        if (0 == annotations.length)
            return noAnnotation();

        List<Annotation> winners =
                Arrays.stream(annotations)
                        .filter(anno -> GeneratorAnnotationsMappings.areCompatible(anno, parameter))
                        .collect(Collectors.toList());

        Annotation winner = winners.get(0);
        GeneratorAnnotationsMappings.getValidators(winner).forEach(validator -> validator.validate(winner, parameter));
        return winner;
    }

    public static Map<Parameter, Annotation> getParameterAnnotationMapping(Constructor constructor) {
        Parameter[] parameters = constructor.getParameters();
        final Map<Parameter, Annotation> map = new LinkedHashMap<>();
        Arrays.stream(parameters)
                .forEach(param -> map.put(param, getAnnotation(param)));
        return map;
    }
}
