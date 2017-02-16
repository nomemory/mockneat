package com.mockneat.random.unit.networking;

import com.mockneat.random.Rand;
import com.mockneat.random.interfaces.RandUnitString;
import com.mockneat.types.enums.DomainSuffixType;

import java.util.function.Supplier;

import static com.mockneat.random.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static com.mockneat.types.enums.DomainSuffixType.POPULAR;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 16/02/2017.
 */
public class Domains implements RandUnitString {

    private Rand rand;

    public Domains(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return type(POPULAR)::val;
    }

    public RandUnitString type(DomainSuffixType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "type");
        return () -> rand.dicts().type(type.getDictType())::val;
    }

    public RandUnitString types(DomainSuffixType... types) {
        notEmpty(types, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        DomainSuffixType type = rand.from(types).val();
        return type(type);
    }
}
