package com.mockneat.mock.unit.user;

import com.mockneat.mock.utils.ValidationUtils;
import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.types.Pair;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.UserNameType;

import java.util.function.Supplier;

import static com.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class Users implements MockUnitString {

    private static final Double UNDERSCORE = 0.15;

    private MockNeat mock;

    public Users(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<String> supplier() {
        return types(UserNameType.values()).supplier();
    }

    public MockUnitString type(UserNameType type) {
        notNull(type, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "type");
        Supplier<String> supplier = () -> generateUserName(type);
        return () -> supplier;
    }

    public MockUnitString types(UserNameType... types) {
        notEmpty(types, ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        UserNameType type = mock.from(types).val();
        return type(type);
    }

    protected String generateUserName(UserNameType type) {
        Pair<DictType, DictType> pair = mock.from(type.getDictCombos()).val();

        String part1 =
                mock.dicts().type(pair.getFirst()).format(LOWER_CASE).val();
        String part2 =
                mock.dicts().type(pair.getSecond()).format(LOWER_CASE).val();

        if (mock.bools().probability(UNDERSCORE).val()) {
            part1 += "_";
        }
        return part1 + part2;
    }
}
