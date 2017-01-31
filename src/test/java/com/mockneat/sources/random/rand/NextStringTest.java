//package net.andreinc.jchaos.sources.random.rand;
//
//import junit.framework.Assert;
//import FunctUtils;
//import org.junit.Test;
//
//import static java.util.Arrays.stream;
//import static RandTestConstants.NEXT_STRING_CYCLES;
//import static RandTestConstants.RAND;
//import static RandTestConstants.RANDS;
//import static FunctUtils.cycle;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class NextStringTest {
//
//    /**
//     * Test if the methdod:
//     * > String nextString(String rChars, Integer size)
//     * Is returnning the correct stream
//     * @throws Exception
//     */
//    @Test
//    public void testNextStringcorrectValues() throws Exception {
//        String alphabet = "0";
//        cycle(NEXT_STRING_CYCLES, () ->
//            stream(RANDS).forEach(r -> assertEquals("000", r.nextString(alphabet, 3))));
//    }
//
//    /**
//     * Test if the methdod:
//     * > String nextString(String rChars, Integer size)
//     * Is returning the correct stream
//     * @throws Exception
//     */
//    @Test
//    public void testNextStringcorrectValues2() throws Exception {
//        String alphabet = "ABCDE";
//        Integer size = 10;
//        cycle(NEXT_STRING_CYCLES, () ->
//                stream(RANDS)
//                        .map(r -> r.nextString(alphabet, size))
//                        .forEach(s ->
//                                s.chars().forEach(c -> assertTrue(alphabet.indexOf(c) >= 0))));
//    }
//
//    /**
//     * Test if the method:
//     * > String nextString(String rChars, Integer size)
//     * Doesn't accept the NULL as an rChars
//     * @throws Exception
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testNextStringNotNullAlphabet() throws Exception {
//        String alphabet = null;
//        RAND.nextString(alphabet, 10);
//    }
//
//    /**
//     * Test if the method:
//     * > String nextString(String rChars, Integer size)
//     * Doesn't accept an empty string as an rChars
//     * @throws Exception
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testNextStringNotEmptyAlphabet() throws Exception {
//        String alphabet = "";
//        RAND.nextString(alphabet, 10);
//    }
//
//    /**
//     * Test if the method:
//     * > String nextString(String rChars, Integer size)
//     * Doesn't accept an null size
//     * @throws Exception
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testNextStringNotNullSize() throws Exception {
//        String alphabet = "abc";
//        RAND.nextString(alphabet, null);
//    }
//
//}
