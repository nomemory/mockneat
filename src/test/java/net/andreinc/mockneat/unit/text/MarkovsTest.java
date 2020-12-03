package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.types.enums.MarkovChainType;
import org.junit.Test;

import static net.andreinc.mockneat.unit.text.Markovs.markovs;

public class MarkovsTest {
    @Test
    public void test1() {
        markovs().type(MarkovChainType.LOREM_IPSUM).get();
    }
}
