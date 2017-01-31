package com.mockneat.utils;


import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Created by andreinicolinciobanu on 19/12/2016.
 */
public class StringUtils {
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String CR = "\r";
    public static final String LF = "\n";

    public static Boolean isEmpty(CharSequence str) {
        return null == str || 0 == str.length();
    }

    public static Boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    // FROM APACHE COMMONS LANG
    public static String capitalize(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final int firstCodepoint = str.codePointAt(0);
        final int newCodePoint = Character.toTitleCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            // already capitalized
            return str;
        }

        final int newCodePoints[] = new int[strLen];
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint;
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
            final int codepoint = str.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint;
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    public static String upperCase(String str) {
        if (isEmpty(str)) return str;
        else return str.toUpperCase();
    }

    public static String lowerCase(String str) {
        if (isEmpty(str)) return str;
        else return str.toLowerCase();
    }

    public static String basicL33t(String str) {
        if (isEmpty(str))
            return str;
        StringBuilder buff = new StringBuilder();
        for(Character chr : str.toCharArray()) {
            if (chr.equals('A') || chr.equals('a'))
                buff.append(4);
            else if (chr.equals('E') || chr.equals('e'))
                buff.append(3);
            else if (chr.equals('I') || chr.equals('i'))
                buff.append(1);
            else if (chr.equals('O') || chr.equals('o'))
                buff.append(0);
            else
                buff.append(chr);
        }
        return buff.toString();
    }

    public static String escapeForUsername(String raw) {
        return raw.replaceAll("['-]","");
    }

    public static boolean allUppercase(String str) {
        return !IntStream
                    .range(0, str.length())
                    .filter(i -> Character.isLowerCase(str.charAt(i)))
                    .findFirst()
                    .isPresent();
    }

    public static boolean allLowerCase(String str) {
        return !IntStream
                    .range(0, str.length())
                    .filter(i -> Character.isUpperCase((str.charAt(i))))
                    .findFirst()
                    .isPresent();
    }
}
