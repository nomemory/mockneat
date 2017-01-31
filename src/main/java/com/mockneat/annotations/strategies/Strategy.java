package com.mockneat.annotations.strategies;

import java.lang.reflect.Parameter;

@FunctionalInterface
public interface Strategy<T> {
    Object generate(Parameter p, T a);
}
