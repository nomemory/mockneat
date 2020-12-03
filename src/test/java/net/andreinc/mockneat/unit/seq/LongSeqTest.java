package net.andreinc.mockneat.unit.seq;

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.MockNeat;
import org.junit.Test;

import java.util.List;

import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LongSeqTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorMinBiggerMax() {
        new LongSeq(0, 1, 10, 20, false);
    }

    @Test
    public void testIntSeq() {
        loop(
                Constants.SEQ_CYCLES,
                Constants.MOCKS,
                MockNeat::longSeq,
                seq -> range(0, 100).forEach(i -> assertEquals((long) seq.val(), i))
        );
    }

    @Test
    public void testIntSeqCycle() {
        loop(
                Constants.SEQ_CYCLES,
                Constants.MOCKS,
                m -> m.longSeq().start(10).max(15).cycle(true),
                seq ->
                        loop(1000, () -> {
                            long val = seq.val();
                            assertTrue(10 <= val);
                            assertTrue(val <= 15);
                        })
        );
    }

    @Test
    public void testIntSeqListings() {
        loop(
                Constants.SEQ_CYCLES,
                Constants.MOCKS,
                (m) -> {
                    int size = m.ints().range(100, 1000).val();
                    List<Long> lst = m.longSeq().start(1).list(size).val();
                    long sum = lst.stream().mapToLong(i -> i).sum();
                    assertEquals(sum, (long) size * (size + 1) / 2);
                }
        );
    }
}
