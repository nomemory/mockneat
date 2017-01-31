package com.mockneat.annotations.validators.random;

import com.mockneat.types.exceptions.AnnotationValidationException;
import com.mockneat.types.exceptions.AnnotationValidationExceptionType;
import com.mockneat.annotations.types.random.RandDict;
import com.mockneat.annotations.generator.SourcesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by andreinicolinciobanu on 03/11/2016.
 */
public class RandValidUtils {

    private static final Logger logger = LoggerFactory.getLogger(RandValidUtils.class);

    private static final String LOWER_BOUND = "lowerBound";
    private static final String UPPER_BOUND = "upperBound";
    private static final String RAND_SOURCE = "randSource";
    private static final String MAX_LENGTH = "maxLength";

    private RandValidUtils() {}

    protected static int getBound(Annotation annotation, String bound) {
        try {
            Class cls = annotation.getClass();
            Method method = cls.getMethod(bound);
            return (int) method.invoke(annotation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new AnnotationValidationException("",
                    AnnotationValidationExceptionType.FAILED_TO_OBTAIN_BOUND,
                    annotation.getClass().getSimpleName());
        }
    }

    protected static String getRandomSourceName(Annotation annotation) {
        String result = null;
        try {
            Class cls = annotation.getClass();
            Method method = cls.getMethod(RAND_SOURCE);
            result = (String) method.invoke(annotation);
            return result;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new AnnotationValidationException("",
                    AnnotationValidationExceptionType.FAILED_TO_OBTAIN_RAND_SOURCE,
                    result,
                    annotation.getClass().getSimpleName());
        }
    }

    protected static int getLowerBound(Annotation annotation) {
        return getBound(annotation, LOWER_BOUND);
    }

    protected static int getUpperBound(Annotation annotation) {
        return getBound(annotation, UPPER_BOUND);
    }

    protected static void validateBound(Annotation annotation, String bound) {
            int val = getBound(annotation, bound);
            if (val<0)
                throw new AnnotationValidationException("",
                        AnnotationValidationExceptionType.NEGATIVE_BOUND,
                        bound,
                        annotation.getClass().getSimpleName(),
                        val);
    }

    public static void notNegativeUpperBound(Annotation annotation) {
        validateBound(annotation, UPPER_BOUND);
    }

    public static void notNegativeLowerBound(Annotation annotation) {
        validateBound(annotation, LOWER_BOUND);
    }

    public static void lowerBoundLessThanUpperBound(Annotation annotation) {
        int lower = getLowerBound(annotation);
        int upper = getUpperBound(annotation);
        if (lower>upper)
            throw new AnnotationValidationException("",
                    AnnotationValidationExceptionType.INVALID_INTERVAL,
                    lower,
                    upper);
    }

    public static void randSourceExists(Annotation annotation) {
        String randSource = getRandomSourceName(annotation);
        if (null == SourcesRepository.getInstance().getRandSource(randSource))
            throw new AnnotationValidationException("", AnnotationValidationExceptionType.INVALID_RAND_SOURCE, randSource);
    }

    public static void dictExists(RandDict randDict)  {
        //TODO
    }

}
