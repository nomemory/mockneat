package com.mockneat.random.unit.interfaces;

import com.mockneat.types.enums.StringFormatType;

import java.util.function.Supplier;

import static com.mockneat.utils.CheckUtils.checkStringFormatTypeNotNull;

/**
 * Created by andreinicolinciobanu on 07/02/2017.
 */
@FunctionalInterface
public interface RandUnitString extends RandUnit<String> {

    default RandUnitString format(StringFormatType formatType) {
        checkStringFormatTypeNotNull(formatType);
        Supplier<String> supplier = () -> formatType.getFormatter().apply(supplier().get());
        return () -> supplier;
    }

    default RandUnitString sub(int endIndex) {
        return sub(0, endIndex);
    }

    default RandUnitString sub(int beginIndex, int endIndex) {
        Supplier<String> supplier = () -> supplier().get().substring(beginIndex, endIndex);
        return () -> supplier;
    }

    default RandUnitString concat(String str) {
        // TODO validate String
        Supplier<String> supplier = () -> supplier().get().concat(str);
        return () -> supplier;
    }

    default RandUnitString replace(char oldCHar, char newChar) {
        Supplier<String> supplier = () -> supplier().get().replace(oldCHar, newChar);
        return () -> supplier;
    }

    default RandUnitString replace(CharSequence target, CharSequence replacement) {
        Supplier<String> supplier = () -> supplier().get().replace(target, replacement);
        return () -> supplier;
    }

    default RandUnitString replaceAll(String regex, String replacement) {
        Supplier<String > supplier = () -> supplier().get().replaceAll(regex, replacement);
        return () -> supplier;
    }

    default RandUnitString replaceFirst(String regex, String replacement) {
        Supplier<String> supplier = () -> supplier().get().replaceFirst(regex, replacement);
        return () -> supplier;
    }

    default RandUnit<String[]> split(String regex, int limit) {
        Supplier<String[]> supplier = () -> supplier().get().split(regex, limit);
        return () -> supplier;
    }

    default RandUnit<String[]> split(String regex) {
        return split(regex, 0);
    }
}
