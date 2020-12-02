package net.andreinc.mockneat.utils;

/**
 * Copyright 2018, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */


import java.util.HashMap;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlEscapeUtils {

    private SqlEscapeUtils() {}

    public static final class MySQL {

        private static final HashMap<String,String> sqlTokens;
        private static final Pattern sqlTokenPattern;

        private MySQL() {}

        static
        {
            //MySQLText escape sequences: http://dev.mysql.com/doc/refman/5.1/en/string-syntax.html
            String[][] searchRegexReplacement = new String[][]
                    {
                            //search string     search regex        sql replacement regex
                            {   "\u0000"    ,       "\\x00"     ,       "\\\\0"     },
                            {     "'"         ,       "'"         ,       "\\\\'"     },
                            {   "\""        ,       "\""        ,       "\\\\\""    },
                            {   "\b"        ,       "\\x08"     ,       "\\\\b"     },
                            {   "\n"        ,       "\\n"       ,       "\\\\n"     },
                            {   "\r"        ,       "\\r"       ,       "\\\\r"     },
                            {   "\t"        ,       "\\t"       ,       "\\\\t"     },
                            {   "\u001A"    ,       "\\x1A"     ,       "\\\\Z"     },
                            {   "\\"        ,       "\\\\"      ,       "\\\\\\\\"  }
                    };

            sqlTokens = new HashMap<>();
            StringBuilder buff = new StringBuilder();
            for (String[] srr : searchRegexReplacement)
            {
                sqlTokens.put(srr[0], srr[2]);
                buff.append(buff.length() == 0 ? "" : "|").append(srr[1]);
            }
            String patternStr = buff.toString();
            sqlTokenPattern = Pattern.compile('(' + patternStr + ')');
        }

        private static String escape(String s) {
            Matcher matcher = sqlTokenPattern.matcher(s);
            StringBuffer sb = new StringBuffer();
            while(matcher.find()) {
                matcher.appendReplacement(sb, sqlTokens.get(matcher.group(1)));
            }
            matcher.appendTail(sb);
            return sb.toString();
        }

        public static final UnaryOperator<String> TEXT = input -> "'" + escape(input) + "'";
    }
}
