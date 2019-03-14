package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.*;

import java.util.List;
import java.util.Map;


public class From {

    private From() {}

    public static <T> MockUnit<T> from(List<T> alphabet) {
        return MockNeat.threadLocal().from(alphabet);
    }

    public static <T> MockUnit<T> from(T[] alphabet) {
        return MockNeat.threadLocal().from(alphabet);
    }

    public static <T extends Enum<?>> MockUnit<T> from(Class<T> enumClass) {
       return MockNeat.threadLocal().from(enumClass);
    }

    public static <T> MockUnit<T> fromKeys(Map<T, ?> map) {
       return MockNeat.threadLocal().fromKeys(map);
    }

    public static <T> MockUnit<T> fromValues(Map<?, T> map) {
       return MockNeat.threadLocal().fromValues(map);
    }

    public static MockUnitInt fromInts(Integer[] alphabet) {
        return MockNeat.threadLocal().fromInts(alphabet);
    }

    public static MockUnitInt fromInts(int[] alphabet) {
        return MockNeat.threadLocal().fromInts(alphabet);
    }

    public static MockUnitInt fromInts(List<Integer> alphabet) {
       return MockNeat.threadLocal().fromInts(alphabet);
    }

    public static MockUnitInt fromIntsValues(Map<?, Integer> map) {
        return MockNeat.threadLocal().fromIntsValues(map);
    }

    public static MockUnitInt fromIntsKeys(Map<Integer, ?> map) {
        return MockNeat.threadLocal().fromIntsKeys(map);
    }

    public static MockUnitDouble fromDoubles(Double[] alphabet) {
        return MockNeat.threadLocal().fromDoubles(alphabet);
    }

    public static MockUnitDouble fromDoubles(double[] alphabet) {
        return MockNeat.threadLocal().fromDoubles(alphabet);
    }

    public static MockUnitDouble fromDoubles(List<Double> alphabet) {
       return MockNeat.threadLocal().fromDoubles(alphabet);
    }

    public static MockUnitDouble fromDoublesValues(Map<?, Double> map) {
       return MockNeat.threadLocal().fromDoublesValues(map);
    }

    public static MockUnitDouble fromDoublesKeys(Map<Double, ?> map) {
       return MockNeat.threadLocal().fromDoublesKeys(map);
    }

    public static MockUnitLong fromLongs(Long[] alphabet) {
        return MockNeat.threadLocal().fromLongs(alphabet);
    }

    public static MockUnitLong fromLongs(long[] alphabet) {
        return MockNeat.threadLocal().fromLongs(alphabet);
    }

    public static MockUnitLong fromLongs(List<Long> alphabet) {
        return MockNeat.threadLocal().fromLongs(alphabet);
    }

    public static MockUnitLong fromLongsValues(Map<?, Long> map) {
        return MockNeat.threadLocal().fromLongsValues(map);
    }

    public static MockUnitLong fromLongsKeys(Map<Long, ?> map) {
        return MockNeat.threadLocal().fromLongsKeys(map);
    }

    public static MockUnitString fromStrings(String[] alphabet) {
        return MockNeat.threadLocal().fromStrings(alphabet);
    }

    public static MockUnitString fromStrings(List<String> alphabet) {
        return MockNeat.threadLocal().fromStrings(alphabet);
    }

    public static MockUnitString fromStringsValues(Map<?, String> map) {
        return MockNeat.threadLocal().fromStringsValues(map);
    }

    public static MockUnitString fromStringsKeys(Map<String, ?> map) {
        return MockNeat.threadLocal().fromStringsKeys(map);
    }

}
