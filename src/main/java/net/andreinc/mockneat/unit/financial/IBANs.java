package net.andreinc.mockneat.unit.financial;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.Pair;
import net.andreinc.mockneat.types.enums.CharsType;
import net.andreinc.mockneat.types.enums.IBANType;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static java.lang.Character.toLowerCase;
import static java.math.BigInteger.valueOf;
import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.alphabets.Alphabets.DIGITS;
import static net.andreinc.mockneat.alphabets.Alphabets.LETTERS_UPPERCASE;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmpty;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class IBANs extends MockUnitBase implements MockUnitString {

    private static final Map<Character, String> VALUE_MAP = new ConcurrentHashMap<>();

    static {
        // Initialize a map:
        // (A, a) -> 10
        // (B, b) -> 11
        // ...
        // (Z, z) -> 35
        range(0, LETTERS_UPPERCASE.size()).forEach( i -> {
            Character upper = LETTERS_UPPERCASE.get(i);
            Character lower =  toLowerCase(upper);
            String value = valueOf((long)i + 10).toString();
            VALUE_MAP.put(lower, value);
            VALUE_MAP.put(upper, value);
        });

        range(0, DIGITS.size())
            .forEach( i ->
                VALUE_MAP.put(DIGITS.get(i), DIGITS.get(i).toString())
            );
    }

    public static IBANs ibans() { return MockNeat.threadLocal().ibans(); }

    protected IBANs() { }

    public IBANs(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return () -> {
            IBANType type = mockNeat.from(IBANType.class).val();
            return generate(type);
        };
    }

    /**
     * This method returns a valid IBAN code from the given supplied country.
     *
     * @param ibanType The country
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString type(IBANType ibanType) {
        notNull(ibanType, "ibanType");
        return () -> () -> generate(ibanType);
    }

    /**
     * This method returns a valid IBAN code from the given supplied countries.
     *
     * @param ibanTypes The countries from which the selection is randomly being done.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString types(IBANType... ibanTypes) {
        notEmpty(ibanTypes, "ibanTypes");
        return () -> () -> {
            IBANType type = mockNeat.from(ibanTypes).val();
            return generate(type);
        };
    }

    private String generate(IBANType ibanType) {

        List<Pair<Integer, CharsType>> bbanGroup = ibanType.getBban();
        String prefix = ibanType.getPrefix();

        // Will contain the final IBAN code as returned by the method
        final StringBuilder iban = new StringBuilder(ibanType.getLength());
        // Will contain the bban section (the numbers that come after <CountryCode><CheckDigits>
        final StringBuilder bban = new StringBuilder(ibanType.getLength() - 4);
        // Will be used to calculate the check digits
        final StringBuilder numeric = new StringBuilder(ibanType.getLength() * 2);

        // Apending the country prefix
        iban.append(prefix);

        // Generate BBAN and the NUMERIC IBAN needed to generate the check digits
        bbanGroup.forEach(pair -> {
            int groupLength = pair.getFirst();
            CharsType type = pair.getSecond();
            Character currentChar;
            while(groupLength-->0) {
                currentChar = mockNeat.chars().type(type).val();
                bban.append(currentChar);
                numeric.append(VALUE_MAP.get(currentChar));
            }
        });

        // Add prefix at the end of the numeric representation
        numeric.append(VALUE_MAP.get(prefix.charAt(0)))
               .append(VALUE_MAP.get(prefix.charAt(1)))
                // Check digits are added as 0s.
                .append("00");

        return iban.append(checkDigits(numeric.toString()))
                    .append(bban)
                    .toString();
    }

    public String checkDigits(String numericIBAN) {
        BigInteger bigInteger = new BigInteger(numericIBAN);
        int remainder = bigInteger.mod(valueOf(97)).intValue();

        int checkDigits = 98 - remainder;

        return (checkDigits<10) ?
                // if the remainder is only 1 digit,
                // we add a new 0
                "0" + checkDigits : String.valueOf(checkDigits);
    }
}
