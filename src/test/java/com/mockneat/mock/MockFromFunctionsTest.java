package com.mockneat.mock;

import com.mockneat.mock.interfaces.MockUnitDouble;
import com.mockneat.mock.interfaces.MockUnitInt;
import com.mockneat.mock.interfaces.MockUnitLong;
import com.mockneat.mock.interfaces.MockUnitString;
import org.junit.Test;

import java.util.*;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.junit.Assert.assertTrue;

public class MockFromFunctionsTest {

    private static class TestModel {
        private String x1;
        private Integer y1;

        public TestModel(String x1, Integer y1) {
            this.x1 = x1;
            this.y1 = y1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestModel testModel = (TestModel) o;

            if (x1 != null ? !x1.equals(testModel.x1) : testModel.x1 != null) return false;
            return y1 != null ? y1.equals(testModel.y1) : testModel.y1 == null;

        }

        @Override
        public int hashCode() {
            int result = x1 != null ? x1.hashCode() : 0;
            result = 31 * result + (y1 != null ? y1.hashCode() : 0);
            return result;
        }

        public static TestModel[] getTestArray() {
            return new TestModel[]{
                    new TestModel("a", 1),
                    new TestModel("b", 2),
                    new TestModel("c", 3),
                    new TestModel("d", 4),
                    new TestModel("xa", 100),
                    new TestModel("ha", 200),
                    new TestModel("bla", 300),
                    new TestModel("Hohoho", 25)
            };
        }

        public static List<TestModel> getTestList() {
            return Arrays.asList(getTestArray());
        }
    }

    private static enum TestEnum { TEST1, TEST2, TEST3 };

    private static enum TestEnumEmpty {};

    @Test
    public void testFromArray() throws Exception {
        TestModel[] array = TestModel.getTestArray();
        Set<TestModel> possibleValues = new HashSet<>(asList(array));
        loop(OBJS_CYCLES,
                RANDS,
                r -> r.from(array).val(),
                tm -> assertTrue(possibleValues.contains(tm)));
    }

    @Test
    public void testFromArraySingleElement() throws Exception {
        String[] array = { "a" };
        loop(OBJS_CYCLES,
                RANDS,
                r -> r.from(array).val(),
                s -> assertTrue(s.equals(array[0])));
    }

