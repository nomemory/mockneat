//package com.mockneat.random.rand;
//
//import org.junit.Test;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static java.util.Arrays.asList;
//import static java.util.Arrays.stream;
//import static com.mockneat.random.rand.RandTestConstants.NEXT_BYTES_CYLCES;
//import static com.mockneat.random.rand.RandTestConstants.RAND;
//import static com.mockneat.random.rand.RandTestConstants.RANDS;
//import static com.mockneat.utils.ArrayUtils.toWrapperArray;
//import static com.mockneat.utils.FunctUtils.cycle;
//import static org.junit.Assert.assertTrue;
//
//public class BytesTest {
//
//    /**
//     * Tests if the method:
//     * >> Byte[] nextBytes(Integer size)
//     * Doesn't accept null for its size
//     * @throws Exception
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testNextBytesNotNullSize() throws Exception {
//        RAND.bytes().valueFrom(null);
//    }
//
//    /**
//     * Tests if the method:
//     * >> Byte[] nextBytes(Integer size)
//     * Doesn't accept 0 for its size
//     * @throws Exception
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testNextBytesNonZeroSize() throws Exception {
//        RAND.bytes().array(0);
//    }
//
//    /**
//     * Tests if the method:
//     * >> Byte[] nextBytes(Integer size)
//     * Doesn't accept negative stream for its size
//     * @throws Exception
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testNextBytesNonNegativeSize() throws Exception {
//        RAND.bytes().array(-100);
//    }
//
//    /**
//     * Tests if the method:
//     * >> Byte[] nextBytes(Integer size)
//     * Always return the correct array size
//     * @throws Exception
//     */
//    @Test
//    public void testNextBytesCorrectSize() throws Exception {
//        int size = 30;
//        cycle(NEXT_BYTES_CYLCES, () ->
//            stream(RANDS)
//                    .map(r -> r.bytes().array(size))
//                    .forEach(arr -> assertTrue(size == arr.length)));
//    }
//
//    /**
//     * Tests if the method:
//     * >> Byte name(byte[] rChars)
//     * Always return a val from the existing rChars
//     * @throws Exception
//     */
//    @Test
//    public void testNextByteCorrectValues() throws Exception {
//        byte[] bytes = { -128, -5, 0, 1, 5, 10, 15, 21, 25, 31, 100, 127 };
//        Set<Byte> possibleValues = new HashSet<>(asList(toWrapperArray(bytes)));
//
//        cycle(NEXT_BYTES_CYLCES, () ->
//            stream(RANDS)
//                    .map(r -> r.bytes().valueFrom(bytes))
//                    .forEach(b -> assertTrue(possibleValues.contains(b))));
//    }
//
//    /**
//     * Tests if the method:
//     * >> Byte name(byte[] rChars)
//     * Doesn't accept a null rChars
//     * @throws Exception
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testNextNullNotAlphabet() throws Exception {
//        byte[] alphabet = null;
//        RAND.bytes().valueFrom(alphabet);
//    }
//
//    /**
//     * Tests if the method:
//     * >> Byte name(byte[] rChars)
//     * Doesn't accept an empty array rChars
//     * @throws Exception
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testNextEmptyArrayNotAlphabet() throws Exception {
//        byte[] alphabet = new byte[] {};
//        RAND.bytes().valueFrom(alphabet);
//    }
//}
