package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.Pair;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.StringFormatType;
import com.mockneat.types.enums.UserNameType;

import static com.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static com.mockneat.utils.StringUtils.basicL33t;
import static com.mockneat.utils.StringUtils.escapeForUsername;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class UsersOfTypes implements RandUnitGeneric<String> {

    private static final Double L33T = 15.0;
    private static final Double UNDERSCORE = 15.0;

    private Rand rand;
    private UserNameType[] types;

    public UsersOfTypes(Rand rand, UserNameType... types) {
        this.rand = rand;
        this.types = types;
    }

    @Override
    public String val() {
        //TODO TYPE VALIDATE TYPES
        UserNameType type = rand.objs().from(types).val();
        Pair<DictType, DictType> pair = rand.objs().from(type.getDictCombos()).val();

        String part1 =
                escapeForUsername(rand.dicts().from(pair.getFirst()).format(LOWER_CASE).val());
        String part2 =
                escapeForUsername(rand.dicts().from(pair.getSecond()).format(LOWER_CASE).val());

        boolean l33t = rand.bools().probability(L33T).val();

        if (l33t) {
            part1 = basicL33t(part1);
            part2 = basicL33t(part2);
        }
        if (rand.bools().probability(UNDERSCORE).val()) {
            part1 += "_";
        }
        return part1 + part2;
    }

    public RandUnitFormatStringImpl format(StringFormatType formatType) {
        return new RandUnitFormatStringImpl(rand, formatType, val());
    }
}
