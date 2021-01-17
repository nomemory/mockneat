package net.andreinc.mockneat.unit.networking;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.utils.LoopsUtils;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import static net.andreinc.mockneat.alphabets.Alphabets.HEXA_STR;

public class IPv6s extends MockUnitBase implements MockUnitString {

    /**
     * <p>Returns a {@code IPv6s} object that can be used to generate arbitrary IPv6 addresses.</p>
     *
     * @return A re-usable {@code IPv6s} object. The {@code IPv6s} class implements {@code MockUnitString}.
     */
    public static IPv6s ipv6s() {
        return MockNeat.threadLocal().iPv6s();
    }

    public IPv6s() { }

    public IPv6s(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return this::ip;
    }

    private String group() {
        String result = mockNeat.from(HEXA_STR)
                            .stream().val()
                            .limit(4)
                            .collect(Collectors.joining())
                            .replaceAll("([0])\\1", "");
        return result.length() == 0 ? "0" : result;
    }

    private String ip() {
        final StringBuilder buff = new StringBuilder();
        LoopsUtils.loop(8, () -> buff.append(group()).append(":"));
        buff.deleteCharAt(buff.length()-1);
        return buff.toString();
    }
}
