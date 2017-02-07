package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.Pair;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.UserNameType;

import java.util.function.Supplier;

import static com.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static com.mockneat.utils.StringUtils.basicL33t;
import static com.mockneat.utils.StringUtils.escapeForUsername;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class Users implements RandUnitString {

    private static final Double L33T = 0.15;
    private static final Double UNDERSCORE = 0.15;

    private Rand rand;

    public Users(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return types(UserNameType.values()).supplier();
    }

    public RandUnitString type(UserNameType type) {
        //TODO check
        Supplier<String> supplier = () -> {
            Pair<DictType, DictType> pair = rand.objs().from(type.getDictCombos()).val();

            String part1 =
                    escapeForUsername(rand.dicts().type(pair.getFirst()).format(LOWER_CASE).val());
            String part2 =
                    escapeForUsername(rand.dicts().type(pair.getSecond()).format(LOWER_CASE).val());

            boolean l33t = rand.bools().probability(L33T).val();

            if (l33t) {
                part1 = basicL33t(part1);
                part2 = basicL33t(part2);
            }
            if (rand.bools().probability(UNDERSCORE).val()) {
                part1 += "_";
            }
            return part1 + part2;
        };
        return () -> supplier;
    }

    public RandUnitString types(UserNameType... types) {
        // TODO check
        UserNameType type = rand.objs().from(types).val();
        return type(type);
    }
}
