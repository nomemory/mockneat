package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.*;
import net.andreinc.mockneat.utils.ValidationUtils;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import static org.apache.commons.lang3.Validate.notEmpty;

public class Froms extends MockUnitBase {

    public Froms(MockNeat mockNeat) {
        super(mockNeat);
    }

    protected Random getRandom() {
        return mockNeat.getRandom();
    }

    public <T> MockUnit<T> from(List<T> alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<T> supp = () -> {
            int idx = getRandom().nextInt(alphabet.size());
            return alphabet.get(idx);
        };
        return () -> supp;
    }

    public <T> MockUnit<T> from(T[] alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<T> supp = () -> {
            int idx = getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }

    public <T extends Enum<?>> MockUnit<T> from(Class<T> enumClass) {
        ValidationUtils.notNull(enumClass, "enumClass");
        T[] arr = enumClass.getEnumConstants();
        return from(arr);
    }

    @SuppressWarnings("unchecked")
    public <T> MockUnit<T> fromKeys(Map<T, ?> map) {
        notEmpty(map, ValidationUtils.INPUT_PARAMETER_NOT_EMPTY_OR_NULL, "map");
        Supplier<T> supp = () -> {
            T[] keys = (T[]) map.keySet().toArray();
            int idx = getRandom().nextInt(keys.length);
            return keys[idx];
        };
        return () -> supp;
    }

    public <T> MockUnit<T> fromValues(Map<?, T> map) {
        notEmpty(map, ValidationUtils.INPUT_PARAMETER_NOT_EMPTY_OR_NULL, "map");
        Supplier<T> supp = () -> {
            @SuppressWarnings("unchecked") T[] values = (T[]) map.values().toArray();
            int idx = getRandom().nextInt(values.length);
            return values[idx];
        };
        return () -> supp;
    }

    public MockUnitInt fromInts(Integer[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitInt fromInts(int[] alphabet) {
        return () -> mockNeat.ints().from(alphabet)::val;
    }

    public MockUnitInt fromInts(List<Integer> alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitInt fromIntsValues(Map<?, Integer> map) {
        return () -> fromValues(map)::val;
    }

    public MockUnitInt fromIntsKeys(Map<Integer, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public MockUnitDouble fromDoubles(Double[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitDouble fromDoubles(double[] alphabet) {
        return () -> mockNeat.doubles().from(alphabet)::val;
    }

    public MockUnitDouble fromDoubles(List<Double> alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitDouble fromDoublesValues(Map<?, Double> map) {
        return () -> fromValues(map)::val;
    }

    public MockUnitDouble fromDoublesKeys(Map<Double, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public MockUnitLong fromLongs(Long[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitLong fromLongs(long[] alphabet) {
        return () -> mockNeat.longs().from(alphabet)::val;
    }

    public MockUnitLong fromLongs(List<Long> alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitLong fromLongsValues(Map<?, Long> map) {
        return () -> fromValues(map)::val;
    }

    public MockUnitLong fromLongsKeys(Map<Long, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public MockUnitString fromStrings(String[] alphabet) {
        return () ->  () -> from(alphabet).val();
    }

    public MockUnitString fromStrings(List<String> alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitString fromStringsValues(Map<?, String> map) {
        return () -> fromValues(map)::val;
    }

    public MockUnitString fromStringsKeys(Map<String, ?> map) {
        return () -> fromKeys(map)::val;
    }

}
