package com.mockneat.random.unit.user;

import com.mockneat.random.Rand;
import com.mockneat.random.interfaces.RandUnitString;
import com.mockneat.types.Pair;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.UserNameType;

import java.util.function.Supplier;

import static com.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static com.mockneat.random.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.random.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class Users implements RandUnitString {

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
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
        Supplier<String> supplier = () -> generateUserName(type);
        return () -> supplier;
    }

    public RandUnitString types(UserNameType... types) {
        notEmpty(types, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        UserNameType type = rand.from(types).val();
        return type(type);
    }

    protected String generateUserName(UserNameType type) {
        Pair<DictType, DictType> pair = rand.from(type.getDictCombos()).val();

        String part1 =
                rand.dicts().type(pair.getFirst()).format(LOWER_CASE).val();
        String part2 =
                rand.dicts().type(pair.getSecond()).format(LOWER_CASE).val();

        if (rand.bools().probability(UNDERSCORE).val()) {
            part1 += "_";
        }
        return part1 + part2;
    }
}
