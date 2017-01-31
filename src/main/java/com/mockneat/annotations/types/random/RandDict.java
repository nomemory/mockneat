package com.mockneat.annotations.types.random;

import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.StringFormatType;
import com.mockneat.annotations.generator.SourcesRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RandDict {
    String randSource() default SourcesRepository.DEFAULT_RAND_SOURCE_THREAD_LOCAL;
    int maxLength() default 0;
    StringFormatType format() default StringFormatType.LOWER_CASE;
    DictType[] value();
    String sepator() default ".";
}