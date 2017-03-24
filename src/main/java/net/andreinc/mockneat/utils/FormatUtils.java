package net.andreinc.mockneat.utils;

import static net.andreinc.mockneat.utils.LoopsUtils.loop;

/**
 * Created by andreinicolinciobanu on 24/03/17.
 */
public class FormatUtils {
    public static String prependZeroesToSize(String string, int size) {
        if (string.length()<size) {
            int difference = size - string.length();
            StringBuilder buff = new StringBuilder();
            loop(difference, () -> buff.append("0"));
            buff.append(string);
            return buff.toString();
        }
        return string;
    }
}
