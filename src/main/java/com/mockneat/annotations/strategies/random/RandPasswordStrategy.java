package com.mockneat.annotations.strategies.random;

import com.mockneat.annotations.generator.SourcesRepository;
import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.types.random.RandPassword;
import com.mockneat.sources.random.Rand;
import com.mockneat.types.enums.PassStrengthType;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 23/12/2016.
 */
public class RandPasswordStrategy implements Strategy<RandPassword> {
    @Override
    public Object generate(Parameter p, RandPassword a) {
        Rand rand = SourcesRepository.getInstance().getRandSource(a.randSource());
        PassStrengthType type = a.value();
        return ""; //TODO
    }
}
