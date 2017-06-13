package net.andreinc.mockneat.abstraction;

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

import net.andreinc.mockneat.types.enums.StringFormatType;
import net.andreinc.mockneat.utils.ValidationUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import static java.net.URLEncoder.encode;
import static net.andreinc.aleph.AlephFormatter.template;
import static net.andreinc.mockneat.utils.MockUnitUtils.ifSupplierNotNullDo;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

@FunctionalInterface
public interface MockUnitString extends MockUnit<String> {

    default MockUnitString format(StringFormatType formatType) {
        notNull(formatType, "formatType");
        return () -> ifSupplierNotNullDo(supplier(), formatType.getFormatter()::apply);
    }

    default MockUnitString sub(int endIndex) {
        return sub(0, endIndex);
    }

    default MockUnitString sub(int beginIndex, int endIndex) {
        return () -> ifSupplierNotNullDo(supplier(), s -> s.substring(beginIndex, endIndex));
    }

    default MockUnitString append(String str) {
        notNull(str, "str");
        return () -> ifSupplierNotNullDo(supplier(), s -> s.concat(str));
    }

    default MockUnitString prepend(String str) {
        notNull(str, "str");
        return () -> ifSupplierNotNullDo(supplier(), str::concat);
    }

    default MockUnitString replace(char oldCHar, char newChar) {
        return () -> ifSupplierNotNullDo(supplier(), s -> s.replace(oldCHar, newChar));
    }

    default MockUnitString replace(CharSequence target, CharSequence replacement) {
        notNull(target, "target");
        notNull(replacement, "replacement");
        return () -> ifSupplierNotNullDo(supplier(), s -> s.replace(target, replacement));
    }

    default MockUnitString replaceAll(String regex, String replacement) {
        notNull(regex, "regex");
        notNull(replacement, "replacement");
        return () -> ifSupplierNotNullDo(supplier(), s -> s.replaceAll(regex,replacement));
    }

    default MockUnitString replaceFirst(String regex, String replacement) {
        notNull(regex, "regex");
        notNull(replacement, "replacement");
        return () -> ifSupplierNotNullDo(supplier(), s -> s.replaceFirst(regex, replacement));
    }

    default MockUnit<String[]> split(String regex, int limit) {
        notNull(regex, "regex");
        return () -> ifSupplierNotNullDo(supplier(), s -> s.split(regex, limit));
    }

    default MockUnit<String[]> split(String regex) {
        return split(regex, 0);
    }

    default MockUnitString urlEncode(String encoding) {
        notNull(encoding, "encoding");
        return () -> ifSupplierNotNullDo(supplier(), s -> {
            try { return encode(s, encoding); }
            catch (UnsupportedEncodingException e) {
                String msg = template(ValidationUtils.CANNOT_URL_ENCODE_UTF_8, "val", s).fmt();
                throw new IllegalArgumentException(msg, e);
            }
        });
    }

    default MockUnitString urlEncode() {
        return urlEncode("UTF-8");
    }

    default MockUnitString noSpecialChars() {
        return () -> ifSupplierNotNullDo(supplier(), s -> s.replaceAll("[^\\dA-Za-z ]", ""));
    }

    default MockUnitString escapeCsv() {
        return () -> ifSupplierNotNullDo(supplier(), StringEscapeUtils::escapeCsv);
    }

    default MockUnitString escapeEcmaScript() {
        return () -> ifSupplierNotNullDo(supplier(), StringEscapeUtils::escapeEcmaScript);
    }

    default MockUnitString escapeHtml() {
        return () -> ifSupplierNotNullDo(supplier(), StringEscapeUtils::escapeHtml4);
    }

    default MockUnitString escapeXml() {
        return () -> ifSupplierNotNullDo(supplier(), StringEscapeUtils::escapeXml11);
    }

    // TODO document methods

    default MockUnitString md2() {
        return () -> ifSupplierNotNullDo(supplier(), DigestUtils::md2Hex);
    }

    default MockUnitString md5() {
        return () -> ifSupplierNotNullDo(supplier(), DigestUtils::md5Hex);
    }

    default MockUnitString sha1() {
        return () -> ifSupplierNotNullDo(supplier(), DigestUtils::sha1Hex);
    }

    default MockUnitString sha256() {
        return () -> ifSupplierNotNullDo(supplier(), DigestUtils::sha256Hex);
    }

    default MockUnitString sha384() {
        return () -> ifSupplierNotNullDo(supplier(), DigestUtils::sha384Hex);
    }

    default MockUnitString sha512() {
        return () -> ifSupplierNotNullDo(supplier(), DigestUtils::sha512Hex);
    }

    default MockUnitString base64() {
        return () -> ifSupplierNotNullDo(supplier(), (str) -> new Base64().encodeAsString(str.getBytes(Charset.defaultCharset())));
    }

    default MockUnit<String[]> array(int size) {
        return array(String.class, size);
    }
}
