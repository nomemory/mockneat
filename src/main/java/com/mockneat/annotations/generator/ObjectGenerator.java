package com.mockneat.annotations.generator;

import java.lang.reflect.InvocationTargetException;

import static com.mockneat.annotations.generator.GeneratorAnnotationParser.isClassMapped;

public class ObjectGenerator<T> {

    private Class cls;
    private String generatorName;
    private GeneratorNode<T> generatorNode;

    public ObjectGenerator(Class cls, String generatorName) {
        this.cls = cls;
        this.generatorName = generatorName;

        if (!isClassMapped(cls))
            GeneratorAnnotationParser.parse(cls);

        this.generatorNode = GeneratorAnnotationParser.getGeneratorNode(cls, generatorName);
    }

    public T generate() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return this.generatorNode.generate();
    }

    public String getGeneratorName() {
        return generatorName;
    }

    public GeneratorNode<T> getGeneratorNode() {
        return generatorNode;
    }
}
