package com.mockneat.mock.utils;

/*
 * Copyright 2017, Andrei N. Ciobanu

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

import java.util.Map;

import static com.mockneat.mock.utils.NamedFormatUtils.State.*;
import static com.mockneat.mock.utils.ValidationUtils.CANNOT_FIND_PARAM_IN_ARGS;
import static com.mockneat.mock.utils.ValidationUtils.INVALID_PARAM_NAME_LENGTH_0;

public class NamedFormatUtils {

    protected enum State { PARAM, PARAM_START, PARAM_END, FREE_TEXT }

    // fmt = "${param1}${param2}${param3}"
    public static String format(String fmt, Map<String, String> args) {
        int i = 0;

        State state = FREE_TEXT;
        StringBuilder result =  new StringBuilder(fmt.length());
        StringBuilder param = new StringBuilder(16);

        while(i < fmt.length()) {
            char chr = fmt.charAt(i);
            state = nextState(state, fmt, i);
            switch (state) {
                case FREE_TEXT :
                    result.append(chr);
                    break;
                case PARAM_START:
                    i++;
                    break;
                case PARAM:
                    param.append(chr);
                    break;
                case PARAM_END:
                    appendParamValue(args, param, result);
                    break;
            }
            i++;
        }

        return result.toString();
    }

    private static void appendParamValue(Map<String, String> args, StringBuilder param, StringBuilder result) {
        String paramStr = param.toString();
        validateParamLength(paramStr);
        validateParamExistence(args, paramStr);
        result.append(args.get(paramStr));
        param.setLength(0);
    }

    private static void validateParamLength(String paramStr) {
        if (0 == paramStr.length()) {
            throw new IllegalArgumentException(INVALID_PARAM_NAME_LENGTH_0);
        }
    }

    private static void validateParamExistence(Map<String, String> args, String paramStr) {
        if (!args.containsKey(paramStr)) {
            String fmt = String.format(CANNOT_FIND_PARAM_IN_ARGS, paramStr);
            throw new IllegalArgumentException(fmt);
        }
    }

    private static State nextState(State currentState, String fmt, int idx) {
        switch (currentState) {
            case FREE_TEXT :
                return  isParamStart(fmt, idx) ? PARAM_START : FREE_TEXT;
            case PARAM_START:
                return PARAM;
            case PARAM :
                return isParamEnd(fmt, idx) ? PARAM_END : PARAM;
            case PARAM_END:
                return isParamStart(fmt, idx) ? PARAM_START : FREE_TEXT;
        }
        throw new IllegalArgumentException("Invalid state Exception");
    }

    private static boolean isParamStart(String fmt, int idx) {
        return ('#' == fmt.charAt(idx)) && (idx+1< fmt.length() && ('{' == fmt.charAt(idx+1)));
    }

    private static boolean isParamEnd(String fmt, int idx) {
        return '}' == fmt.charAt(idx);
    }

}
