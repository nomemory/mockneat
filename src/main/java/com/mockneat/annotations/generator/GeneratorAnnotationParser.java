package com.mockneat.annotations.generator;

import com.mockneat.annotations.types.Generator;
import com.mockneat.annotations.types.other.WithGenerator;
import com.mockneat.types.exceptions.GeneratorException;
import com.mockneat.types.exceptions.GeneratorExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;

import static java.util.stream.Collectors.*;


public class GeneratorAnnotationParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorAnnotationParser.class);

    // <Class, <GeneratorName, Generator>>
    private static final Map<Class, Map<String, GeneratorNode>> GENERATORS_STORE
            = new HashMap<>();

    private GeneratorAnnotationParser() {}

    protected static Boolean isClassMapped(Class<?> type) {
        return GENERATORS_STORE.keySet().contains(type);
    }

    protected static Boolean isClassGeneratorMapped(Class<?> type, String generatorName) {
        return isClassMapped(type) && (GENERATORS_STORE.get(type).get(generatorName) != null);
    }

    protected static GeneratorNode getGeneratorNode(Class<?> type, String generatorName) {

        Map<String, GeneratorNode> generatorsForType = GENERATORS_STORE.get(type);
        if (null == generatorsForType) {
            throw new GeneratorException(GeneratorExceptionType.CLASS_HAS_NO_VALID_GENERATORS, type.getSimpleName());
        }

        GeneratorNode generatorNode = generatorsForType.get(generatorName);
        if (null == generatorNode) {
            throw new GeneratorException(GeneratorExceptionType.INVALID_GENERATOR_REFERENCE, generatorName, type.getSimpleName());
        }

        return generatorNode;
    }

    protected static synchronized void addGlobalGeneratorNode(Class<?> type, String generatorName, GeneratorNode node) {
        Map<String, GeneratorNode> store = GENERATORS_STORE.get(type);
        if (null==store) {
            store = new HashMap<>();
            GENERATORS_STORE.put(type, store);
        }
        store.put(generatorName, node);
    }

    protected static synchronized void parse(Class<?> cls) {
        new ClassParserUnit(cls);
    }

    protected static class ClassParserUnit<T> {

        private static final Logger CPU_LOGGER = LoggerFactory.getLogger(ClassParserUnit.class);

        private Map<String, Constructor<T>> generators = new HashMap<>();
        private Map<String, GeneratorNode<T>> generatorNodes = new HashMap<>();

        private Class<T> cls;

        public ClassParserUnit(Class<T> cls) {
            this.cls = cls;

            CPU_LOGGER.info("Creating a new parsing unit for type {}", cls.getSimpleName());

            initGenerators();
            initGeneratorNodes();
            parse();

            generatorNodes.entrySet().forEach(entry ->
                CPU_LOGGER.info("@Generator(\"{}\") was parsed and mapped. Structure -> {}", entry.getKey(), entry.getValue())
            );
        }

        protected void initGenerators() {

            Arrays.stream(this.cls.getConstructors())
                    .filter(c -> c.isAnnotationPresent(Generator.class))
                    .forEach(c -> {
                        Generator generator = c.getAnnotation(Generator.class);
                        String generatorName = generator.value();

                        CPU_LOGGER.info("@Generator(\"{}\") identified for class {}. Parsing annotations", generatorName, cls.getSimpleName());
                        this.generators.put(generatorName, (Constructor<T>) c);
                    });
        }

        protected void initGeneratorNodes() {
            this.generators
                    .entrySet()
                    .stream()
                    .filter(e -> !isClassGeneratorMapped(cls, e.getKey()))
                    .forEach(e -> {
                        String generatorName = e.getKey();
                        GeneratorNode generatorNode = generatorNode(e.getValue());
                        generatorNodes.put(generatorName, generatorNode);
                    });
        }

        protected GeneratorNode<T> generatorNode(Constructor<T> constructor) {
            GeneratorNode<T> generatorNode = new GeneratorNode();
            Map<Parameter, Annotation> parameterAnnotationMap = ParserUtils.getParameterAnnotationMapping(constructor);

            generatorNode.setConstructor(constructor);
            generatorNode.setParameters(parameterNodes(parameterAnnotationMap));

            return generatorNode;
        }

        protected List<GeneratorParameterNode> parameterNodes(Map<Parameter, Annotation> parameterAnnotationMap) {
            return parameterAnnotationMap
                    .entrySet()
                    .stream()
                    .map(this::parameterNode)
                    .collect(toList());
        }

        protected GeneratorParameterNode parameterNode(Map.Entry<Parameter, Annotation> entry) {
            return parameterNode(entry.getKey(), entry.getValue());
        }

        protected GeneratorParameterNode parameterNode(Parameter param, Annotation annotation) {
            GeneratorParameterNode generatorParameterNode = new GeneratorParameterNode();
            generatorParameterNode.setParameter(param);
            generatorParameterNode.setAnnotation(annotation);

            if (annotation instanceof WithGenerator) {
                WithGenerator withGenerator = (WithGenerator) annotation;
                String generatorName = withGenerator.value();

                // Check to see if the class was not already mapped
                if (!isClassGeneratorMapped(param.getType(), generatorName)) {
                    ClassParserUnit classUnit = new ClassParserUnit(param.getType());
                    classUnit.parse();
                }
                GeneratorNode generatorNode = getGeneratorNode(param.getType(), generatorName);
                generatorParameterNode.setGeneratorNode(generatorNode);
            } else {
                generatorParameterNode.setStrategy(GeneratorAnnotationsMappings.getStrategy(annotation));
            }

            return generatorParameterNode;
        }

        protected void parse() {
            this.generatorNodes
                    .entrySet()
                    .forEach(e -> addGlobalGeneratorNode(cls, e.getKey(), e.getValue()));
        }
    }
}
