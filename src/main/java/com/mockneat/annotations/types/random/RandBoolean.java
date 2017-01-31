package com.mockneat.annotations.types.random;

import com.mockneat.annotations.generator.SourcesRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RandBoolean {
    String randSource() default SourcesRepository.DEFAULT_RAND_SOURCE_THREAD_LOCAL;
}
