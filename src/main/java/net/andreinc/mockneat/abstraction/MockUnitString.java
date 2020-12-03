package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.types.enums.StringFormatType;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.function.Supplier;

import static java.net.URLEncoder.encode;
import static net.andreinc.aleph.AlephFormatter.str;
import static net.andreinc.mockneat.utils.MockUnitUtils.ifSupplierNotNullDo;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

@FunctionalInterface
public interface MockUnitString extends MockUnit<String> {

    /**
     * <p>Joins intermediary results by calling val() a number of times using the given separator</p>
     *
     * @param times The number of times the value is joined
     * @param separator The string used as a separator
     * @return A new {@code MockUnitstring}.
     */
    default MockUnitString accumulate(int times, String separator) {
        notNull(separator, "separator");
        isTrue(times>0, NUMBER_OF_TIMES_POSITIVE);
        Supplier<String> supplier = () -> {
            StringBuilder buff = new StringBuilder();
            int counter = times;
            while(counter-->0) {
                buff.append(val()).append(separator);
            }
            // Removed the last separator if needed
            if (!separator.isEmpty()) {
                int length = separator.length();
                buff.delete(buff.length() - length, buff.length());
            }
            return buff.toString();
        };
        return () -> supplier;
    }

    /**
     * <p>Formats the results obtained from the {@code MockUnit<String>} and creates a new {@code MockUnitString} that generates formatted results.</p>
     *
     * Currently there are 3 supported formatting types:
     *
     * <ul>
     *     <li>{@code StringFormatType.UPPER_CASE}</li>
     *     <li>{@code StringFormatType.LOWER_CASE}</li>
     *     <li>{@code StringFormatType.CAPITALIZED}</li>
     * </ul>
     *
     * @param formatType The format type that will be applied to the generated value.
     * @return A new {@code MockUnitString}
     */
    default MockUnitString format(StringFormatType formatType) {
        notNull(formatType, "formatType");
        return () -> ifSupplierNotNullDo(supplier(), formatType.getFormatter());
    }

    /**
     * <p>Creates a new {@code MockUnitString} by applying {@code String.substring(0, endIndex)} on the generated values.</p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * <p><em>Note:</em> The developer is responsible to supply correct indexes for the substring operation.</p>
     *
     * @param endIndex The end index.
     * @return A new {@code MockUnitString}
     */
    default MockUnitString sub(int endIndex) {
        return sub(0, endIndex);
    }

    /**
     * <p>Creates a new {@code MockUnitString} by applying {@code String.substring(beginIndex, endIndex)} on the generated values. </p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * <p><em>Note:</em> The developer is responsible to supply correct indexes for the substring operation.</p>
     *
     * @param beginIndex The begin index.
     * @param endIndex The end index.
     * @return A new {@code MockUnitString}
     */
    default MockUnitString sub(int beginIndex, int endIndex) {
        return () -> ifSupplierNotNullDo(supplier(), s -> s.substring(beginIndex, endIndex));
    }

    /**
     * <p>Creates a new {@code MockUnitString}</p> by appending a new String on the generated value.
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * @param str The String to append.
     * @return A new {@code MockUnitString}
     */
    default MockUnitString append(String str) {
        notNull(str, "str");
        return () -> ifSupplierNotNullDo(supplier(), s -> s.concat(str));
    }

    /**
     * <p>Creates a new {@code MockUnitString} by prepending a new String on the generated value.</p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * @param str The String to prepend.
     * @return A new {@code MockUnitString}
     */
    default MockUnitString prepend(String str) {
        notNull(str, "str");
        return () -> ifSupplierNotNullDo(supplier(), str::concat);
    }

    /**
     * <p>Creates a new {@code MockUnitString} by replacing all the occurrences of {@code oldChar} with {@code newChar}</p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * @param oldCHar The char to be replaced.
     * @param newChar The new char replacing {@code oldChar}.
     * @return A new {@code MockUnitString}
     */
    default MockUnitString replace(char oldCHar, char newChar) {
        return () -> ifSupplierNotNullDo(supplier(), s -> s.replace(oldCHar, newChar));
    }

    /**
     * <p>Creates a new {@code MockUnitString} by replacing all occurrences of {@code target} with {@code replacement}.</p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * @param target The substring to be replaced.
     * @param replacement The substring that replaces {@code target}.
     * @return A new {@code MockUnitString}
     */
    default MockUnitString replace(CharSequence target, CharSequence replacement) {
        notNull(target, "target");
        notNull(replacement, "replacement");
        return () -> ifSupplierNotNullDo(supplier(), s -> s.replace(target, replacement));
    }

