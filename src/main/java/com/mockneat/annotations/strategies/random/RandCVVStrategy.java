package com.mockneat.annotations.strategies.random;

import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.types.random.RandCVV;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 20/12/2016.
 */
public class RandCVVStrategy implements Strategy<RandCVV> {

    @Override
    public Object generate(Parameter p, RandCVV a) {
//        Rand rand = SourcesRepository.getInstance().getRandSource(a.randSource());
//        return rand.cvv(a.val());
        return null;
    }

}
