package com.mockneat.annotations.strategies.random;

import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.types.random.RandUUID;
import com.mockneat.sources.random.Rand;
import com.mockneat.annotations.generator.SourcesRepository;

import java.lang.reflect.Parameter;
import java.util.UUID;

public class RandUUIDStrategy implements Strategy<RandUUID> {

    @Override
    public Object generate(Parameter p, RandUUID a) {
        Rand rand = SourcesRepository.getInstance().getRandSource(a.randSource());
        if (p.getType().equals(UUID.class))
            return null; //TODO
//            return rand.nextUUIDObject();
        else if (p.getType().equals(String.class))
            return null; //TODO
//            return rand.nextUUID();
        else
            return null;
    }

}
