package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.Range;
import com.mockneat.types.enums.IPv4Type;

import java.util.Arrays;
import java.util.function.Supplier;

import static com.mockneat.types.enums.IPv4Type.NO_CONSTRAINT;
import static com.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class IPv4s implements RandUnitString {

    private Rand rand;

    public IPv4s(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return type(NO_CONSTRAINT).supplier();
    }

    public RandUnitString types(IPv4Type... types) {
        notEmpty(types, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        IPv4Type type = rand.objs().from(types).val();
        return type(type);
    }

    public RandUnitString type(IPv4Type type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
        Supplier<String> supp = () -> {
            Range<Integer>[] oc = type.getOctets();
            StringBuilder buff = new StringBuilder();
            Arrays.stream(oc).forEach(range -> {
                if (range.isConstant()) buff.append(range.getLowerBound()).append(".");
                else {
                    Integer result = rand.ints().range(range.getLowerBound(), range.getUpperBound() + 1).val();
                    buff.append(result).append(".");
                }
            });
            buff.deleteCharAt(buff.length() - 1);
            return buff.toString();
        };
        return () -> supp;
    }

}
