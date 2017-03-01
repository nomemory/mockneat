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
                MOCKS,
                r -> r.from(array).val(),
                tm -> assertTrue(possibleValues.contains(tm)));
    }

    @Test
    public void testFromArraySingleElement() throws Exception {
        String[] array = { "a" };
        loop(OBJS_CYCLES,
                MOCKS,
                r -> r.from(array).val(),
                s -> assertTrue(s.equals(array[0])));
    }

    @Test(expected = NullPointerException.class)
    public void testFromNullArray() throws Exception {
        TestModel[] array = null;
        M.from(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromEmptyArray() throws Exception {
        TestModel[] array = new TestModel[]{};
        M.from(array).val();
    }

    @Test
    public void testFromList() throws Exception {
        List<TestModel> list = TestModel.getTestList();
        Set<TestModel> possibleValues = new HashSet<>(list);
        loop(OBJS_CYCLES,
                MOCKS,
                r -> r.from(list).val(),
                tm -> assertTrue(possibleValues.contains(tm)));
    }

    @Test
    public void testFrom1ElementList() throws Exception {
        List<String> list = Arrays.asList(new String[]{ "a" });
        loop(OBJS_CYCLES,
                MOCKS,
                r -> r.from(list).val(),
                s -> assertTrue(s.equals(list.get(0))));
    }

    @Test(expected = NullPointerException.class)
    public void testFromNullList() throws Exception {
        List<TestModel> array = null;
        M.from(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromEmptyList() throws Exception {
        List<TestModel> list = new ArrayList<>();
        M.from(list).val();
    }

    @Test
    public void testEnum() throws Exception {
        Set<TestEnum> possible = new HashSet<>(asList(TestEnum.values()));
        loop(OBJS_CYCLES,
                MOCKS,
                r -> r.from(TestEnum.class).val(),
                tm -> assertTrue(possible.contains(tm)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnumEmpty() throws Exception {
        M.from(TestEnumEmpty.class).val();
    }

    @Test(expected = NullPointerException.class)
    public void testEnumNull() throws Exception {
        Class<? extends Enum> cls = null;
        M.from(cls).val();
    }

    @Test
    public void testFromKeys() throws Exception {
        Map<Integer, Integer> map = M.ints().mapKeys(25, M.ints()::val).val();
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromKeys(map).val(),
                x -> map.containsKey(x));
    }

    @Test(expected = NullPointerException.class)
    public void testFromKeysNullMap() throws Exception {
        Map<Integer, Integer> intMap = null;
        M.fromKeys(intMap).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromKeysEmptyMap() throws Exception {
        Map<Integer, Integer> intMap = new HashMap<>();
        M.fromKeys(intMap).val();
    }

    @Test
    public void testFromValues() throws Exception {
        Map<Integer, Integer> map = M.ints().mapKeys(25, M.ints()::val).val();
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromValues(map).val(),
                x -> assertTrue(map.containsValue(x)));
    }

    @Test(expected = NullPointerException.class)
    public void testFromValuesNullMap() throws Exception {
        Map<Integer, Integer> intMap = null;
        M.fromValues(intMap).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromValuesEmptyMap() throws Exception {
        Map<Integer, Integer> intMap = new HashMap<>();
        M.fromValues(intMap).val();
    }

    /**********************
     * fromInts
     ***********************/

    @Test
    public void testFromIntsInteger() throws Exception {
        Integer[] array = M.ints().array(25).val();
        Set<Integer> arrayValues = new HashSet<>(asList(array));
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromInts(array).val(),
                x -> arrayValues.contains(x));
        assertTrue(M.fromInts(array) instanceof MockUnitInt);
    }

    @Test(expected = NullPointerException.class)
    public void testFromIntsIntegerNullArray() throws Exception {
        Integer[] array = null;
        M.fromInts(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIntsIntegerEmptyArray() throws Exception {
        Integer[] array = new Integer[0];
        M.fromInts(array).val();
    }

    @Test
    public void testFromIntsInt() throws Exception {
        int[] array = M.ints().arrayPrimitive(25).val();
        Set<Integer> arrayValues = new HashSet<>(asList(toObject(array)));
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromInts(array).val(),
                x -> arrayValues.contains(x));
        assertTrue(M.fromInts(array) instanceof MockUnitInt);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIntsIntEmtpyArray() throws Exception {
        int[] array = new int[0];
        M.fromInts(array).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromIntsIntNullArray() throws Exception {
        int[] array = null;
        M.fromInts(array).val();
    }

    @Test
    public void testFromIntsList() throws Exception {
        List<Integer> list = M.ints().range(0,5).list(10).val();
        Set<Integer> listValues = new HashSet<>(list);
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromInts(list).val(),
                x -> assertTrue(listValues.contains(x)));
        assertTrue(M.fromInts(list) instanceof MockUnitInt);
    }

    @Test(expected = NullPointerException.class)
    public void testFromIntsListNullList() throws Exception {
        List<Integer> list = null;
        M.fromInts(list).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIntsListNotEmpty() throws Exception {
        List<Integer> list = new ArrayList<>();
        M.fromInts(list).val();
    }

    @Test
    public void testFromIntsValues() {
        Map<Character, Integer> map = M.chars().letters().mapVals(25, M.ints()::val).val();
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromIntsValues(map).val(),
                x -> assertTrue(map.containsValue(x)));
        assertTrue(M.fromIntsValues(map) instanceof MockUnitInt);
    }

    @Test(expected = NullPointerException.class)
    public void testFromIntsValuesNullMap() {
        Map<Collection, Integer> map = null;
        M.fromIntsValues(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIntsValuesEmptyMap() {
        Map<String, Integer> map = new HashMap<>();
        M.fromIntsValues(map).val();
    }

    @Test
    public void testFromIntsKeys() {
        Map<Integer, Character> map = M.chars().letters().mapKeys(25, M.ints()::val).val();
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromIntsKeys(map).val(),
                x -> assertTrue(map.containsKey(x)));
        assertTrue(M.fromIntsKeys(map) instanceof MockUnitInt);
    }

    @Test(expected = NullPointerException.class)
    public void testFromIntKeysNullMap() {
        Map<Integer, ?> map = null;
        M.fromIntsKeys(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromIntKeysEmptyMap() {
        Map<Integer, ?> map = new HashMap<>();
        M.fromIntsKeys(map).val();
    }

    /**********************
     * fromDoubles
     ***********************/

    @Test
    public void testFromDoublesDouble() throws Exception {
        Double[] array = M.doubles().array(25).val();
        Set<Double> arrayValues = new HashSet<>(asList(array));
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromDoubles(array).val(),
                x -> assertTrue(arrayValues.contains(x)));
        assertTrue(M.fromDoubles(array) instanceof MockUnitDouble);
    }

    @Test(expected = NullPointerException.class)
    public void testFromDoublesDoubleNullArray() throws Exception {
        Double[] array = null;
        M.fromDoubles(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromDoublesDoubleEmptyArray() throws Exception {
        Double[] array = new Double[0];
        M.fromDoubles(array).val();
    }

    @Test
    public void testFromDoublesDoublePrim() throws Exception {
        double[] array = M.doubles().arrayPrimitive(25).val();
        Set<Double> arrayValues = new HashSet<>(asList(toObject(array)));
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromDoubles(array).val(),
                x -> assertTrue(arrayValues.contains(x)));
        assertTrue(M.fromDoubles(array) instanceof MockUnitDouble);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromDoublesDoublePrimEmtpyArray() throws Exception {
        double[] array = new double[0];
        M.fromDoubles(array).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromDoublesDoublePrimNullArray() throws Exception {
        double[] array = null;
        M.fromDoubles(array).val();
    }

    @Test
    public void testFromDoubleList() throws Exception {
        List<Double> list = M.doubles().range(0,5).list(10).val();
        Set<Double> listValues = new HashSet<>(list);
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromDoubles(list).val(),
                x -> assertTrue(listValues.contains(x)));
        assertTrue(M.fromDoubles(list) instanceof MockUnitDouble);
    }

    @Test(expected = NullPointerException.class)
    public void testFromDoubleListNullList() throws Exception {
        List<Double> list = null;
        M.fromDoubles(list).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromDoublesListNotEmpty() throws Exception {
        List<Double> list = new ArrayList<>();
        M.fromDoubles(list).val();
    }

    @Test
    public void testFromDoublesValues() {
        Map<Character, Double> map = M.chars().letters().mapVals(25, M.doubles()::val).val();
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromDoublesValues(map).val(),
                x -> assertTrue(map.containsValue(x)));
        assertTrue(M.fromDoublesValues(map) instanceof MockUnitDouble);
    }

    @Test(expected = NullPointerException.class)
    public void testFromDoublesValuesNullMap() {
        Map<Collection, Double> map = null;
        M.fromDoublesValues(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromDoublesValuesEmptyMap() {
        Map<String, Double> map = new HashMap<>();
        M.fromDoublesValues(map).val();
    }

    @Test
    public void testFromDoublesKeys() {
        Map<Double, Character> map = M.chars().letters().mapKeys(25, M.doubles()::val).val();
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromDoublesKeys(map).val(),
                x -> assertTrue(map.containsKey(x)));
        assertTrue(M.fromDoublesKeys(map) instanceof MockUnitDouble);
    }

    @Test(expected = NullPointerException.class)
    public void testFromDoubleKeysNullMap() {
        Map<Double, ?> map = null;
        M.fromDoublesKeys(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromDoublesKeysEmptyMap() {
        Map<Double, ?> map = new HashMap<>();
        M.fromDoublesKeys(map).val();
    }

    /**********************
     * fromLongs
     ***********************/

    @Test
    public void testFromLongsLong() throws Exception {
        Long[] array = M.longs().array(25).val();
        Set<Long> arrayValues = new HashSet<>(asList(array));
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromLongs(array).val(),
                x -> assertTrue(arrayValues.contains(x)));
        assertTrue(M.fromLongs(array) instanceof MockUnitLong);
    }

    @Test(expected = NullPointerException.class)
    public void testFromLongsLongNullArray() throws Exception {
        Long[] array = null;
        M.fromLongs(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLongsLongEmptyArray() throws Exception {
        Long[] array = new Long[0];
        M.fromLongs(array).val();
    }

    @Test
    public void testFromLongsLongPrim() throws Exception {
        long[] array = M.longs().arrayPrimitive(25).val();
        Set<Long> arrayValues = new HashSet<>(asList(toObject(array)));
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromLongs(array).val(),
                x -> assertTrue(arrayValues.contains(x)));
        assertTrue(M.fromLongs(array) instanceof MockUnitLong);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLongsLongPrimEmtpyArray() throws Exception {
        long[] array = new long[0];
        M.fromLongs(array).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromLongsLongPrimNullArray() throws Exception {
        long[] array = null;
        M.fromLongs(array).val();
    }

    @Test
    public void testFromLongsList() throws Exception {
        List<Long> list = M.longs().range(0,5).list(10).val();
        Set<Long> listValues = new HashSet<>(list);
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromLongs(list).val(),
                x -> listValues.contains(x));
        assertTrue(M.fromLongs(list) instanceof MockUnitLong);
    }

    @Test(expected = NullPointerException.class)
    public void testFromLongsLongNullList() throws Exception {
        List<Long> list = null;
        M.fromLongs(list).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLongsListNotEmpty() throws Exception {
        List<Double> list = new ArrayList<>();
        M.fromDoubles(list).val();
    }

    @Test
    public void testFromLongsValues() {
        Map<Character, Long> map = M.chars().letters().mapVals(25, M.longs()::val).val();
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromLongsValues(map).val(),
                x -> assertTrue(map.containsValue(x)));
        assertTrue(M.fromLongsValues(map) instanceof MockUnitLong);
    }

    @Test(expected = NullPointerException.class)
    public void testFromLongsValuesNullMap() {
        Map<Collection, Long> map = null;
        M.fromLongsValues(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLongsValuesEmptyMap() {
        Map<String, Long> map = new HashMap<>();
        M.fromLongsValues(map).val();
    }

    @Test
    public void testFromLongsKeys() {
        Map<Long, Character> map = M.chars().letters().mapKeys(25, M.longs()::val).val();
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromLongsKeys(map).val(),
                x -> assertTrue(map.containsKey(x)));

        assertTrue(M.fromLongsKeys(map) instanceof MockUnitLong);
    }

    @Test(expected = NullPointerException.class)
    public void testFromLongsKeysNullMap() {
        Map<Long, ?> map = null;
        M.fromLongsKeys(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLongsKeysEmptyMap() {
        Map<Long, ?> map = new HashMap<>();
        M.fromLongsKeys(map).val();
    }

    /**********************
     * fromStrings
     ***********************/

    @Test
    public void testFromStrings() throws Exception {
        String[] array = {"a", "b", "c"};
        Set<String> arrayValues = new HashSet<>(asList(array));
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromStrings(array).val(),
                x -> assertTrue(arrayValues.contains(x)));
        assertTrue(M.fromStrings(array) instanceof MockUnitString);
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringNullArray() throws Exception {
        String[] array = null;
        M.fromStrings(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringsEmptyArray() throws Exception {
        String[] array = new String[0];
        M.fromStrings(array).val();
    }

    @Test
    public void testFromStringList() throws Exception {
        List<String> list = new ArrayList<>(asList(new String[]{"a","b","c"}));
        Set<String> listValues = new HashSet<>(list);
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromStrings(list).val(),
                x -> assertTrue(listValues.contains(x)));
        assertTrue(M.fromStrings(list) instanceof MockUnitString);
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringNullList() throws Exception {
        List<String> list = null;
        M.fromStrings(list).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromLStringsListNotEmpty() throws Exception {
        List<String> list = new ArrayList<>();
        M.fromStrings(list).val();
    }

    @Test
    public void testFromStringValues() {
        Map<Character, String> map = M.chars().letters().mapVals(25, M.names()::val).val();
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromStringsValues(map).val(),
                x -> assertTrue(map.containsValue(x)));
        assertTrue(M.fromStringsValues(map) instanceof MockUnitString);
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringValuesNullMap() {
        Map<Collection, String> map = null;
        M.fromStringsValues(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringValuesEmptyMap() {
        Map<String, String> map = new HashMap<>();
        M.fromStringsValues(map).val();
    }

    @Test
    public void testFromStringKeys() {
        Map<String, Character> map = M.chars().letters().mapKeys(25, M.names()::val).val();
        loop(MOCK_CYCLES,
                MOCKS,
                r -> r.fromStringsKeys(map).val(),
                x -> map.containsKey(x));

        assertTrue(M.fromStringsKeys(map) instanceof MockUnitString);
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringKeysNullMap() {
        Map<String, ?> map = null;
        M.fromStringsKeys(map).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringKeysEmptyMap() {
        Map<String, ?> map = new HashMap<>();
        M.fromStringsKeys(map).val();
    }
}
