package com.mockneat.types.enums;

import com.mockneat.sources.random.Rand;
import com.mockneat.types.TriConsumer;

/**
 * Created by andreinicolinciobanu on 21/12/2016.
 */
public enum MACAddressFormatType {
    DASH_EVERY_2_DIGITS(MACAddressFormatType::line2Digits),
    COLON_EVERY_2_DIGITS(MACAddressFormatType::colon2Digits),
    DOT_EVERY_2_DIGITS(MACAddressFormatType::point2Digits),
    DOT_EVERY_4_DIGITS(MACAddressFormatType::point4Digits);

    private TriConsumer<Integer, StringBuilder, Rand> consumer;

    public TriConsumer<Integer, StringBuilder, Rand> getConsumer() {
        return consumer;
    }

    MACAddressFormatType(TriConsumer<Integer, StringBuilder, Rand> consumer) {
        this.consumer = consumer;
    }

    private static void everyDigits(Integer i, StringBuilder buff, Rand rand, String chr, Integer digits) {
        if (i % digits == 0) buff.append(chr);
        buff.append(Integer.toHexString(rand.ints().inRange(0, 16).val()));
    }

    private static void line2Digits(Integer i, StringBuilder buff, Rand rand) {
        everyDigits(i, buff, rand, "-", 2);
    }

    private static void colon2Digits(Integer i, StringBuilder buff, Rand rand) {
        everyDigits(i, buff, rand, ":", 2);
    }

    private static void point2Digits(Integer i, StringBuilder buff, Rand rand) {
        everyDigits(i, buff, rand, ".", 2);
    }

    private static void point4Digits(Integer i, StringBuilder buff, Rand rand) {
        everyDigits(i, buff, rand, ".", 4);
    }
}
