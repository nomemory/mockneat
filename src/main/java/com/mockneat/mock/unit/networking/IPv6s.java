package com.mockneat.mock.unit.networking;

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.mock.utils.FunctUtils;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.mockneat.alphabets.Alphabets.HEXA_STR;
import static com.mockneat.mock.utils.FunctUtils.loop;

/**
 * Created by andreinicolinciobanu on 20/02/2017.
 */
public class IPv6s implements MockUnitString {
    private MockNeat rand;
    public IPv6s(MockNeat rand) {
        this.rand = rand;
    }
    @Override
    public Supplier<String> supplier() {
        return () -> ip();
    }
    protected String group() {
        String result = rand.from(HEXA_STR)
                            .stream().val()
                            .limit(4)
                            .collect(Collectors.joining())
                            .replaceAll("([0])\\1", "");
        return result.length() == 0 ? "0" : result;
    }
    protected String ip() {
        final StringBuilder buff = new StringBuilder("");
        FunctUtils.loop(8, () -> buff.append(group()).append(":"));
        buff.deleteCharAt(buff.length()-1);
        return buff.toString();
    }
}
