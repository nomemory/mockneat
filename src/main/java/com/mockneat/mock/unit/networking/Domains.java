package com.mockneat.mock.unit.networking;

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.mock.utils.ValidationUtils;
import com.mockneat.types.enums.DomainSuffixType;

import java.util.function.Supplier;

import static com.mockneat.types.enums.DomainSuffixType.POPULAR;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 16/02/2017.
 */
public class Domains implements MockUnitString {

    private MockNeat rand;

    public Domains(MockNeat rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return type(POPULAR)::val;
    }

    public MockUnitString type(DomainSuffixType type) {
        notNull(type, ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "type");
        return () -> rand.dicts().type(type.getDictType())::val;
    }

    public MockUnitString types(DomainSuffixType... types) {
        notEmpty(types, ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        DomainSuffixType type = rand.from(types).val();
        return type(type);
    }
}
