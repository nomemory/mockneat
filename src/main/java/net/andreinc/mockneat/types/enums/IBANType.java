package net.andreinc.mockneat.types.enums;

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

import net.andreinc.mockneat.types.Pair;

import java.util.List;

import static java.util.Arrays.asList;
import static net.andreinc.mockneat.types.Pair.of;
import static net.andreinc.mockneat.types.enums.CharsType.*;

@SuppressWarnings("ImmutableEnumChecker")
public enum IBANType {

    ALBANIA(
            8, "AL", of(8, DIGITS), of(16, ALPHA_NUMERIC)
    ),
    ANDORRA(
            24, "AD", of(8, DIGITS), of(12, ALPHA_NUMERIC)
    ),
    AUSTRIA(
            20, "AT", of(16, DIGITS)
    ),
    AZERBAIJAN(
            28, "AZ", of(4, ALPHA_NUMERIC), of(20, DIGITS)
    ),
    BAHRAIN(
            22, "BH", of(4, UPPER_LETTERS), of(14, ALPHA_NUMERIC)
    ),
    BELARUS(
            28, "BY", of(4, ALPHA_NUMERIC), of(20, DIGITS)
    ),
    BELGIUM(
            16, "BE", of(12, DIGITS)
    ),
    BOSNIA_AND_HERZEGOVINA(
            20, "BA", of(16, DIGITS)
    ),
    BRAZIL(
            29, "BR", of(23, DIGITS), of(1, UPPER_LETTERS), of(1, ALPHA_NUMERIC)
    ),
    BULGARIA(
            22, "BG", of(4, UPPER_LETTERS), of(6, DIGITS), of(8, ALPHA_NUMERIC)
    ),
    // EXCEPTION
    COSTA_RICA(
            22, "CR", of(17, DIGITS)
    ),
    CYPRUS(
            28, "CY", of(8, DIGITS), of(16, ALPHA_NUMERIC)
    ),
    CZECH_REPUBLIC(
            24, "CZ", of(20, DIGITS)
    ),
    DENMARK(
            18, "DK", of(14, DIGITS)
    ),
    DOMINICAN_REPUBLIC(
            28, "DO", of(4, UPPER_LETTERS), of(20, DIGITS)
    ),
    EAST_TIMOR(
            23, "TL", of(19, DIGITS)
    ),
    ESTONIA(
            20, "EE", of(16, DIGITS)
    ),
    FAROE_ISLANDS(
            18, "FO", of(14, DIGITS)
    ),
    FINLAND(
            18, "FI", of(14, DIGITS)
    ),
    FRANCE(
            27, "FR", of(10, DIGITS), of(11, ALPHA_NUMERIC), of(2, DIGITS)
    ),
    GEORGIA(
            22, "GE", of(2, ALPHA_NUMERIC), of(16, DIGITS)
    ),
    GERMANY(
            22, "DE", of(18, DIGITS)
    ),
    GIBRALTAR(
            23, "GI", of(4, UPPER_LETTERS), of(15, ALPHA_NUMERIC)
    ),
    GREECE(
            27, "GR", of(7, DIGITS), of(16, ALPHA_NUMERIC)
    ),
    GREENLAND(
            18, "GL", of(14, DIGITS)
    ),
    GUATEMALA(
            28, "GT", of(4, ALPHA_NUMERIC), of(20, ALPHA_NUMERIC)
    ),
    HUNGARY(
            28, "HU", of(24, DIGITS)
    ),
    ICELAND(
            26, "IS", of(22, DIGITS)
    ),
    IRELAND(
            22, "IE", of(4, ALPHA_NUMERIC), of(14, DIGITS)
    ),
    ISRAEL(
            23, "IL", of(19, DIGITS)
    ),
    ITALY(
            27, "IT", of(1, UPPER_LETTERS), of(10, DIGITS), of(12, ALPHA_NUMERIC)
    ),
    JORDAN(
            30, "JO", of(4, UPPER_LETTERS), of(22, DIGITS)
    ),
    KAZAKHSTAN(
            20, "KZ", of(3, DIGITS), of(13, ALPHA_NUMERIC)
    ),
    KOSOVO(
            20, "XK", of(4, DIGITS), of(10, DIGITS), of(2, DIGITS)
    ),
    KUWAIT(
            30, "KW", of(4, UPPER_LETTERS), of(22, ALPHA_NUMERIC)
    ),
    LATVIA(
            21, "LV", of(4, UPPER_LETTERS), of(13, ALPHA_NUMERIC)
    ),
    LEBANON(
            28, "LB", of(4, DIGITS), of(20, ALPHA_NUMERIC)
    ),
    LIECHTENSTEIN(
            21, "LI", of(5, DIGITS), of(12, ALPHA_NUMERIC)
    ),
    LITHUANIA(
            20, "LT", of(16, DIGITS)
    ),
    LUXEMBOURG(
            20, "LU", of(3, DIGITS), of(13, ALPHA_NUMERIC)
    ),
    MACEDONIA(
            19, "MK", of(3, DIGITS), of(10, ALPHA_NUMERIC), of(2, DIGITS)
    ),
    MALTA(
            31, "MT", of(4, UPPER_LETTERS), of(5, DIGITS), of(18, ALPHA_NUMERIC)
    ),
    MAURITANIA(
            27, "MR", of(23, DIGITS)
    ),
    MAURITIUS(
            30, "MU", of(4, UPPER_LETTERS), of(19, DIGITS), of(3, UPPER_LETTERS)
    ),
    MONACO(
            27, "MC", of(10, DIGITS), of(11, ALPHA_NUMERIC), of(2, DIGITS)
    ),
    MOLDOVA(
            24, "MD", of(2, ALPHA_NUMERIC), of(18, ALPHA_NUMERIC)
    ),
    MONTENEGRO(
            22, "ME", of(18, DIGITS)
    ),
    NETHERLANDS(
            18, "NL", of(4, UPPER_LETTERS), of(10, DIGITS)
    ),
    NORWAY(
            15, "NO", of(11, DIGITS)
    ),
    PAKISTAN(
            24, "PK", of(4, ALPHA_NUMERIC), of(16, DIGITS)
    ),
    PALESTINIAN_TERRITORIES(
            29, "PS", of(4, ALPHA_NUMERIC), of(21, DIGITS)
    ),
    POLAND(
            28, "PL", of(24, DIGITS)
    ),
    PORTUGAL(
            25, "PT", of(21, DIGITS)
    ),
    QATAR(
            29, "QA", of(4, UPPER_LETTERS), of(21, ALPHA_NUMERIC)
    ),
    ROMANIA(
            24, "RO", of(4, UPPER_LETTERS), of(16, ALPHA_NUMERIC)
    ),
    SAN_MARINO(
            27, "SM", of(1, UPPER_LETTERS), of(10, DIGITS), of(12, ALPHA_NUMERIC)
    ),
    SAUDI_ARABIA(
            24, "SA", of(2, DIGITS), of(18, ALPHA_NUMERIC)
    ),
    SERBIA(
            22, "RS", of(18, DIGITS)
    ),
    SLOVAKIA(
            24, "SK", of(20, DIGITS)
    ),
    SLOVENIA(
            19, "SI", of(15, DIGITS)
    ),
    SPAIN(
            24, "ES", of(20, DIGITS)
    ),
    SWEDEN(
            24, "SE", of(24, DIGITS)
    ),
    SWITZERLAND(
            21, "CH", of(5, DIGITS), of(12, ALPHA_NUMERIC)
    ),
    TUNISIA(
            24, "TN", of(20, DIGITS)
    ),
    TURKEY(
            26, "TR", of(5, DIGITS), of(17, ALPHA_NUMERIC)
    ),
    UAE(
            23, "AE", of(3, DIGITS), of(16, ALPHA_NUMERIC)
    ),
    UK(
            22, "GB", of(4, UPPER_LETTERS), of(14, DIGITS)
    );

    private final int length;
    private final String prefix;
    private final List<Pair<Integer, CharsType>> bban;
    private String checkDigits = null;

    IBANType(int length, String prefix, Pair<Integer, CharsType>... charsGroup) {
        this.length = length;
        this.prefix = prefix;
        this.bban = asList(charsGroup);
    }

    IBANType(int length, String checkDigits, String prefix, Pair<Integer, CharsType>... charsGroup) {
        this.length = length;
        this.checkDigits = checkDigits;
        this.prefix = prefix;
        this.bban = asList(charsGroup);
    }

    public int getLength() {
        return length;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<Pair<Integer, CharsType>> getBban() {
        return bban;
    }
}
