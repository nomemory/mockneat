package com.mockneat.types.exceptions;

public class StrategyException extends RuntimeException {

    private String message;

    public StrategyException(StrategyExceptionType type, Object... args)
    {
        this.message = String.format(type.getMessage(), args);
    }

    public StrategyException(String prefix, AnnotationValidationException type, Object[] args) {
        this.message = String.format(type.getMessage(), args);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
