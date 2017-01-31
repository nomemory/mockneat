package com.mockneat.annotations.validators;

import com.mockneat.annotations.validators.random.*;
import com.mockneat.annotations.validators.other.WithGeneratorValidator;

/**
 * Created by andreinicolinciobanu on 03/11/2016.
 */
public enum AnnotationValidators {

    WITH_GENERATOR_VALIDATOR(new WithGeneratorValidator()),

    RAND_BOOLEAN_VALIDATOR(new RandBooleanValidator()),
    RAND_CREDIT_CARD_VALIDATOR(new RandCreditCardValidator()),
    RAND_CVV_VALIDATOR(new RandCVVValidator()),
    RAND_DICT_VALIDATOR(new RandDictValidator()),
    RAND_EMAIL_VALIDATOR(new RandEmailValidator()),
    RAND_INTEGER_VALIDATOR(new RandIntegerValidator()),
    RAND_IPV4_ADDRESS_VALIDATOR(new RandIPv4AddressValidator()),
    RAND_MAC_ADDRESS_VALIDATOR(new RandMACAddressValidator()),
    RAND_PASSWORD_VALIDATOR(new RandPasswordValidator()),
    RAND_USERNAME_VALIDATOR(new RandUserNameValidator()),
    RAND_UUID_VALIDATOR(new RandUUIDValidator());

    AnnotationValidators(AnnotationValidator validator) {
        this.validator = validator;
    }

    private AnnotationValidator validator;

    public AnnotationValidator get() {
        return validator;
    }
}