    /**
     * <p>Creates a new {@code MockUnitString} by replacing all occurrences that match {@code regex} with {@code replacement}.</p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * @param regex The regular expression to be replaced.
     * @param replacement The replacement for the characters matching {@code regex}.
     * @return A new {@code MockUnitString}
     */
    default MockUnitString replaceAll(String regex, String replacement) {
        notNull(regex, "regex");
        notNull(replacement, "replacement");
        return () -> ifSupplierNotNullDo(supplier(), s -> s.replaceAll(regex,replacement));
    }

    /**
     * <p>Creates a new {@code MockUnitString} by replacing the first occurrence that match {@code regex} with {@code replacement}.</p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * @param regex The regular expression to be replaced.
     * @param replacement The replacement for the characters matching {@code regex}.
     * @return A new {@code MockUnitString}
     */
    default MockUnitString replaceFirst(String regex, String replacement) {
        notNull(regex, "regex");
        notNull(replacement, "replacement");
        return () -> ifSupplierNotNullDo(supplier(), s -> s.replaceFirst(regex, replacement));
    }

    /**
     * <p>Creates a new {@code MockUnit<String[]>} by splitting the generated value.</p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * @param regex The seperator regex (where the splittings are performed).
     * @param limit Limits the number of split operations.
     * @return A new {@code MockUnit<String>}
     */
    default MockUnit<String[]> split(String regex, int limit) {
        notNull(regex, "regex");
        return () -> ifSupplierNotNullDo(supplier(), s -> s.split(regex, limit));
    }


    /**
     * <p>Creates a new {@code MockUnit<String[]>} by splitting the generated value.</p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * @param regex The seperator regex (where the splittings are performed).
     * @return A new {@code MockUnit<String>}
     */
    default MockUnit<String[]> split(String regex) {
        return split(regex, 0);
    }

    /**
     * <p>Creates a new {@code MockUnitString} by applying an URL Encode on the result.</p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * @param encoding Type of encoding.
     * @return A new {@code MockUnitString}
     */
    default MockUnitString urlEncode(String encoding) {
        notNull(encoding, "encoding");
        return () -> ifSupplierNotNullDo(supplier(), s -> {
            try { return encode(s, encoding); }
            catch (UnsupportedEncodingException e) {
                String msg = str(CANNOT_URL_ENCODE_UTF_8).args("val", s, "encoding", encoding   ).fmt();
                throw new IllegalArgumentException(msg, e);
            }
        });
    }

    /**
     * <p>Creates a new {@code MockUnitString} by applying an URL Encode on the result.</p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * @return A new {@code MockUnitString}
     */
    default MockUnitString urlEncode() {
        return urlEncode("UTF-8");
    }

    /**
     * <p>Creates a new {@code MockUnitString} by stripping the result from special characters. Everything that is not " ", number or letter will be removed from the resulting string.</p>
     *
     * <p><em>Note:</em> If the initial generated value is {@code null} the resulting value is also {@code null}.</p>
     *
     * @return A new {@code MockUnitString}
     */
    default MockUnitString noSpecialChars() {
        return () -> ifSupplierNotNullDo(supplier(), s -> s.replaceAll("[^\\dA-Za-z ]", ""));
    }

    /**
     * <p>Creates a new {@code MockUnitString} by CSV-escaping the generated value. </p>
     *
     * @return A new {@code MockUnitString}
     */
    default MockUnitString escapeCsv() {
        return () -> ifSupplierNotNullDo(supplier(), StringEscapeUtils::escapeCsv);
    }

    /**
     * <p>Creates a new {@code MockUnitString} by ECMA-escaping the generated value. </p>
     *
     * @return A new {@code MockUnitString}
     */
    default MockUnitString escapeEcmaScript() {
        return () -> ifSupplierNotNullDo(supplier(), StringEscapeUtils::escapeEcmaScript);
    }

    /**
     * <p>Creates a new {@code MockUnitString} by HTML-escaping the generated value. </p>
     *
     * @return A new {@code MockUnitString}
     */
    default MockUnitString escapeHtml() {
        return () -> ifSupplierNotNullDo(supplier(), StringEscapeUtils::escapeHtml4);
    }

    /**
     * <p>Creates a new {@code MockUnitString} by XML-escaping the generated value.</p>
     *
     * @return A new {@code MockUnitString}
     */
    default MockUnitString escapeXml() {
        return () -> ifSupplierNotNullDo(supplier(), StringEscapeUtils::escapeXml11);
    }

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
        return () -> ifSupplierNotNullDo(supplier(), str -> new Base64().encodeAsString(str.getBytes(Charset.defaultCharset())));
    }

    /**
     * <p>Transforms an existing {@code MockUnitString} into a {@code MockUnit<String[]>}.</p>
     *
     * <p>The array will contains values generated through the {@code MockUnitString}.</p>
     *
     * @param size The size of the array.
     * @return A new {@code MockUnit<String[]>}
     */
    default MockUnit<String[]> array(int size) {
        return array(String.class, size);
    }
}
