package com.mockneat.annotations.strategies.random;

import com.mockneat.annotations.types.random.RandUserName;
import com.mockneat.sources.random.Rand;
import com.mockneat.annotations.generator.SourcesRepository;
import com.mockneat.annotations.strategies.Strategy;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 29/12/2016.
 */
public class RandUserNameStrategy implements Strategy<RandUserName> {
    @Override
    public Object generate(Parameter p, RandUserName a) {
        Rand rand = SourcesRepository.getInstance().getRandSource(a.randSource());
        //return rand.nextUserName();
        return null;
    }
}
