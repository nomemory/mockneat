package net.andreinc.mockneat.unit.misc;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.FormatUtils.prependZeroesToSize;

/**
 * Created by andreinicolinciobanu on 24/03/17.
 */
public class SSCs implements MockUnitString {

    private final MockNeat mockNeat;

    public SSCs(MockNeat mockNeat) {
        this.mockNeat = mockNeat;
    }

    /**
     * AAA-GG-SSSS
     *
     * @return
     */
    @Override
    public Supplier<String> supplier() {
        return () -> generateAAA() + "-" + generateGG() + "-" + generateSSSS();
    }

    private String generateAAA() {
        Integer number = mockNeat.ints().range(1, 900).val();
        // "078051120" - Woolworth Wallet Fiasco
        // "219099999" - Used in add
        // 666-xx-xxxx - Doesn't work
        while(number==666 || number==78 || number==219)
            number = mockNeat.ints().range(1, 900).val();
        return prependZeroesToSize(number.toString(), 3);
    }

    private String generateGG() {
        return prependZeroesToSize(mockNeat.ints().range(1, 100).valStr(), 2);
    }

    private String generateSSSS() {
        return prependZeroesToSize(mockNeat.ints().range(1, 10000).valStr(), 4);
    }
}
