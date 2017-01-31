package com.mockneat.types.exceptions;

/**
 * Created by andreinicolinciobanu on 04/11/2016.
 */
public class GeneratorException extends RuntimeException {
    private String message;

    public GeneratorException(GeneratorExceptionType type, Object... args)
    {
        this.message = String.format(type.getMessage(), args);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
