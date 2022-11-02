package net.andreinc.mockneat.abstraction;

import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.*;

public interface MockUnitChar extends MockUnit<Character> {

    /**
     * <p>Transforms an existing {@code MockUnitChar} into a {@code MockUnitString}.</p>
     *
     * <p>The chars in the string will be generated using the {@code MockUnitChar} supplier
     * and length of string will be generated using the {@code MockUnitInt}.</p>
     *
     * @param lengthUnit The length of the String
     * @return A new {@code MockUnitString}
     */
    default MockUnitString string(MockUnitInt lengthUnit) {
        notNull(lengthUnit, "lengthUnit");
        Supplier<String> supp = () -> {
            int length = lengthUnit.get();
            isTrue(length >= 0, SIZE_BIGGER_THAN_ZERO);
            return stream().get()
                    .limit(length)
                    .reduce(new StringBuilder(length), StringBuilder::append, StringBuilder::append)
                    .toString();
        };
        return () -> supp;
    }

    /**
     * <p>Transforms an existing {@code MockUnitChar} into a {@code MockUnitString}.</p>
     *
     * <p>The chars in the string will be generated using the {@code MockUnitChar} supplier
     * and length of string will be generated using the {@code MockUnitInt}.</p>
     *
     * @param length The length of the String
     * @return A new {@code MockUnitString}
     */
    default MockUnitString string(int length) {
        isTrue(length >= 0, SIZE_BIGGER_THAN_ZERO);
        return string(() -> () -> length);
    }

}
