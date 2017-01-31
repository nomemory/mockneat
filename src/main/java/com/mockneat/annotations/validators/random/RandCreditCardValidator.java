package com.mockneat.annotations.validators.random;

import com.mockneat.annotations.types.random.RandCreditCard;
import com.mockneat.annotations.validators.AnnotationValidator;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 20/12/2016.
 */
public class RandCreditCardValidator implements AnnotationValidator<RandCreditCard> {

    @Override
    public Boolean validate(RandCreditCard annotation, Parameter parameter) {
        runValidationsInChain(annotation,
                RandValidUtils::randSourceExists);
        return true;
    }

}
