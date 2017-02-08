package com.mockneat.random;

import com.mockneat.utils.FunctUtils;
import org.junit.Test;

import static com.mockneat.random.RandTestConstants.CHARS_CYCLES;
import static com.mockneat.random.RandTestConstants.RANDS;
import static java.util.Arrays.stream;

public class CharsTest {

    @Test
    public void testDigits() throws Exception {
        FunctUtils.cycle(CHARS_CYCLES, () -> {
            stream(RANDS).forEach(r -> {
                char c = r.chars().digits().val();
            });
        });
    }
}
