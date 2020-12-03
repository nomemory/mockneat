package net.andreinc.mockneat.unit.misc;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.FormatUtils.prependZeroesToSize;

public class SSCs extends MockUnitBase implements MockUnitString {

    public static SSCs sscs() {
        return MockNeat.threadLocal().sscs();
    }

    private SSCs() { }

    public SSCs(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * AAA-GG-SSSS
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
