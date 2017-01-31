package com.mockneat.utils;

import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.annotation.Annotation;
import java.util.Iterator;

/**
 * Created by andreinicolinciobanu on 28/10/2016.
 */
public class LoggingUtils {

    public static String annotationText(Class<Annotation> annotation) {
        return new StringBuilder("@").append(annotation.getClass().getSimpleName()).toString();
    }

    public static String strategyText(Strategy strategy) {
        return new StringBuilder("AnnotationStrategy:").append(strategy.getClass().getSimpleName()).toString();
    }

    protected static String iterableTypeText (String prefix, Iterable<?> something) {
        StringBuilder result = new StringBuilder(prefix).append(":[");
        Iterator it = something.iterator();
        int i = 0;
        while(it.hasNext()) {
            result.append(it.next().getClass().getSimpleName()).append(", ");
            i++;
        }

        if (i!=0)
            result.delete(result.length() - 2, result.length() - 1);

        return result.append("]").toString();
    }

    public static String supportedTypesText(Iterable<Class<?>> supportedTypes) {
        return iterableTypeText("SupportedType(s)", supportedTypes);
    }

    public static String annotationValidatorsText(Iterable<AnnotationValidator> annotationValidators) {
        return iterableTypeText("AnnotationValidator(s)", annotationValidators);
    }
}
