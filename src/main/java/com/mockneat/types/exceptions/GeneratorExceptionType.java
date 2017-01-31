package com.mockneat.types.exceptions;

/**
 * Created by andreinicolinciobanu on 04/11/2016.
 */
public enum GeneratorExceptionType {
    CLASS_HAS_NO_VALID_GENERATORS("Class %s has no valid @Generator(s) associated with it."),
    INVALID_RANDOM_SOURCE_REFERENCE("Cannot find random source '%s' in the repository. Please add it or use the defaults."),
    INVALID_SEQUNCE_REFERENCE("Cannot find sequence '%s' in the repository. Please add it before using it."),
    INVALID_GENERATOR_REFERENCE("Cannot find generator '%s' in the repository. Are you sure class '%s' has a @Generator named like this?"),
    RESERVED_NAME("%s is reserved. Please chose another name for the source");

    private String message;

    GeneratorExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
