package com.mockneat.annotations.strategies.random;

import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.types.random.RandBoolean;
import com.mockneat.sources.random.Rand;
import com.mockneat.annotations.generator.SourcesRepository;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 19/12/2016.
 */
public class RandBooleanStrategy implements Strategy<RandBoolean> {
    @Override
    public Object generate(Parameter p, RandBoolean a) {
        Rand rand = SourcesRepository.getInstance().getRandSource(a.randSource());
        return rand.bools().val();
    }
}
