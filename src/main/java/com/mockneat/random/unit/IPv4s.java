package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.Range;
import com.mockneat.types.enums.IPv4Type;

import java.util.Arrays;
import java.util.function.Supplier;

import static com.mockneat.types.enums.IPv4Type.NO_CONSTRAINT;
import static com.mockneat.utils.CheckUtils.checkIpTypeNotNull;

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
        Supplier<String> supplier = () -> {
            IPv4Type type = rand.objs().from(types).val();

            checkIpTypeNotNull(type);

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
        return () -> supplier;
    }

    public RandUnitString type(IPv4Type type) { return types(type); }

}
