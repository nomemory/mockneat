package com.mockneat.annotations.generator;

import com.mockneat.annotations.strategies.Strategy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public class GeneratorParameterNode<T> {

    private Parameter parameter;

    private Annotation annotation;

    private Strategy strategy;

    private GeneratorNode generatorNode;

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public Strategy<T> getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy<T> strategy) {
        this.strategy = strategy;
    }

    public GeneratorNode getGeneratorNode() {
        return generatorNode;
    }

    public void setGeneratorNode(GeneratorNode generatorNode) {
        this.generatorNode = generatorNode;
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append(" Parameter{type=")
                .append(parameter.getType().getSimpleName())
                .append(", annotation=")
                .append(annotation.annotationType().getSimpleName())
                .append("},");
        return buff.toString();
    }
}
