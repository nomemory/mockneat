package com.mockneat.types.exceptions;

public enum AnnotationValidationExceptionType {

    // RANDOMS
    FAILED_TO_OBTAIN_RAND_SOURCE("Invalid 'randSource' property  associated with annotation: @%s. This validator my not apply to this type of annotation."),
    FAILED_TO_OBTAIN_BOUND("Failed to obtain bound (upper|lower) for annotation @%s. This validator my not apply to this type of annotation."),
    INVALID_GENERATOR_REFERENCE("Invalid generator reference '%s'. There is no generator registered with this name for class: %s "),
    INVALID_INTERVAL("The interval determined by the lowerBound:%d and upperBound:%d bounds is invalid. The lowerBound needs to be lesser than the upperBound"),
    INVALID_RAND_SOURCE("There is no RandSource identified by '%s' in the SourcesRepository. You should first add one if you are not using the defaults"),
    NEGATIVE_BOUND("%s for @%s cannot be a negative number (%d)");

    private String message;

    AnnotationValidationExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
