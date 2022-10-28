package net.andreinc.mockneat.utils;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static java.util.stream.IntStream.range;
import static net.andreinc.aleph.AlephFormatter.str;

/**
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

public final class ValidationUtils {

    public static final String INPUT_PARAMETER_NOT_NULL = "Input parameter: '#{input}' should not be NULL.";
    public static final String SUPPLIER_VALUE_NOT_NULL = "Value retrieved by Supplier<?> '#{supplier}' should not be NULL";
    public static final String INPUT_PARAMETER_NOT_EMPTY_OR_NULL = "Input parameter: '#{input}' should not be empty or NULL.";
    public static final String LOWER_BOUND_BIGGER_THAN_ZERO = "The input parameter 'lowerBound' should be >= 0.0.";
    public static final String LOWER_BOUND_DIFFERENT_THAN_INTEGER_MAX = "The 'lowerB    ound' should be different than Integer.MAX_VALUE.";
    public static final String LOWER_BOUND_DIFFERENT_THAN_LONG_MAX = "The 'lowerBound' should be different than Long.MAX_VALUE.";
    public static final String UPPER_BOUND_BIGGER_THAN_ZERO = "The input parameter 'upperBound' should be > 0.0.";
    public static final String UPPER_BOUND_BIGGER_LOWER_BOUND = "The input parameter 'upperBound' > 'lowerBound'.";
    public static final String UPPER_MONTH_BIGGER_THAN_LOWER = "'lower' Month < 'upper' Month";
    public static final String IS_FINITE_NUMBER = "Number #{number} should be finite (non-infinite, non-nan).";
    public static final String SIZE_BIGGER_THAN_ZERO = "The size needs to be bigger than 0 (>=).";
    public static final String IN_RANGE_CLOSED = "Number: #{num} should be in [#{min}, #{max}] range.";
    public static final String CANNOT_ADD_VALUE_TO_COLLECTION = "Cannot add value '#{val}' to collection '#{cls.simpleName}'";
    public static final String CANNOT_ADD_VALUE_TO_LIST = "Cannot add value '#{value}' to '#{cls.simpleName}'.";
    public static final String CANNOT_ADD_VALUE_TO_SET = "Cannot add value '#{value}' to '#{cls.simpleName}'.";
    public static final String CANNOT_PUT_VALUES_TO_MAP = "Cannot put values '{#{key}:#{val}}' to '#{cls.simpleName}'.";
    // Time
    public static final String BEFORE_DAY_DIFFERENT_THAN_MONDAY = "Cannot use 'Monday' as 'before'. 'Monday' is considered to be the first day of the week.";
    public static final String AFTER_DAY_DIFFERENT_THAN_SUNDAY = "Cannot use 'Sunday' as 'after'. 'Sunday' is considered to be the last day of the week.";
    public static final String BEFORE_MONTH_DIFFERENT_THAN_JANUARY = "Cannot use 'January' as 'before'. 'January' is considered to be the first month of the year.";
    public static final String AFTER_MONTH_DIFFERENT_TNAN_DECEMBER = "Cannot use 'December' as 'after'. 'December' is considered to be the last month of the year.";
    public static final String CANNOT_URL_ENCODE_UTF_8 = "Cannot URL encode the following string: '#{val}'. Encoding: '#{encoding}' looks invalid.";
    public static final String LOWER_DATE_SMALLER_THAN_UPPER_DATE = "lowerDate '#{lower}' should be < than upperDate '#{upper}'.";
    public static final String MAX_DATE_NOT_BIGGER_THAN = "maxDate '#{max}'  should be < than '#{date}' (LocalDates.MAX)";
    public static final String MIN_DATE_BIGGER_THAN = "minDate '#{min}' should be > than '#{date}' (LocalDate.MIN)";
    public static final String MAX_DATE_DIFFERENT_THAN_NOW = "maxDate '#{max}' should be > than now() (#{now}).";
    public static final String MIN_DATE_DIFFERENT_THAN_NOW = "minDate '#{min}' should be < than now() (#{now}).";
    // MOCK
    public static final String CANNOT_INSTANTIATE_OBJECT_OF_CLASS = "Cannot create an instance of '#{cls.name}'. Please verify if the class has a public 'No Arguments' constructor: #{cls.name}.";
    public static final String CANNOT_SET_FIELD_WITH_VALUE = "Cannot set field (or static field) #{cls}.#{field} with value '#{val}'. Is the supplied value correct ?";
    public static final String CANNOT_INFER_CONSTRUCTOR = "Cannot infer #{c.name}.#{params}. Are you sure the constructor exists and it's public ?";
    public static final String CANNOT_INVOKE_STATIC_FACTORY_METHOD = "Cannot invoke '#{cls.class.name}.#{method}()' with params: '#{types}'.";
    public static final String JAVA_FIELD_REGEX_MATCH = "Field '#{field}' doesn't match the Java Naming Conventions for fields.";
    public static final String JAVA_METHOD_REGEX_MATCH = "Method '#{method}' doesn't match the Java Naming Conventions for methods.";
    public static final String JAVA_FIELD_IS_FINAL = "Field '#{field}' is marked as FINAL. It cannot be modified. Please remove it from the fields list.";
    public static final String JAVA_FIELD_DOESNT_EXIST_ON_CLASS = "Cannot access field: '#{field}'.";
    public static final String INPUT_PARAM_ALPHANUMERIC = "Input param '#{input}' should be alpha-numeric.";
    public static final String INT_SEQ_OVERFLOW = "IntSeq overflow. Values are generated inside the interval: [#{min}, #{max}]. Cannot increment any further.";
    public static final String LONG_SEQ_OVERFLOW = "LongSeq overflow. Values are generated inside the interval: [#{min}, #{max}]. Cannot increment any further.";
    public static final String SEQ_INVALID_RANGE = "The min value '#{min}' should be lower than the maximum '#{max}' value of the sequence.";
    public static final String PROBABILITIES_SUM_BIGGER = "Probabilities sum cannot be bigger (>) than '1.0'.";
    public static final String PROBABILITIES_SUM_NOT_1 = "Probabilities sum must be exactly (=) 1.0 when generating values.";
    public static final String INVALID_REGEX_PATTERN = "Invalid regex pattern ('#{pattern}'): ";
    public static final String OBJECT_NOT_SERIALIZABLE = "Object is not serializable. Does the add's class extends 'java.io.Serializable' ?";
    public static final String PROBABILITY_NOT_NEGATIVE = "Probability '#{prob}' should be bigger than '0.0'.";
    public static final String IMPOSSIBLE_TO_SEQ_OVER_EMPTY_COLLECTION = "Impossible to create a Seq from an empty Iterable<T>.";
    public static final String NUMBER_OF_TIMES_POSITIVE = "The number of times should be a positive number.";
    public static final String NUMBER_OF_LINES_POSITIVE = "The number of lines needs to be a positive number.";
    public static final String ROW_POSITIVE_VALUE = "The row should be a positive value. (>=0)";
    public static final String EMPTY_CSV_NO_COLUMNS = "Cannot generate CSV without any columns.";
    // SQL
    public static final String COLUMN_DOESNT_EXISTS = "Column: #{column} cannot be found on #{table}.";

    private ValidationUtils() {}

    public static void isTrue(boolean expr, String fmt, Object... args) {
        if (!expr)
            throw new IllegalArgumentException(str(fmt).args(args).fmt());
    }

    public static <T> void notNull(T object, final String message, Object... args) {
        if (object == null)
            throw new NullPointerException(str(message).args(args).fmt());
    }

    public static <T> void notNull(T object, String input) {
        notNull(object, INPUT_PARAMETER_NOT_NULL, "input", input);
    }

    public static <T> void notNullSupp(T object, String input) {
        notNull(object, SUPPLIER_VALUE_NOT_NULL, "supplier", input);
    }

    public static <T extends CharSequence> T notEmpty(T chars, String message, Object... values) {
        if (chars == null) {
            throw new NullPointerException(str(message).args(values).fmt());
        }
        if (chars.length() == 0) {
            throw new IllegalArgumentException(str(message).args(values).fmt());
        }
        return chars;
    }

    public static <T extends CharSequence> void notEmpty(T chars, String input) {

        String msg = str(INPUT_PARAMETER_NOT_EMPTY_OR_NULL)
                        .args("input", input)
                        .fmt();

        if (chars == null) {
            throw new NullPointerException(msg);
        }

        if (chars.length() == 0) {
            throw new IllegalArgumentException(msg);
        }

    }

    public static <T> void notEmptyOrNullValues(T[] arr, String arrName) {
        notEmpty(arr, arrName);
        range(0, arr.length).forEach(i  -> notNull(arr[i], arrName + "[" + i + "]"));
    }

    public static void notEmptyOrNullValues(String[] arr, String arrName) {
        notEmpty(arr, arrName);
        range(0, arr.length).forEach(i  -> notEmpty(arr[i], arrName + "[" + i + "]"));
    }

    public static <T> void notEmpty(T[] arr, String name) {
        if (null == arr)
            throw new NullPointerException(str(INPUT_PARAMETER_NOT_EMPTY_OR_NULL).args("input", name).fmt());
        if (0 == arr.length) {
            throw new IllegalArgumentException(str(INPUT_PARAMETER_NOT_EMPTY_OR_NULL).args("input", name).fmt());
        }
    }

    public static void notEmpty(char[] array, String fmt, Object... params) {
        if (null==array)
            throw new NullPointerException(String.format(fmt, params));
        if (0==array.length)
            throw new IllegalArgumentException(String.format(fmt, params));
    }

    public static void notEmpty(double[] array, String fmt, Object... params) {
        if (null==array)
            throw new NullPointerException(String.format(fmt, params));
        if (0==array.length)
            throw new IllegalArgumentException(String.format(fmt, params));
    }

    public static void notEmpty(float[] array, String fmt, Object... params) {
        if (null==array)
            throw new NullPointerException(String.format(fmt, params));
        if (0==array.length)
            throw new IllegalArgumentException(String.format(fmt, params));
    }
    public static void notEmpty(int[] array, String fmt, Object... params) {
        if (null==array)
            throw new NullPointerException(String.format(fmt, params));
        if (0==array.length)
            throw new IllegalArgumentException(String.format(fmt, params));
    }

    public static void notEmpty(long[] array, String fmt, Object... params) {
        if (null==array)
            throw new NullPointerException(String.format(fmt, params));
        if (0==array.length)
            throw new IllegalArgumentException(String.format(fmt, params));
    }

    public static void validRegex(String regex) {
        try {
            Pattern.compile(regex);
        } catch (PatternSyntaxException pse) {
            String fmt = str(INVALID_REGEX_PATTERN).arg("pattern", regex).fmt();
            throw new IllegalArgumentException(fmt, pse);
        }
    }

    public static void isFinite(Double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            String msg = str(IS_FINITE_NUMBER).args("number", value).fmt();
            throw new IllegalArgumentException(msg);
        }
    }

    public static void betweenClosed(Double value, Double start, Double end, String message, final Object... args) {
        if (!(start <=  value && end >= value)) {
            String msg = str(message).args(args).fmt();
            throw new IllegalArgumentException(msg);
        }
    }

    public static void betweenClosed(Double value, Double start, Double end) {
        betweenClosed(value, start, end, IN_RANGE_CLOSED,
                "num", value,
                "min", start,
                "max", end);
    }

}
