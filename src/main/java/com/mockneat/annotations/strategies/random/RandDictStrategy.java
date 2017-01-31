package com.mockneat.annotations.strategies.random;

import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.sources.random.Rand;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.StringFormatType;
import com.mockneat.annotations.types.random.RandDict;
import com.mockneat.annotations.generator.SourcesRepository;

import java.lang.reflect.Parameter;

/**
 * Created by andreinicolinciobanu on 18/11/2016.
 */
public class RandDictStrategy implements Strategy<RandDict> {

    @Override
    public Object generate(Parameter p, RandDict a) {
        Rand rand = SourcesRepository.getInstance().getRandSource(a.randSource());

        DictType type = a.value()[0];
        int maxLength = a.maxLength();
        StringFormatType formatType = a.format();

        //TODO implement maxLength
        return rand.dicts().from(type).format(formatType).val();
    }
}
