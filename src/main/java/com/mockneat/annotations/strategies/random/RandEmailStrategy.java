package com.mockneat.annotations.strategies.random;

import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.types.random.RandEmail;
import com.mockneat.sources.random.Rand;
import com.mockneat.annotations.generator.SourcesRepository;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 29/12/2016.
 */
public class RandEmailStrategy implements Strategy<RandEmail> {
    @Override
    public Object generate(Parameter p, RandEmail a) {
        Rand rand = SourcesRepository.getInstance().getRandSource(a.randSource());
      //  return rand.nextEmail();
        return null;
    }
}
