package net.andreinc.mockneat.unit.text.sql;


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
        tokenList.forEach(token -> {
            replacers.put(token.getSearchToken(), token.getReplaceString());
            buff.append('|').append(token.getSearchRegex());
        });
        // Deletes the first "|" from the pattern
        buff.deleteCharAt(0);
        // Wraps the regex pattern inside "(" ")"
        buff.insert(0, '(');
        buff.append(')');
        this.escapePattern = Pattern.compile(buff.toString());
    }

    public String escape(String text) {
        notEmpty(text, "text");
        Matcher matcher = escapePattern.matcher(text);
        StringBuffer escapedBuff = new StringBuffer();
        escapedBuff.append('\'');
        while(matcher.find()) {
            matcher.appendReplacement(escapedBuff, replacers.get(matcher.group(1)));
        }
        matcher.appendTail(escapedBuff);
        escapedBuff.append('\'');
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
