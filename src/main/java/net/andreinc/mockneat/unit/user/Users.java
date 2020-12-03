package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.Pair;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.UserNameType;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Users extends MockUnitBase implements MockUnitString {

    private static final Double UNDERSCORE = 0.15;

    public static Users users() {
        return MockNeat.threadLocal().users();
    }

    protected Users() { }

    public Users(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return types(UserNameType.values()).supplier();
    }

    public MockUnitString type(UserNameType type) {
        notNull(type, "type");
        Supplier<String> supplier = () -> generateUserName(type);
        return () -> supplier;
    }

    public MockUnitString types(UserNameType... types) {
        notEmptyOrNullValues(types, "types");
        return () -> {
            UserNameType type = mockNeat.from(types).val();
            return type(type).supplier();
        };
    }

    private String generateUserName(UserNameType type) {
        Pair<DictType, DictType> pair = mockNeat.from(type.getDictCombos()).val();

        String part1 =
                mockNeat.dicts().type(pair.getFirst()).format(LOWER_CASE).val();
        String part2 =
                mockNeat.dicts().type(pair.getSecond()).format(LOWER_CASE).val();

        if (mockNeat.bools().probability(UNDERSCORE).val()) {
            part1 += "_";
        }


        return part1 + part2;
    }
}
