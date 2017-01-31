package com.mockneat.annotations.strategies.constants;

import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.types.exceptions.StrategyException;
import com.mockneat.types.exceptions.StrategyExceptionType;
import com.mockneat.utils.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


import static com.mockneat.utils.ArrayUtils.toWrapperArray;

/**
 * Created by andreinicolinciobanu on 03/11/2016.
 */
public class ConstStrategy implements Strategy<Annotation> {

    private static final String VALUE = "val";

    @Override
    public Object generate(Parameter p, Annotation a) {
        try {
            Class callingAnnotation  = a.getClass();
            Class type = p.getType();
            Method method = callingAnnotation.getMethod(VALUE);
            Object result = method.invoke(a);

            // Sorry for this mess
            if (type.equals(Boolean[].class)) {
                return ArrayUtils.toWrapperArray((boolean[]) result);
            } else if (type.equals(Byte[].class)) {
                return ArrayUtils.toWrapperArray((byte[]) result);
            } else if (type.equals(Character[].class)) {
                return ArrayUtils.toWrapperArray((char[]) result);
            } else if (type.equals(Double[].class)) {
                return ArrayUtils.toWrapperArray((double[]) result);
            } else if (type.equals(Float[].class)) {
                return ArrayUtils.toWrapperArray((float[]) result);
            } else if (type.equals(Integer[].class)) {
                return ArrayUtils.toWrapperArray((int[]) result);
            } else {
                return result;
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new StrategyException(StrategyExceptionType.FAILED_CONST_STRATEGY, e);
        }
    }
}
