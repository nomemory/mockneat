package com.mockneat.types.exceptions;

public enum StrategyExceptionType {
    FAILED_CONST_STRATEGY("Failed to call ConstStrategy. Cannot obtain the constant val.");

    StrategyExceptionType(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }
}
