package com.mockneat.mock.interfaces;

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

import com.mockneat.mock.utils.ValidationUtils;
import com.mockneat.types.enums.StringFormatType;

import java.io.UnsupportedEncodingException;
import java.util.function.Supplier;

import static java.net.URLEncoder.encode;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

@FunctionalInterface
public interface MockUnitString extends MockUnit<String> {

    default MockUnitString format(StringFormatType formatType) {
        notNull(formatType, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "formatType");
        Supplier<String> supplier = () -> formatType.getFormatter().apply(supplier().get());
        return () -> supplier;
    }

    default MockUnitString sub(int endIndex) {
        return sub(0, endIndex);
    }

    default MockUnitString sub(int beginIndex, int endIndex) {
        Supplier<String> supplier = () -> supplier().get().substring(beginIndex, endIndex);
        return () -> supplier;
    }

    default MockUnitString append(String str) {
        notEmpty(str);
        Supplier<String> supplier = () -> supplier().get().concat(str);
        return () -> supplier;
    }

    default MockUnitString prepend(String str) {
        notEmpty(str);
        Supplier<String> supplier = () -> str.concat(supplier().get());
        return () -> supplier;
    }

    default MockUnitString replace(char oldCHar, char newChar) {
        Supplier<String> supplier = () -> supplier().get().replace(oldCHar, newChar);
        return () -> supplier;
    }

    default MockUnitString replace(CharSequence target, CharSequence replacement) {
        Supplier<String> supplier = () -> supplier().get().replace(target, replacement);
        return () -> supplier;
    }

    default MockUnitString replaceAll(String regex, String replacement) {
        Supplier<String > supplier = () -> supplier().get().replaceAll(regex, replacement);
        return () -> supplier;
    }

    default MockUnitString replaceFirst(String regex, String replacement) {
        Supplier<String> supplier = () -> supplier().get().replaceFirst(regex, replacement);
        return () -> supplier;
    }

    default MockUnit<String[]> split(String regex, int limit) {
        Supplier<String[]> supplier = () -> supplier().get().split(regex, limit);
        return () -> supplier;
    }

    default MockUnit<String[]> split(String regex) {
        return split(regex, 0);
    }

    default MockUnitString urlEncode(String enc) {
        Supplier<String> supplier = () -> {
            String val = supplier().get();
            try {
                return encode(val, enc);
            } catch (UnsupportedEncodingException e) {
                String msg = String.format(ValidationUtils.CANNOT_URL_ENCODE_UTF_8, val);
                throw new IllegalArgumentException(msg, e);
            }
        };
        return () -> supplier;
    }

    default MockUnitString urlEncode() {
        return urlEncode("UTF-8");
    }

    default MockUnitString noSpecialChars() {
        return () -> () -> supplier().get().replaceAll("[^\\dA-Za-z ]", "");
    }
}
