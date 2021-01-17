package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.NameType;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.NameType.*;
import static net.andreinc.mockneat.utils.ValidationUtils.betweenClosed;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static org.apache.commons.lang3.Validate.notNull;

public class Names extends MockUnitBase implements MockUnitString {

    /**
     * <p>Returns a {@code Names} object that can be used to generate "names" (full names, first names or last names).</p>
     *
     * <p><em>Note:</em>By default it can be used to generate people full names.</p>
     *
     * <p><em>Note:</em> The names are the most common names that appear in the US.</p>
     *
     * @return A re-usable {@code Names} object. The {@code Names} class implements {@code MockUnitString}.
     */
    public static Names names() {
        return MockNeat.threadLocal().names();
    }

    protected Names() { }

    public Names(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return full().supplier();
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate arbitrary first names (both male and female).</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString first() { return type(FIRST_NAME); }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate first names (males only).</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString firstAndMale() { return type(FIRST_NAME_MALE); }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate first names (female only).</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString firstAndFemale() { return type(FIRST_NAME_FEMALE); }

    /**
     * <p>Returns a new {@code MockUnitstring} that can be used to generate last names.</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString last() { return type(LAST_NAME); }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate full names (First Name + Last Name).</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString full() {
        Supplier<String> supp = () -> first().val() + " " + last().val();
        return () -> supp;
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate full names including a middle name (with a given probability).</p>
     *
     * @param middleInitialProbability A double value between [0.0, 100.0] denoting the probability of the middle name to appear.
     *
     * @return A new {@code MockUnitString}
     */
    public MockUnitString full(double middleInitialProbability) {
        betweenClosed(middleInitialProbability, 0.0, 100.0);
        Supplier<String> supp = () -> {
            boolean middleName = mockNeat.bools().probability(middleInitialProbability).val();
            String initial = (middleName) ? " " + mockNeat.chars().upperLetters().val() + "." : "";
            return first().val() + initial + " " + last().val();
        };
        return () -> supp;
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate names in the given array of types.</p>
     *
     * @param types A var-arg array denoting the types of names that are generated.
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString types(NameType... types) {
        notEmptyOrNullValues(types, "types");
        return () -> {
            NameType nameType = mockNeat.from(types).val();
            return type(nameType).supplier();
        };
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate names of the given type.</p>
     *
     * @param type The type of name we want to generate.
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString type(NameType type) {
        notNull(type, "type");
        DictType dictType = mockNeat.from(type.getDictionaries()).val();
        return () -> mockNeat.dicts().type(dictType)::val;
    }
}
