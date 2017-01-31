package com.mockneat.annotations.strategies.random;


import com.mockneat.annotations.strategies.Strategy;
import com.mockneat.annotations.types.random.RandIPv4Address;
import com.mockneat.sources.random.Rand;
import com.mockneat.types.enums.IPv4Type;
import com.mockneat.annotations.generator.SourcesRepository;

import java.lang.reflect.Parameter;

public class RandIPv4AddressStrategy implements Strategy<RandIPv4Address> {
    @Override
    public Object generate(Parameter p, RandIPv4Address a) {
        Rand rand = SourcesRepository.getInstance().getRandSource(a.randSource());
        IPv4Type type = a.value();
        return rand.ipv4s().ofTypes(type).val();
    }
}
