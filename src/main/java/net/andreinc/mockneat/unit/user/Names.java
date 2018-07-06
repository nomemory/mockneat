package net.andreinc.mockneat.unit.user;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

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
     * <p>Returns a new {@code MockUnitString} that can be used to generate arbitrary middle names (both male and female).</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString middle() { return type(MIDDLE_NAME); }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate middle names (males only).</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString middleAndMale() { return type(MIDDLE_NAME_MALE); }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate middle names (female only).</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString middleAndFemale() { return type(MIDDLE_NAME_FEMALE); }

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
        Supplier<String> supp = () -> first().val() + " " + middle().val() + " " + last().val();
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
        NameType nameType = mockNeat.from(types).val();
        return type(nameType);
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
