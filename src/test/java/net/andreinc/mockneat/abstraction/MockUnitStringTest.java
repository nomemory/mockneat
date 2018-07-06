package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.types.enums.StringFormatType;
import net.andreinc.mockneat.types.enums.StringType;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.types.enums.StringType.LETTERS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.*;
import static org.junit.Assert.*;

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

public class MockUnitStringTest {


    @Test(expected = IllegalArgumentException.class)
    public void testAccumulateZeroTimes() throws Exception {
        M.strings().accumulate(0, ",");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAccumulateNegativeTimes() throws Exception {
        M.strings().accumulate(-1, ",");
    }

    @Test(expected = NullPointerException.class)
    public void testAccumulateNullSeparator() throws Exception {
        M.strings().accumulate(10, null);
    }

    @Test
    public void testAccumulateEmptySeparator() {
        final String[] strings = new String[] {"A", "B", "C" };
        loop(
                STRING_CYCLES,
                MOCKS,
                m -> m.fromStrings(strings).accumulate(10, "").val(),
                str -> {
                    assertTrue(str.length()==10);
                    for(char c : str.toCharArray()) {
                        assertTrue(c == 'A' || c == 'B' || c == 'C');
                    }
                }
        );
    }

    @Test
    public void testAccumulateOneCharSeparator() {
        final String[] strings = new String[] { "A" };
        String result = M.fromStrings(strings).accumulate(10, ",").val();
        assertTrue(10 == countMatches(result, "A"));
        assertTrue(9 == countMatches(result, ","));
    }

    @Test
    public void testAccumulatTwoCharSeparator() {
        final String[] strings = new String[] { "A" };
        String result = M.fromStrings(strings).accumulate(10, ";;").val();
        assertTrue(10 == countMatches(result, "A"));
        assertTrue(9 == countMatches(result, ";;"));
    }

    @Test(expected = NullPointerException.class)
    public void testFormatNull() throws Exception {
        M.strings().format(null).val();
    }

    @Test
    public void testFormats() {
        loop(
            STRING_CYCLES, MOCKS,
            m -> {
                StringFormatType type = m.from(StringFormatType.class).val();
                String result = m.strings().type(LETTERS).format(type).val();

                assertTrue(result != null);

                switch (type) {
                    case CAPITALIZED: { assertTrue(Character.isUpperCase(result.charAt(0))); break; }
                    case LOWER_CASE: { assertTrue(isAllLowerCase(result)); break; }
                    case UPPER_CASE: { assertTrue(isAllUpperCase(result)); break; }
                }
            }
        );
    }

    @Test
    public void testSubString() {
        String[] testString = { "TEST1" };

        String t1 = M.from(testString).mapToString().sub(0, 1).val();

        assertTrue(t1!=null);
        assertTrue(t1.equals("T"));

        String t2 = M.from(testString).mapToString().sub(1, 2).val();

        assertTrue(t2 != null);
        assertTrue(t2.equals("E"));

        String t3 = M.from(testString).mapToString().sub(0, 5).val();
        assertTrue(t3 != null);
        assertTrue(t3.equals("TEST1"));

        String t4 = M.from(testString).mapToString().sub(3).val();
        assertTrue(t4 != null);
        assertTrue(t4.equals("TES"));
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testSubStringInvalidIndex() throws Exception {
        String[] testString =  { "TEST1" };
        M.from(testString).mapToString().sub(-1, -5).val();
    }

    @Test(expected = NullPointerException.class)
    public void testAppendNull() throws Exception {
        String[] testString = { "A" };
        M.from(testString).mapToString().append(null).append("4").val();
    }

    @Test
    public void testAppend() throws Exception {
        String c = M.strings().size(5).append("A").append("B").val();
        assertTrue(c.endsWith("AB"));
    }

    @Test(expected = NullPointerException.class)
    public void testPrependNull() throws Exception {
        M.strings().prepend(null).val();
    }

    @Test
    public void testPrepend() throws Exception {
        String c = M.strings().size(5).prepend("A").prepend("B").val();
        assertTrue(c.startsWith("BA"));
    }

    @Test
    public void testReplaceChrs() throws Exception {
        String[] strs = { "aaa", "aab", "aac", "aa1" };

        M.from(strs)
                .mapToString()
                .replace('a', 'x')
                .stream().val()
                .limit(STRING_CYCLES)
                .forEach(s  -> assertTrue(s.startsWith("xx")));
    }

    @Test
    public void testReplaceStr() throws Exception {
        M.regex("AB[0-9][0-9]CD")
                .replace("AB", "AC")
                .stream().val()
                .limit(STRING_CYCLES)
                .forEach(s -> assertTrue(s.startsWith("AC")));
    }

    @Test(expected = NullPointerException.class)
    public void testReplaceStrNullTarget() throws Exception {
        M.strings().replace(null, "B").val();
    }

    @Test
    public void testReplaceAll() throws Exception {
        M.regex("A[0-9]A[1-5]A[a-z]")
                .replaceAll("A", "X")
                .stream().val()
                .limit(STRING_CYCLES)
                .forEach(s -> {
                    assertTrue(s.charAt(0) == 'X');
                    assertTrue(s.charAt(2) == 'X');
                    assertTrue(s.charAt(4) == 'X');
                });
    }

    @Test
    public void testReplaceFirst() throws Exception {
        M.regex("A[0-9][abc]A")
                .replaceFirst("A", "B")
                .stream().val()
                .limit(STRING_CYCLES)
                .forEach(s -> {
                    assertTrue(s.charAt(0) == 'B');
                    assertTrue(s.charAt(3) == 'A');
                });
    }

    @Test
    public void testSplitNull() throws Exception {
        String[] s = { null };
        assertNull(M.from(s).mapToString().split("\\.").val());
    }

    @Test
    public void testSplit() throws Exception {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m  -> m.regex("[a-z];[a-z];[a-z]").split(";").val(),
                arr -> {
                    assertNotNull(arr);
                    assertTrue(arr.length == 3);
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testEncodeUrlNullEncoding() {
        M.strings().urlEncode(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEncodeUrlInvalidEncoding() {
        M.strings().urlEncode("INVALID_ENCODING").val();
    }

    @Test
    public void testEncodeUrl() {
        String[] urls = { "A B C ™" };
        String encodedUrl = M.from(urls).mapToString().urlEncode().val();
        assertTrue("A+B+C+%E2%84%A2".equals(encodedUrl));
    }

    @Test
    public void testNoSpecialChars() {
        loop(
                true,
                STRING_CYCLES,
                MOCKS,
                m -> m.strings().types(StringType.SPECIAL_CHARACTERS).noSpecialChars().val(),
                str -> assertTrue(str.length()==0)
        );
    }

    @Test
    public void testEscapeCsv() {
        String escapedString =
        M.from(new String[]{"\"Already Quoted\""})
         .mapToString()
         .escapeCsv()
         .val();

        assertTrue("\"\"\"Already Quoted\"\"\"".equals(escapedString));
    }

    @Test
    public void testEscapeEcmaScript() {
        String[] badBoyString = { "Hello ol' \"boy\"" };
        String escaped = M.from(badBoyString)
                            .mapToString()
                            .escapeEcmaScript()
                            .val();
        assertTrue(escaped.equals("Hello ol\\' \\\"boy\\\""));
    }

    @Test
    public void testEscapeXml() {
        String[] badBoyString = { "{@code \"bread\" & \"butter\"}" };
        String escaped = M.from(badBoyString)
                            .mapToString()
                            .escapeXml()
                            .val();
        assertTrue(escaped.equals("{@code &quot;bread&quot; &amp; &quot;butter&quot;}"));
    }

    @Test
    public void testEscapeHtml() {
        String[] badBoyString = {"\"bread\" &amp; \"butter\""};
        String escaped = M.from(badBoyString)
                            .mapToString()
                            .escapeHtml()
                            .val();
        assertTrue(escaped.equals("&quot;bread&quot; &amp;amp; &quot;butter&quot;"));
    }

    @Test
    public void testMd2() {
        loop(
                STRING_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.strings().md2().val(),
                md2 -> {
                    assertTrue(32==md2.length());
                    assertTrue(isAlphanumeric(md2));
                }
        );
    }

    @Test
    public void testMd5() {
        loop(
                STRING_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.strings().md5().val(),
                md5 -> {
                    assertTrue(32==md5.length());
                    assertTrue(isAlphanumeric(md5));
                }
        );
    }

    @Test
    public void testSha1() {
        loop(
                STRING_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.strings().sha1().val(),
                sha1 -> assertTrue(sha1.length()==40)
        );
    }

    @Test
    public void testSha256() {
        loop(
                STRING_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.strings().sha256().val(),
                sha256 -> assertTrue(sha256.length()==64)
        );
    }

    @Test
    public void testSha384() {
        loop(
                STRING_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.strings().sha384().val(),
                sha384 -> assertTrue(sha384.length()==96)
        );
    }

    @Test
    public void testSha512() {
        loop(
                STRING_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.strings().sha512().val(),
                sha512 -> assertTrue(sha512.length()==128)
        );
    }


    @Test
    public void testBase64() {
        loop(
                STRING_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.strings().base64().val(),
                base64 -> assertTrue(base64.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$"))
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayNegativeSize() {
        M.strings().array(-1).val();
    }

    @Test
    public void testArray() {
        M.from(new String[]{"a", "b"})
                .mapToString()
                .array(100)
                .consume(arr ->
                            Arrays.stream(arr)
                                  .forEach(el ->
                                          assertTrue("a".equals(el) || "b".equals(el))));
    }
}
