package net.andreinc.mockneat.unit.seq;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.util.List;

import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class IntSeqTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorMinBiggerMax() throws Exception {
        new IntSeq(0, 1, 10, 20, false);
    }

    @Test
    public void testIntSeq() throws Exception {
        loop(
                Constants.SEQ_CYCLES,
                Constants.MOCKS,
                m -> m.intSeq(),
                seq -> range(0, 100).forEach(i -> assertTrue(seq.val() == i))
        );
    }

    @Test
    public void testIntSeqCycle() throws Exception {
        loop(
                Constants.SEQ_CYCLES,
                Constants.MOCKS,
                m -> m.intSeq().start(10).max(15).cycle(true),
                seq ->
                    loop(1000, () -> {
                        int val = seq.val();
                        assertTrue(10 <= val);
                        assertTrue(val <= 15);
                    })
        );
    }

    @Test
    public void testIntSeqListings() throws Exception {
        loop(
                Constants.SEQ_CYCLES,
                Constants.MOCKS,
                (m) -> {
                    int size = m.ints().range(100, 1000).val();
                    List<Integer> lst = m.intSeq().start(1).list(size).val();
                    int sum = lst.stream().mapToInt(i -> i.intValue()).sum();
                    assertTrue(sum == size * (size + 1) / 2);
                }
        );
    }
}
