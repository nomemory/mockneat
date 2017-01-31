package com.mockneat.annotations.generator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class GeneratorNode<T> {

    private Constructor<T> constructor;

    private List<GeneratorParameterNode> parameters;

    public Constructor<T> getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor<T> constructor) {
        this.constructor = constructor;
    }

    public List<GeneratorParameterNode> getParameters() {
        return parameters;
    }

    public void setParameters(List<GeneratorParameterNode> parameters) {
        this.parameters = parameters;
    }

    public T generate() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Object> values = new LinkedList<>();
        for(GeneratorParameterNode generatorParameterNode : parameters) {
            if (generatorParameterNode.getGeneratorNode()==null) {
                values.add(generatorParameterNode.getStrategy().generate(generatorParameterNode.getParameter(), generatorParameterNode.getAnnotation()));
            }
            else {
                values.add(generatorParameterNode.getGeneratorNode().generate());
            }
        }
        return constructor.newInstance(values.toArray());
    }

    @Override
    public String toString() {
        final StringBuilder buff = new StringBuilder("Constructor [");
        parameters.forEach(buff::append);
        buff.delete(buff.length()-1,buff.length());
        buff.append(" ]");
        return buff.toString();
    }
}
