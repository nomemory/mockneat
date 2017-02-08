package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.NameType;

import java.util.function.Supplier;

import static com.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class Names implements RandUnitString {

    private Rand rand;

    public Names(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return type(NameType.FIRST_NAME)::val;
    }

    public RandUnitString types(NameType... types) {
        notEmpty(types, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        NameType nameType = rand.objs().from(types).val();
        return type(nameType);
    }

    public RandUnitString type(NameType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
        DictType dictType = rand.objs().from(type.getDictionaries()).val();
        return () -> rand.dicts().type(dictType)::val;
    }
}
