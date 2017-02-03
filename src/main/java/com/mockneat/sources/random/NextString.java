package com.mockneat.sources.random;

import java.util.Random;
import java.util.stream.Stream;

import static com.mockneat.utils.NextUtils.checkSize;
import static com.mockneat.utils.NextUtils.checkStringAlpabet;

public class NextString {

    private Rand rand;

    protected NextString(Rand rand) {
        this.rand = rand;
    }


    /**
     * Returns a random string of a given size using only stream from another String
     * @param alphabet A non-null, non-empty String
     * @param size A non-null, bigger than 0 integer
     * @return
     */
    protected String nextString(String alphabet, Integer size) {
        checkStringAlpabet(alphabet);
        checkSize(size);
        StringBuilder builder = new StringBuilder();
        int cnt = size;
        while (cnt-- > 0)
            builder.append(rand.chars().from(alphabet).val());
        return builder.toString();
    }

    /**
     * Returns a names of random strings of a given size using only stream from another String
     * @param alphabet A non-null, non-empty String
     * @param size A non-null, bigger than 0 integer
     * @return
     */
    protected Stream<String> streamString(String alphabet, Integer size) {
        return Stream.generate(() -> nextString(alphabet, size));
    }

    /**
     * Get a random string from the String rChars.
     * The string will have the same length as the rChars.
     *
     * @param alphabet A non-null, non-empty String
     * @return
     */
    protected String nextString(String alphabet) {
        checkStringAlpabet(alphabet);
        return nextString(alphabet, alphabet.length());
    }

    /**
     * Get a names of random string stream from the String rChars.
     * The strings will have the same length as the rChars.
     *
     * @param alphabet A non-null, non-empty String
     * @return
     */
    protected Stream<String> streamString(String alphabet) {
        return Stream.generate(() -> nextString(alphabet));
    }

    /**
     * Get a random string, by randomizing the val positions from a givem String "rChars".
     * The positions swap are random numbers, so you might swap, but it's possible to swap the same positions.
     * To reduce the chances of producing the same String you need to have a String with more than 10 stream
     * and enough cycles.
     *
     * @param alphabet A non-null, non-empty String
     * @param cycles A non-null, bigger than 0 integer
     * @return
     */
    protected String nextStringRandomized(String alphabet, Integer cycles) {
        int cnt = cycles;
        char[] chars = alphabet.toCharArray();
        while(cnt --> 0) {
            swapCharacters(chars, rand.ints().withBound(chars.length).val(),
                    rand.ints().withBound(chars.length).val());
        }
        return new String(chars);
    }


    /**
     * Get a names of random strings, obtained by randomizing the val positions from a givem String "rChars".
     *
     * The positions swap are random numbers, so you might swap, but it's possible to swap the same positions.
     * To reduce the chances of producing the same String you need to have a String with more than 10 stream
     * and enough cycles.
     *
     * @param alphabet A non-null, non-empty String
     * @param cycles A non-null, bigger than 0 integer
     * @return
     */
    protected Stream<String> streamStringRandomized(String alphabet, Integer cycles) {
        return Stream.generate(() -> nextStringRandomized(alphabet, cycles));
    }

    protected void swapCharacters(char[] chars, Integer pos1, Integer pos2) {
        char tmp = chars[pos1];
        chars[pos1] = chars[pos2];
        chars[pos2] = tmp;
    }
}
