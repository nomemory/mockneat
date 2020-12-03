package net.andreinc.mockneat.types.enums;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.types.TriConsumer;

@SuppressWarnings("ImmutableEnumChecker")
public enum MACAddressFormatType {

    DASH_EVERY_2_DIGITS(MACAddressFormatType::line2Digits),
    COLON_EVERY_2_DIGITS(MACAddressFormatType::colon2Digits),
    DOT_EVERY_2_DIGITS(MACAddressFormatType::point2Digits),
    DOT_EVERY_4_DIGITS(MACAddressFormatType::point4Digits);

    private final TriConsumer<Integer, StringBuilder, MockNeat> consumer;

    MACAddressFormatType(TriConsumer<Integer, StringBuilder, MockNeat> consumer) {
        this.consumer = consumer;
    }

    public TriConsumer<Integer, StringBuilder, MockNeat> getConsumer() {
        return consumer;
    }

    private static void everyDigits(Integer i, StringBuilder buff, MockNeat rand, String chr, Integer digits) {
        if (i % digits == 0)
            buff.append(chr);
        buff.append(Integer.toHexString(rand.ints().range(0, 16).val()));
    }

    private static void line2Digits(Integer i, StringBuilder buff, MockNeat rand) {
        everyDigits(i, buff, rand, "-", 2);
    }

    private static void colon2Digits(Integer i, StringBuilder buff, MockNeat rand) {
        everyDigits(i, buff, rand, ":", 2);
    }

    private static void point2Digits(Integer i, StringBuilder buff, MockNeat rand) {
        everyDigits(i, buff, rand, ".", 2);
    }

    private static void point4Digits(Integer i, StringBuilder buff, MockNeat rand) {
        everyDigits(i, buff, rand, ".", 4);
    }
}
