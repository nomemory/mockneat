package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.Range;
import com.mockneat.types.enums.IPv4Type;

import java.util.Arrays;

import static com.mockneat.utils.NextUtils.checkAlphabet;
import static com.mockneat.utils.NextUtils.checkIpTypeNotNull;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class IPv4sOfTypes implements RandUnitString {

    private Rand rand;
    private IPv4Type[] types;

    public IPv4sOfTypes(Rand rand, IPv4Type... types) {
        this.rand = rand;
        this.types = types;
    }

    @Override
    public String val() {
        IPv4Type type = rand.objs().from(types).val();

        checkIpTypeNotNull(type);

        Range<Integer>[] oc = type.getOctets();
        StringBuilder buff = new StringBuilder();
        Arrays.stream(oc).forEach(range -> {
            if (range.isConstant()) buff.append(range.getLowerBound()).append(".");
            else {
                Integer result = rand.ints().inRange(range.getLowerBound(), range.getUpperBound() + 1).val();
                buff.append(result).append(".");
            }
        });
        buff.deleteCharAt(buff.length() - 1);
        return buff.toString();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
