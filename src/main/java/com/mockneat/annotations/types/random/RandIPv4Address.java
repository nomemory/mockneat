package com.mockneat.annotations.types.random;

import com.mockneat.annotations.generator.SourcesRepository;
import com.mockneat.types.enums.IPv4Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RandIPv4Address {
    String randSource() default SourcesRepository.DEFAULT_RAND_SOURCE_THREAD_LOCAL;
    IPv4Type value() default IPv4Type.NO_CONSTRAINT;
}
