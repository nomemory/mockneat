package com.mockneat.types.exceptions;

public class AnnotationValidationException extends RuntimeException {

    private String message;

    public AnnotationValidationException(String prefix, AnnotationValidationExceptionType type, Object... args)
    {
        this.message = prefix + String.format(type.getMessage(), args);
    }

    public AnnotationValidationException(String prefix, AnnotationValidationException type, Object[] args) {
        this.message = prefix  + String.format(type.getMessage(), args);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
