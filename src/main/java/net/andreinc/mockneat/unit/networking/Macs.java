package net.andreinc.mockneat.unit.networking;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.MACAddressFormatType;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import static net.andreinc.mockneat.types.enums.MACAddressFormatType.COLON_EVERY_2_DIGITS;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Macs extends MockUnitBase implements MockUnitString {

    public static Macs macs() {
        return MockNeat.threadLocal().macs();
    }

    public Macs() { }

    public Macs(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return type(COLON_EVERY_2_DIGITS)::val;
    }

    /**
     * <p>This method can be used to setup the format of the MAC address.</p>
     *
     * @param type The format.
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString type(MACAddressFormatType type) {
        notNull(type, "type");
        Supplier<String> supplier = () -> {
            StringBuilder buff = new StringBuilder();
            IntStream.range(0, 12).forEach(i -> type.getConsumer().consume(i, buff, this.mockNeat));
            return buff.deleteCharAt(0).toString();
        };
        return () -> supplier;
    }
}
