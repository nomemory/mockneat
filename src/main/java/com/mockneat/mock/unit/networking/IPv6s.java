package com.mockneat.mock.unit.networking;

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.mock.utils.LoopsUtils;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.mockneat.alphabets.Alphabets.HEXA_STR;

/**
 * Created by andreinicolinciobanu on 20/02/2017.
 */
public class IPv6s implements MockUnitString {
    private MockNeat mock;
    public IPv6s(MockNeat mock) {
        this.mock = mock;
    }
    @Override
    public Supplier<String> supplier() {
        return this::ip;
    }
    protected String group() {
        String result = mock.from(HEXA_STR)
                            .stream().val()
                            .limit(4)
                            .collect(Collectors.joining())
                            .replaceAll("([0])\\1", "");
        return result.length() == 0 ? "0" : result;
    }
    protected String ip() {
        final StringBuilder buff = new StringBuilder("");
        LoopsUtils.loop(8, () -> buff.append(group()).append(":"));
        buff.deleteCharAt(buff.length()-1);
        return buff.toString();
    }
}
