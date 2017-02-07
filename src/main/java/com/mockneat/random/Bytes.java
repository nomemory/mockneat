//package com.mockneat.random;
//
//import com.mockneat.utils.ArrayUtils;
//
//import java.util.Random;
//import java.util.stream.Stream;
//
//import static com.mockneat.utils.ArrayUtils.toWrapperArray;
//import static com.mockneat.utils.NextUtils.checkByteAlphabet;
//import static com.mockneat.utils.NextUtils.checkSize;
//
//public class Bytes {
//    private Rand rand;
//    private Random random;
//
//    protected Bytes(Rand rand) {
//        this.rand = rand;
//        this.random = rand.getRandom();
//    }
//
//    /**
//     * Generates an array of random bytes (Byte[]) of a given size.
//     * <p>
//     * !Important! This method is considerable more slower than the
//     * nextBytesPrimitive method because it copies the primitive array
//     * element by element to the Wrapper array.
//     *
//     * @param size Must be a positive number different than 0.
//     * @return An array of bytes (Wrapper).
//     */
//    public Byte[] arrayObj(Integer size) {
//        return ArrayUtils.toWrapperArray(array(size));
//    }
//
//    /**
//     * Generates an array of random bytes (byte[]) of a given size.
//     *
//     * @param size Must be a positive number different than 0.
//     * @return An array of bytes (Primitive).
//     */
//    public byte[] array(Integer size) {
//        checkSize(size);
//        byte[] result = new byte[size];
//        random.nextBytes(result);
//        return result;
//    }
//
//    /**
//     * Returns a (pseudo)random val from the rChars array.
//     * All possible Byte stream (from the array) are produced with aproximately the same probability.
//     *
//     * @param alphabet Must be non-null and non-empty (rChars.length!=0)
//     * @return The name possible (pseudo)random number, uniformly distributed from the rChars array.
//     */
//    public Byte valueFrom(byte[] alphabet) {
//        checkByteAlphabet(alphabet);
//        int randIdx = rand.ints().bound(alphabet.length).val();
//        return alphabet[randIdx];
//    }
//
//    /**
//     * Returns a (pseudo)random val from the rChars array.
//     * All possible Byte stream (from the array) are produced with aproximately the same probability.
//     *
//     * @param alphabet Must be non-null and non-empty (rChars.length!=0)
//     * @return The name possible (pseudo)random number, uniformly distributed from the rChars array.
//     */
//    public Stream<Byte> streamFrom(byte[] alphabet) {
//        return Stream.generate(() -> valueFrom(alphabet));
//    }
//
//
//}
