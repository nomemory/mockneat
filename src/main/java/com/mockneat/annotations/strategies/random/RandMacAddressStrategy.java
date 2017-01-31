package com.mockneat.annotations.strategies.random;


import com.mockneat.sources.random.Rand;
import com.mockneat.annotations.generator.SourcesRepository;
import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.types.random.RandMacAddress;
import com.mockneat.types.enums.MACAddressFormatType;

import java.lang.reflect.Parameter;

public class RandMacAddressStrategy implements Strategy<RandMacAddress> {
    @Override
    public Object generate(Parameter p, RandMacAddress a) {
        Rand rand = SourcesRepository.getInstance().getRandSource(a.randSource());
        MACAddressFormatType type = a.value();
        return rand.macs().format(type).val();
    }
}
