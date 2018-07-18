package net.andreinc.mockneat.utils;

import java.util.HashMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlEscapeUtils {

    public static final class MySQL {

        private static final HashMap<String,String> sqlTokens;
        private static Pattern sqlTokenPattern;

        static
        {
            //MySQLText escape sequences: http://dev.mysql.com/doc/refman/5.1/en/string-syntax.html
            String[][] search_regex_replacement = new String[][]
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
            String patternStr = "";
            for (String[] srr : search_regex_replacement)
            {
                sqlTokens.put(srr[0], srr[2]);
                patternStr += (patternStr.isEmpty() ? "" : "|") + srr[1];
            }
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

        public static Function<String, String> TEXT = (input) -> "'" + escape(input) + "'";
    }
}