    @Test(expected = NullPointerException.class)
    public void testFromNullArray() throws Exception {
        TestModel[] array = null;
        RAND.from(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromEmptyArray() throws Exception {
        TestModel[] array = new TestModel[]{};
        RAND.from(array).val();
    }

    @Test
    public void testFromList() throws Exception {
        List<TestModel> list = TestModel.getTestList();
        Set<TestModel> possibleValues = new HashSet<>(list);
        loop(OBJS_CYCLES,
                RANDS,
                r -> r.from(list).val(),
                tm -> assertTrue(possibleValues.contains(tm)));
    }

    @Test
    public void testFrom1ElementList() throws Exception {
        List<String> list = Arrays.asList(new String[]{ "a" });
        loop(OBJS_CYCLES,
                RANDS,
                r -> r.from(list).val(),
                s -> assertTrue(s.equals(list.get(0))));
    }

    @Test(expected = NullPointerException.class)
    public void testFromNullList() throws Exception {
        List<TestModel> array = null;
        RAND.from(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromEmptyList() throws Exception {
        List<TestModel> list = new ArrayList<>();
        RAND.from(list).val();
    }

    @Test
    public void testEnum() throws Exception {
        Set<TestEnum> possible = new HashSet<>(asList(TestEnum.values()));
        loop(OBJS_CYCLES,
                RANDS,
                r -> r.from(TestEnum.class).val(),
                tm -> assertTrue(possible.contains(tm)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnumEmpty() throws Exception {
        RAND.from(TestEnumEmpty.class).val();
    }

    @Test(expected = NullPointerException.class)
    public void testEnumNull() throws Exception {
        Class<? extends Enum> cls = null;
        RAND.from(cls).val();
    }

    @Test
    public void testFromKeys() throws Exception {
        Map<Integer, Integer> map = RAND.ints().mapKeys(25, RAND.ints()::val).val();
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromKeys(map).val(),
                x -> map.containsKey(x));
    }

    @Test(expected = NullPointerException.class)
    public void testFromKeysNullMap() throws Exception {
        Map<Integer, Integer> intMap = null;
        RAND.fromKeys(intMap).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromKeysEmptyMap() throws Exception {
        Map<Integer, Integer> intMap = new HashMap<>();
        RAND.fromKeys(intMap).val();
    }

    @Test
    public void testFromValues() throws Exception {
        Map<Integer, Integer> map = RAND.ints().mapKeys(25, RAND.ints()::val).val();
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromValues(map).val(),
                x -> assertTrue(map.containsValue(x)));
    }

    @Test(expected = NullPointerException.class)
    public void testFromValuesNullMap() throws Exception {
        Map<Integer, Integer> intMap = null;
        RAND.fromValues(intMap).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromValuesEmptyMap() throws Exception {
        Map<Integer, Integer> intMap = new HashMap<>();
        RAND.fromValues(intMap).val();
    }

    /**********************
     * fromInts
     ***********************/

    @Test
    public void testFromIntsInteger() throws Exception {
        Integer[] array = RAND.ints().array(25).val();
        Set<Integer> arrayValues = new HashSet<>(asList(array));
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromInts(array).val(),
                x -> arrayValues.contains(x));
        assertTrue(RAND.fromInts(array) instanceof MockUnitInt);
    }

    @Test(expected = NullPointerException.class)
    public void testFromIntsIntegerNullArray() throws Exception {
        Integer[] array = null;
        RAND.fromInts(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIntsIntegerEmptyArray() throws Exception {
        Integer[] array = new Integer[0];
        RAND.fromInts(array).val();
    }

    @Test
    public void testFromIntsInt() throws Exception {
        int[] array = RAND.ints().arrayPrimitive(25).val();
        Set<Integer> arrayValues = new HashSet<>(asList(toObject(array)));
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromInts(array).val(),
                x -> arrayValues.contains(x));
        assertTrue(RAND.fromInts(array) instanceof MockUnitInt);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIntsIntEmtpyArray() throws Exception {
        int[] array = new int[0];
        RAND.fromInts(array).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromIntsIntNullArray() throws Exception {
        int[] array = null;
        RAND.fromInts(array).val();
    }

    @Test
    public void testFromIntsList() throws Exception {
        List<Integer> list = RAND.ints().range(0,5).list(10).val();
        Set<Integer> listValues = new HashSet<>(list);
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromInts(list).val(),
                x -> assertTrue(listValues.contains(x)));
        assertTrue(RAND.fromInts(list) instanceof MockUnitInt);
    }

    @Test(expected = NullPointerException.class)
    public void testFromIntsListNullList() throws Exception {
        List<Integer> list = null;
        RAND.fromInts(list).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIntsListNotEmpty() throws Exception {
        List<Integer> list = new ArrayList<>();
        RAND.fromInts(list).val();
    }

    @Test
    public void testFromIntsValues() {
        Map<Character, Integer> map = RAND.chars().letters().mapVals(25, RAND.ints()::val).val();
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromIntsValues(map).val(),
                x -> assertTrue(map.containsValue(x)));
        assertTrue(RAND.fromIntsValues(map) instanceof MockUnitInt);
    }

    @Test(expected = NullPointerException.class)
    public void testFromIntsValuesNullMap() {
        Map<Collection, Integer> map = null;
        RAND.fromIntsValues(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIntsValuesEmptyMap() {
        Map<String, Integer> map = new HashMap<>();
        RAND.fromIntsValues(map).val();
    }

    @Test
    public void testFromIntsKeys() {
        Map<Integer, Character> map = RAND.chars().letters().mapKeys(25, RAND.ints()::val).val();
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromIntsKeys(map).val(),
                x -> assertTrue(map.containsKey(x)));
        assertTrue(RAND.fromIntsKeys(map) instanceof MockUnitInt);
    }

    @Test(expected = NullPointerException.class)
    public void testFromIntKeysNullMap() {
        Map<Integer, ?> map = null;
        RAND.fromIntsKeys(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIntKeysEmptyMap() {
        Map<Integer, ?> map = new HashMap<>();
        RAND.fromIntsKeys(map).val();
    }

    /**********************
     * fromDoubles
     ***********************/

    @Test
    public void testFromDoublesDouble() throws Exception {
        Double[] array = RAND.doubles().array(25).val();
        Set<Double> arrayValues = new HashSet<>(asList(array));
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromDoubles(array).val(),
                x -> assertTrue(arrayValues.contains(x)));
        assertTrue(RAND.fromDoubles(array) instanceof MockUnitDouble);
    }

    @Test(expected = NullPointerException.class)
    public void testFromDoublesDoubleNullArray() throws Exception {
        Double[] array = null;
        RAND.fromDoubles(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromDoublesDoubleEmptyArray() throws Exception {
        Double[] array = new Double[0];
        RAND.fromDoubles(array).val();
    }

    @Test
    public void testFromDoublesDoublePrim() throws Exception {
        double[] array = RAND.doubles().arrayPrimitive(25).val();
        Set<Double> arrayValues = new HashSet<>(asList(toObject(array)));
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromDoubles(array).val(),
                x -> assertTrue(arrayValues.contains(x)));
        assertTrue(RAND.fromDoubles(array) instanceof MockUnitDouble);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromDoublesDoublePrimEmtpyArray() throws Exception {
        double[] array = new double[0];
        RAND.fromDoubles(array).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromDoublesDoublePrimNullArray() throws Exception {
        double[] array = null;
        RAND.fromDoubles(array).val();
    }

    @Test
    public void testFromDoubleList() throws Exception {
        List<Double> list = RAND.doubles().range(0,5).list(10).val();
        Set<Double> listValues = new HashSet<>(list);
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromDoubles(list).val(),
                x -> assertTrue(listValues.contains(x)));
        assertTrue(RAND.fromDoubles(list) instanceof MockUnitDouble);
    }

    @Test(expected = NullPointerException.class)
    public void testFromDoubleListNullList() throws Exception {
        List<Double> list = null;
        RAND.fromDoubles(list).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromDoublesListNotEmpty() throws Exception {
        List<Double> list = new ArrayList<>();
        RAND.fromDoubles(list).val();
    }

    @Test
    public void testFromDoublesValues() {
        Map<Character, Double> map = RAND.chars().letters().mapVals(25, RAND.doubles()::val).val();
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromDoublesValues(map).val(),
                x -> assertTrue(map.containsValue(x)));
        assertTrue(RAND.fromDoublesValues(map) instanceof MockUnitDouble);
    }

    @Test(expected = NullPointerException.class)
    public void testFromDoublesValuesNullMap() {
        Map<Collection, Double> map = null;
        RAND.fromDoublesValues(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromDoublesValuesEmptyMap() {
        Map<String, Double> map = new HashMap<>();
        RAND.fromDoublesValues(map).val();
    }

    @Test
    public void testFromDoublesKeys() {
        Map<Double, Character> map = RAND.chars().letters().mapKeys(25, RAND.doubles()::val).val();
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromDoublesKeys(map).val(),
                x -> assertTrue(map.containsKey(x)));
        assertTrue(RAND.fromDoublesKeys(map) instanceof MockUnitDouble);
    }

    @Test(expected = NullPointerException.class)
    public void testFromDoubleKeysNullMap() {
        Map<Double, ?> map = null;
        RAND.fromDoublesKeys(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromDoublesKeysEmptyMap() {
        Map<Double, ?> map = new HashMap<>();
        RAND.fromDoublesKeys(map).val();
    }

    /**********************
     * fromLongs
     ***********************/

    @Test
    public void testFromLongsLong() throws Exception {
        Long[] array = RAND.longs().array(25).val();
        Set<Long> arrayValues = new HashSet<>(asList(array));
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromLongs(array).val(),
                x -> assertTrue(arrayValues.contains(x)));
        assertTrue(RAND.fromLongs(array) instanceof MockUnitLong);
    }

    @Test(expected = NullPointerException.class)
    public void testFromLongsLongNullArray() throws Exception {
        Long[] array = null;
        RAND.fromLongs(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLongsLongEmptyArray() throws Exception {
        Long[] array = new Long[0];
        RAND.fromLongs(array).val();
    }

    @Test
    public void testFromLongsLongPrim() throws Exception {
        long[] array = RAND.longs().arrayPrimitive(25).val();
        Set<Long> arrayValues = new HashSet<>(asList(toObject(array)));
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromLongs(array).val(),
                x -> assertTrue(arrayValues.contains(x)));
        assertTrue(RAND.fromLongs(array) instanceof MockUnitLong);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLongsLongPrimEmtpyArray() throws Exception {
        long[] array = new long[0];
        RAND.fromLongs(array).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromLongsLongPrimNullArray() throws Exception {
        long[] array = null;
        RAND.fromLongs(array).val();
    }

    @Test
    public void testFromLongsList() throws Exception {
        List<Long> list = RAND.longs().range(0,5).list(10).val();
        Set<Long> listValues = new HashSet<>(list);
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromLongs(list).val(),
                x -> listValues.contains(x));
        assertTrue(RAND.fromLongs(list) instanceof MockUnitLong);
    }

    @Test(expected = NullPointerException.class)
    public void testFromLongsLongNullList() throws Exception {
        List<Long> list = null;
        RAND.fromLongs(list).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLongsListNotEmpty() throws Exception {
        List<Double> list = new ArrayList<>();
        RAND.fromDoubles(list).val();
    }

    @Test
    public void testFromLongsValues() {
        Map<Character, Long> map = RAND.chars().letters().mapVals(25, RAND.longs()::val).val();
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromLongsValues(map).val(),
                x -> assertTrue(map.containsValue(x)));
        assertTrue(RAND.fromLongsValues(map) instanceof MockUnitLong);
    }

    @Test(expected = NullPointerException.class)
    public void testFromLongsValuesNullMap() {
        Map<Collection, Long> map = null;
        RAND.fromLongsValues(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLongsValuesEmptyMap() {
        Map<String, Long> map = new HashMap<>();
        RAND.fromLongsValues(map).val();
    }

    @Test
    public void testFromLongsKeys() {
        Map<Long, Character> map = RAND.chars().letters().mapKeys(25, RAND.longs()::val).val();
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromLongsKeys(map).val(),
                x -> assertTrue(map.containsKey(x)));

        assertTrue(RAND.fromLongsKeys(map) instanceof MockUnitLong);
    }

    @Test(expected = NullPointerException.class)
    public void testFromLongsKeysNullMap() {
        Map<Long, ?> map = null;
        RAND.fromLongsKeys(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLongsKeysEmptyMap() {
        Map<Long, ?> map = new HashMap<>();
        RAND.fromLongsKeys(map).val();
    }

    /**********************
     * fromStrings
     ***********************/

    @Test
    public void testFromStrings() throws Exception {
        String[] array = {"a", "b", "c"};
        Set<String> arrayValues = new HashSet<>(asList(array));
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromStrings(array).val(),
                x -> assertTrue(arrayValues.contains(x)));
        assertTrue(RAND.fromStrings(array) instanceof MockUnitString);
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringNullArray() throws Exception {
        String[] array = null;
        RAND.fromStrings(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringsEmptyArray() throws Exception {
        String[] array = new String[0];
        RAND.fromStrings(array).val();
    }

    @Test
    public void testFromStringList() throws Exception {
        List<String> list = new ArrayList<>(asList(new String[]{"a","b","c"}));
        Set<String> listValues = new HashSet<>(list);
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromStrings(list).val(),
                x -> assertTrue(listValues.contains(x)));
        assertTrue(RAND.fromStrings(list) instanceof MockUnitString);
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringNullList() throws Exception {
        List<String> list = null;
        RAND.fromStrings(list).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLStringsListNotEmpty() throws Exception {
        List<String> list = new ArrayList<>();
        RAND.fromStrings(list).val();
    }

    @Test
    public void testFromStringValues() {
        Map<Character, String> map = RAND.chars().letters().mapVals(25, RAND.names()::val).val();
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromStringsValues(map).val(),
                x -> assertTrue(map.containsValue(x)));
        assertTrue(RAND.fromStringsValues(map) instanceof MockUnitString);
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringValuesNullMap() {
        Map<Collection, String> map = null;
        RAND.fromStringsValues(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringValuesEmptyMap() {
        Map<String, String> map = new HashMap<>();
        RAND.fromStringsValues(map).val();
    }

    @Test
    public void testFromStringKeys() {
        Map<String, Character> map = RAND.chars().letters().mapKeys(25, RAND.names()::val).val();
        loop(MOCK_CYCLES,
                RANDS,
                r -> r.fromStringsKeys(map).val(),
                x -> map.containsKey(x));

        assertTrue(RAND.fromStringsKeys(map) instanceof MockUnitString);
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringKeysNullMap() {
        Map<String, ?> map = null;
        RAND.fromStringsKeys(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringKeysEmptyMap() {
        Map<String, ?> map = new HashMap<>();
        RAND.fromStringsKeys(map).val();
    }
}
