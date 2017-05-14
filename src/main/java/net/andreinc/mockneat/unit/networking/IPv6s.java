package net.andreinc.mockneat.unit.networking;

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
import net.andreinc.mockneat.utils.LoopsUtils;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import static net.andreinc.mockneat.alphabets.Alphabets.HEXA_STR;

public class IPv6s extends MockUnitBase implements MockUnitString {

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
        final StringBuilder buff = new StringBuilder("");
        LoopsUtils.loop(8, () -> buff.append(group()).append(":"));
        buff.deleteCharAt(buff.length()-1);
        return buff.toString();
    }
}
