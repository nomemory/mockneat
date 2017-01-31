package com.mockneat.annotations.strategies.random;

import com.mockneat.sources.random.Rand;
import com.mockneat.annotations.generator.SourcesRepository;
import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.types.random.RandCreditCard;
import com.mockneat.types.enums.CreditCardType;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 20/12/2016.
 */
public class RandCreditCardStrategy implements Strategy<RandCreditCard> {
    @Override
    public Object generate(Parameter p, RandCreditCard a) {
        String randSource = a.randSource();
        CreditCardType type = a.type();
        Rand rand = SourcesRepository.getInstance().getRandSource(randSource);
        return rand.ccs().ofType(type).val();
    }
}

