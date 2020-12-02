package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.types.enums.StringFormatType;
import net.andreinc.mockneat.types.enums.StringType;
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
    public void testAccumulateZeroTimes() {
        M.strings().accumulate(0, ",");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAccumulateNegativeTimes() {
        M.strings().accumulate(-1, ",");
    }

    @Test(expected = NullPointerException.class)
    public void testAccumulateNullSeparator() {
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
                    assertEquals(10, str.length());
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
        assertEquals(10, countMatches(result, "A"));
        assertEquals(9, countMatches(result, ","));
    }

    @Test
    public void testAccumulatTwoCharSeparator() {
        final String[] strings = new String[] { "A" };
        String result = M.fromStrings(strings).accumulate(10, ";;").val();
        assertEquals(10, countMatches(result, "A"));
        assertEquals(9, countMatches(result, ";;"));
    }

    @Test(expected = NullPointerException.class)
    public void testFormatNull() {
        M.strings().format(null).val();
    }

    @Test
    public void testFormats() {
        loop(
            STRING_CYCLES, MOCKS,
            m -> {
                StringFormatType type = m.from(StringFormatType.class).val();
                String result = m.strings().type(LETTERS).format(type).val();

                assertNotNull(result);

                switch (type) {
                    case CAPITALIZED: { assertTrue(Character.isUpperCase(result.charAt(0))); break; }
                    case LOWER_CASE: { assertTrue(isAllLowerCase(result)); break; }
                    case UPPER_CASE: { assertTrue(isAllUpperCase(result)); break; }
                    default: throw new IllegalArgumentException("Invalid StringFormatType.");
                }
            }
        );
    }

    @Test
    public void testSubString() {
        String[] testString = { "TEST1" };

        String t1 = M.from(testString).mapToString().sub(0, 1).val();

        assertNotNull(t1);
        assertEquals("T", t1);

        String t2 = M.from(testString).mapToString().sub(1, 2).val();

        assertNotNull(t2);
        assertEquals("E", t2);

        String t3 = M.from(testString).mapToString().sub(0, 5).val();
        assertNotNull(t3);
        assertEquals("TEST1", t3);

        String t4 = M.from(testString).mapToString().sub(3).val();
        assertNotNull(t4);
        assertEquals("TES", t4);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testSubStringInvalidIndex() {
        String[] testString =  { "TEST1" };
        M.from(testString).mapToString().sub(-1, -5).val();
    }

    @Test(expected = NullPointerException.class)
    public void testAppendNull() {
        String[] testString = { "A" };
        M.from(testString).mapToString().append(null).append("4").val();
    }

    @Test
    public void testAppend() {
        String c = M.strings().size(5).append("A").append("B").val();
        assertTrue(c.endsWith("AB"));
    }

    @Test(expected = NullPointerException.class)
    public void testPrependNull() {
        M.strings().prepend(null).val();
    }

    @Test
    public void testPrepend() {
        String c = M.strings().size(5).prepend("A").prepend("B").val();
        assertTrue(c.startsWith("BA"));
    }

    @Test
    public void testReplaceChrs() {
        String[] strs = { "aaa", "aab", "aac", "aa1" };

        M.from(strs)
                .mapToString()
                .replace('a', 'x')
                .stream().val()
                .limit(STRING_CYCLES)
                .forEach(s  -> assertTrue(s.startsWith("xx")));
    }

    @Test
    public void testReplaceStr() {
        M.regex("AB[0-9][0-9]CD")
                .replace("AB", "AC")
                .stream().val()
                .limit(STRING_CYCLES)
                .forEach(s -> assertTrue(s.startsWith("AC")));
    }

    @Test(expected = NullPointerException.class)
    public void testReplaceStrNullTarget() {
        M.strings().replace(null, "B").val();
    }

    @Test
    public void testReplaceAll() {
        M.regex("A[0-9]A[1-5]A[a-z]")
                .replaceAll("A", "X")
                .stream().val()
                .limit(STRING_CYCLES)
                .forEach(s -> {
                    assertEquals('X', s.charAt(0));
                    assertEquals('X', s.charAt(2));
                    assertEquals('X', s.charAt(4));
                });
    }

    @Test
    public void testReplaceFirst() {
        M.regex("A[0-9][abc]A")
                .replaceFirst("A", "B")
                .stream().val()
                .limit(STRING_CYCLES)
                .forEach(s -> {
                    assertEquals('B', s.charAt(0));
                    assertEquals('A', s.charAt(3));
                });
    }

    @Test
    public void testSplitNull() {
        String[] s = { null };
        assertNull(M.from(s).mapToString().split("\\.").val());
    }

    @Test
    public void testSplit() {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m  -> m.regex("[a-z];[a-z];[a-z]").split(";").val(),
                arr -> {
                    assertNotNull(arr);
                    assertEquals(3, arr.length);
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
        assertEquals("A+B+C+%E2%84%A2", encodedUrl);
    }

    @Test
    public void testNoSpecialChars() {
        loop(
                true,
                STRING_CYCLES,
                MOCKS,
                m -> m.strings().types(StringType.SPECIAL_CHARACTERS).noSpecialChars().val(),
                str -> assertEquals(0, str.length())
        );
    }

    @Test
    public void testEscapeCsv() {
        String escapedString =
        M.from(new String[]{"\"Already Quoted\""})
         .mapToString()
         .escapeCsv()
         .val();

        assertEquals("\"\"\"Already Quoted\"\"\"", escapedString);
    }

    @Test
    public void testEscapeEcmaScript() {
        String[] badBoyString = { "Hello ol' \"boy\"" };
        String escaped = M.from(badBoyString)
                            .mapToString()
                            .escapeEcmaScript()
                            .val();
        assertEquals("Hello ol\\' \\\"boy\\\"", escaped);
    }

    @Test
    public void testEscapeXml() {
        String[] badBoyString = { "{@code \"bread\" & \"butter\"}" };
        String escaped = M.from(badBoyString)
                            .mapToString()
                            .escapeXml()
                            .val();
        assertEquals("{@code &quot;bread&quot; &amp; &quot;butter&quot;}", escaped);
    }

    @Test
    public void testEscapeHtml() {
        String[] badBoyString = {"\"bread\" &amp; \"butter\""};
        String escaped = M.from(badBoyString)
                            .mapToString()
                            .escapeHtml()
                            .val();
        assertEquals("&quot;bread&quot; &amp;amp; &quot;butter&quot;", escaped);
    }

    @Test
    public void testMd2() {
        loop(
                STRING_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.strings().md2().val(),
                md2 -> {
                    assertEquals(32, md2.length());
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
                    assertEquals(32, md5.length());
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
                sha1 -> assertEquals(40, sha1.length())
        );
    }

    @Test
    public void testSha256() {
        loop(
                STRING_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.strings().sha256().val(),
                sha256 -> assertEquals(64, sha256.length())
        );
    }

    @Test
    public void testSha384() {
        loop(
                STRING_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.strings().sha384().val(),
                sha384 -> assertEquals(96, sha384.length())
        );
    }

    @Test
    public void testSha512() {
        loop(
                STRING_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.strings().sha512().val(),
                sha512 -> assertEquals(128, sha512.length())
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
