package com.mockneat.annotations.generator;

import com.mockneat.annotations.strategies.Strategies;
import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.strategies.other.NoAnnotationStrategy;
import com.mockneat.annotations.types.constants.*;
import com.mockneat.annotations.types.other.WithGenerator;
import com.mockneat.annotations.types.random.*;
import com.mockneat.annotations.validators.AnnotationValidator;
import com.mockneat.annotations.validators.AnnotationValidators;
import com.mockneat.utils.LoggingUtils;
import com.mockneat.annotations.types.other.NoAnnotation;
import com.mockneat.annotations.validators.other.AlwaysTrueValidator;
import com.mockneat.types.AnyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.*;

import static java.util.stream.Collectors.toSet;

public class GeneratorAnnotationsMappings {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorAnnotationsMappings.class);
    private static final Map<Class<? extends Annotation>, GeneratorAnnotationAssociation> DEFAULT_PARAMETER_ASSOCIATIONS = new HashMap<>();
    private static final Map<Class<? extends Annotation>, GeneratorAnnotationAssociation> CUSTOM_PARAMETER_ASSOCIATIONS = new HashMap<>();

    private GeneratorAnnotationsMappings() {}

    static {

        // RANDOM PARAMETER ANNOTATIONS
        associate(RandBoolean.class, withStrategy(Strategies.RAND_BOOLEAN_STRATEGY), withTypes(Boolean.class, boolean.class), withValidators(AnnotationValidators.RAND_BOOLEAN_VALIDATOR));
        associate(RandCreditCard.class, withStrategy(Strategies.RAND_CREDIT_CARD_STRATEGY), withTypes(String.class), withValidators(AnnotationValidators.RAND_CREDIT_CARD_VALIDATOR));
        associate(RandCVV.class, withStrategy(Strategies.RAND_CVV_STRATEGY), withTypes(String.class), withValidators(AnnotationValidators.RAND_CVV_VALIDATOR));
        associate(RandDict.class, withStrategy(Strategies.RAND_DICT_STRATEGY), withTypes(String.class), withValidators(AnnotationValidators.RAND_DICT_VALIDATOR));
        associate(RandEmail.class, withStrategy(Strategies.RAND_EMAIL_STRATEGY), withTypes(String.class), withValidators(AnnotationValidators.RAND_EMAIL_VALIDATOR));
        associate(RandInteger.class, withStrategy(Strategies.RAND_INTEGER_STRATEGY), withTypes(Integer.class, int.class), withValidators(AnnotationValidators.RAND_INTEGER_VALIDATOR));
        associate(RandIPv4Address.class, withStrategy(Strategies.RAND_IPV4_ADDRESS_STRATEGY), withTypes(String.class), withValidators(AnnotationValidators.RAND_IPV4_ADDRESS_VALIDATOR));
        associate(RandMacAddress.class, withStrategy(Strategies.RAND_MAC_ADDRESS_STRATEGY), withTypes(String.class), withValidators(AnnotationValidators.RAND_MAC_ADDRESS_VALIDATOR));
        associate(RandPassword.class, withStrategy(Strategies.RAND_PASSWORD_STRATEGY), withTypes(String.class), withValidators(AnnotationValidators.RAND_PASSWORD_VALIDATOR));
        associate(RandUserName.class, withStrategy(Strategies.RAND_USERNAME_STRATEGY), withTypes(String.class), withValidators(AnnotationValidators.RAND_USERNAME_VALIDATOR));
        associate(RandUUID.class, withStrategy(Strategies.RAND_UUID_STRATEGY), withTypes(String.class, UUID.class), withValidators(AnnotationValidators.RAND_UUID_VALIDATOR));

        // CONSTANT PARAMETER ANNOTATION
        associate(ConstBoolean.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(Boolean.class, boolean.class), noValidators());
        associate(ConstBooleanArray.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(Boolean[].class, boolean[].class), noValidators());
        associate(ConstByte.class,  withStrategy(Strategies.CONST_STRATEGY), withTypes(Byte.class, byte.class), noValidators());
        associate(ConstByteArray.class,  withStrategy(Strategies.CONST_STRATEGY), withTypes(Byte[].class, byte[].class), noValidators());
        associate(ConstCharacter.class,  withStrategy(Strategies.CONST_STRATEGY), withTypes(Character.class, char.class), noValidators());
        associate(ConstCharacterArray.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(Character[].class, char[].class), noValidators());
        associate(ConstDouble.class,  withStrategy(Strategies.CONST_STRATEGY), withTypes(Double.class, double.class), noValidators());
        associate(ConstDoubleArray.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(Double[].class, double[].class), noValidators());
        associate(ConstFloat.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(Float.class, float.class), noValidators());
        associate(ConstFloatArray.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(Float[].class, float[].class), noValidators());
        associate(ConstInteger.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(Integer.class, int.class), noValidators());
        associate(ConstIntegerArray.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(Integer[].class, int[].class), noValidators());
        associate(ConstLong.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(Long.class, long.class), noValidators());
        associate(ConstLongArray.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(Long[].class, long[].class), noValidators());
        associate(ConstString.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(String.class), noValidators());
        associate(ConstStringArray.class, withStrategy(Strategies.CONST_STRATEGY), withTypes(String[].class), noValidators());

        // GENERATOR ANNOTATION FOR PARAMS
        associate(WithGenerator.class, withNoStrategy(), withAllTypes(), withValidators(AnnotationValidators.WITH_GENERATOR_VALIDATOR));

        // OTHER
        associate(NoAnnotation.class, withNoStrategy(), withAllTypes(), noValidators());
    }

    // Private Methods
    private static Set<Class<?>> withTypes(Class<?>... types) {
        return Arrays.stream(types).collect(toSet());
    }

    private static final Set<Class<?>> withAllTypes() { return new HashSet<>(Arrays.asList(AnyType.class)); }

    private static void associate(Class<? extends Annotation> anno, Strategy strategy, Set<Class<?>> supportedTypes, Set<AnnotationValidator> validators) {
        DEFAULT_PARAMETER_ASSOCIATIONS.put(anno, new GeneratorAnnotationAssociation(strategy, supportedTypes, validators));
    }

    private static void logAssociate(Class<Annotation> anno, Strategy strategy, Set<Class<?>> supportedTypes, Set<AnnotationValidator> validators) {
        String annotationText = LoggingUtils.annotationText(anno);
        String strategyText = LoggingUtils.strategyText(strategy);
        String supportedTypesText = LoggingUtils.supportedTypesText(supportedTypes);
        String validatorsText = LoggingUtils.annotationValidatorsText(validators);
        logger.info("Default Parameter Association Added: {} -> {} -> {} -> {}",
                annotationText, strategyText, supportedTypesText, validatorsText);
    }

    private static Strategy withNoStrategy() {
        return new NoAnnotationStrategy();
    }

    private static Strategy withStrategy(Strategies strategies) {
        return strategies.get();
    }

    private static Set<AnnotationValidator> noValidators() {
        HashSet<AnnotationValidator> result = new HashSet<>();
        result.add(new AlwaysTrueValidator());
        return result;
    }

    private static Set<AnnotationValidator> withValidators(AnnotationValidators... annoValidType) {
        return Arrays.stream(annoValidType).map(AnnotationValidators::get).collect(toSet());
    }

    // PUBLIC METHODS
    public static Boolean isParameterAnnotation(Annotation annotation) {
        return DEFAULT_PARAMETER_ASSOCIATIONS.keySet().contains(annotation.annotationType()) ||
                CUSTOM_PARAMETER_ASSOCIATIONS.keySet().contains(annotation.annotationType());
    }

    public static Boolean areCompatible(Annotation annotation, Class<?> cls) {

        if (!isParameterAnnotation(annotation))
            return false;

        GeneratorAnnotationAssociation defaultGeneratorAnnotationAssociation =
                DEFAULT_PARAMETER_ASSOCIATIONS.get(annotation.annotationType());
        GeneratorAnnotationAssociation customGeneratorAnnotationAssociation =
                CUSTOM_PARAMETER_ASSOCIATIONS.get(annotation.annotationType());

        return defaultGeneratorAnnotationAssociation.getSupportedTypes().contains(cls) ||
                defaultGeneratorAnnotationAssociation.getSupportedTypes().contains(AnyType.class) ||
                customGeneratorAnnotationAssociation.getSupportedTypes().contains(cls) ||
                customGeneratorAnnotationAssociation.getSupportedTypes().contains(AnyType.class);
    }

    public static Boolean areCompatible(Annotation a, Parameter p) {
        return areCompatible(a, p.getType());
    }

    protected static GeneratorAnnotationAssociation getAssociation(Annotation annotation) {

        GeneratorAnnotationAssociation defaultAnno =
                DEFAULT_PARAMETER_ASSOCIATIONS.get(annotation.annotationType());
        GeneratorAnnotationAssociation customAnno =
                CUSTOM_PARAMETER_ASSOCIATIONS.get(annotation.annotationType());

        if (null != defaultAnno)
            return defaultAnno;

        if (null != customAnno)
            return customAnno;

        return null;
    }

     public static Set<AnnotationValidator> getValidators(Annotation annotation) {
        GeneratorAnnotationAssociation association = getAssociation(annotation);

        if (association!=null)
            return association.getValidators();

        return new HashSet<>();
    }

    public static Strategy<Annotation> getStrategy(Annotation annotation) {
        GeneratorAnnotationAssociation association = getAssociation(annotation);

        if (association!=null)
            return association.getStrategy();

        return null;
    }
}
