package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.*;

import java.util.List;
import java.util.Map;


public class From {
    public static <T> MockUnit<T> from(List<T> alphabet) {
        return MockNeat.threadLocal().from(alphabet);
    }

    public <T> MockUnit<T> from(T[] alphabet) {
        return MockNeat.threadLocal().from(alphabet);
    }

    public <T extends Enum<?>> MockUnit<T> from(Class<T> enumClass) {
       return MockNeat.threadLocal().from(enumClass);
    }

    public <T> MockUnit<T> fromKeys(Map<T, ?> map) {
       return MockNeat.threadLocal().fromKeys(map);
    }

    public <T> MockUnit<T> fromValues(Map<?, T> map) {
       return MockNeat.threadLocal().fromValues(map);
    }

    public MockUnitInt fromInts(Integer[] alphabet) {
        return MockNeat.threadLocal().fromInts(alphabet);
    }

    public MockUnitInt fromInts(int[] alphabet) {
        return MockNeat.threadLocal().fromInts(alphabet);
    }

    public MockUnitInt fromInts(List<Integer> alphabet) {
       return MockNeat.threadLocal().fromInts(alphabet);
    }

    public MockUnitInt fromIntsValues(Map<?, Integer> map) {
        return MockNeat.threadLocal().fromIntsValues(map);
    }

    public MockUnitInt fromIntsKeys(Map<Integer, ?> map) {
        return MockNeat.threadLocal().fromIntsKeys(map);
    }

    public MockUnitDouble fromDoubles(Double[] alphabet) {
        return MockNeat.threadLocal().fromDoubles(alphabet);
    }

    public MockUnitDouble fromDoubles(double[] alphabet) {
        return MockNeat.threadLocal().fromDoubles(alphabet);
    }

    public MockUnitDouble fromDoubles(List<Double> alphabet) {
       return MockNeat.threadLocal().fromDoubles(alphabet);
    }

    public MockUnitDouble fromDoublesValues(Map<?, Double> map) {
       return MockNeat.threadLocal().fromDoublesValues(map);
    }

    public MockUnitDouble fromDoublesKeys(Map<Double, ?> map) {
       return MockNeat.threadLocal().fromDoublesKeys(map);
    }

    public MockUnitLong fromLongs(Long[] alphabet) {
        return MockNeat.threadLocal().fromLongs(alphabet);
    }

    public MockUnitLong fromLongs(long[] alphabet) {
        return MockNeat.threadLocal().fromLongs(alphabet);
    }

    public MockUnitLong fromLongs(List<Long> alphabet) {
        return MockNeat.threadLocal().fromLongs(alphabet);
    }

    public MockUnitLong fromLongsValues(Map<?, Long> map) {
        return MockNeat.threadLocal().fromLongsValues(map);
    }

    public MockUnitLong fromLongsKeys(Map<Long, ?> map) {
        return MockNeat.threadLocal().fromLongsKeys(map);
    }

    public MockUnitString fromStrings(String[] alphabet) {
        return MockNeat.threadLocal().fromStrings(alphabet);
    }

    public MockUnitString fromStrings(List<String> alphabet) {
        return MockNeat.threadLocal().fromStrings(alphabet);
    }

    public MockUnitString fromStringsValues(Map<?, String> map) {
        return MockNeat.threadLocal().fromStringsValues(map);
    }

    public MockUnitString fromStringsKeys(Map<String, ?> map) {
        return MockNeat.threadLocal().fromStringsKeys(map);
    }

}
