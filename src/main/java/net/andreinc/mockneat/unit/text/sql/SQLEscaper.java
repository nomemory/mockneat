package net.andreinc.mockneat.unit.text.sql;

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
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.andreinc.mockneat.utils.ValidationUtils.notEmpty;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class SQLEscaper {

    private final Map<String, String> replacers = new HashMap<>();
    private final Pattern escapePattern;

    public SQLEscaper(List<TextEscapeToken> tokenList) {
        // Building the escape pattern
        notNull(tokenList, "tokenList");
        final StringBuilder buff = new StringBuilder();
        tokenList.stream().forEach(token -> {
            replacers.put(token.getSearchToken(), token.getReplaceString());
            buff.append("|").append(token.getSearchRegex());
        });
        // Deletes the first "|" from the pattern
        buff.deleteCharAt(0);
        // Wraps the regex pattern inside "(" ")"
        buff.insert(0, "(");
        buff.append(")");
        this.escapePattern = Pattern.compile(buff.toString());
    }

    public String escape(String text) {
        notEmpty(text, "text");
        Matcher matcher = escapePattern.matcher(text);
        StringBuffer escapedBuff = new StringBuffer();
        escapedBuff.append("'");
        while(matcher.find()) {
            matcher.appendReplacement(escapedBuff, replacers.get(matcher.group(1)));
        }
        matcher.appendTail(escapedBuff);
        escapedBuff.append("'");
        return escapedBuff.toString();
    }

    public static class TextEscapeToken {

        private final String searchToken;
        private final String searchRegex;
        private final String replaceString;

        public TextEscapeToken(String searchToken, String searchRegex, String replaceString) {
            notEmpty(searchToken, "searchToken");
            notEmpty(searchRegex, "searchRegex");
            notEmpty(replaceString, "replaceString");
            this.searchToken = searchToken;
            this.searchRegex = searchRegex;
            this.replaceString = replaceString;
        }

        public String getSearchToken() {
            return searchToken;
        }

        public String getSearchRegex() {
            return searchRegex;
        }

        public String getReplaceString() {
            return replaceString;
        }
    }
}
