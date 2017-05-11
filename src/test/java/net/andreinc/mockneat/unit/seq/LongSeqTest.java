package net.andreinc.mockneat.unit.seq;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.util.List;

import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class LongSeqTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorMinBiggerMax() throws Exception {
        new LongSeq(0, 1, 10, 20, false);
    }

    @Test
    public void testIntSeq() throws Exception {
        loop(
                Constants.SEQ_CYCLES,
                Constants.MOCKS,
                m -> m.longSeq(),
                seq -> range(0, 100).forEach(i -> assertTrue(seq.val() == i))
        );
    }

    @Test
    public void testIntSeqCycle() throws Exception {
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
    public void testIntSeqListings() throws Exception {
        loop(
                Constants.SEQ_CYCLES,
                Constants.MOCKS,
                (m) -> {
                    int size = m.ints().range(100, 1000).val();
                    List<Long> lst = m.longSeq().start(1).list(size).val();
                    long sum = lst.stream().mapToLong(i -> i.longValue()).sum();
                    assertTrue(sum == size * (size + 1) / 2);
                }
        );
    }
}
