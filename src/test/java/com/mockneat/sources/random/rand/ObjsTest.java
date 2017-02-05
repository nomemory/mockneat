package com.mockneat.sources.random.rand;

import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static com.mockneat.sources.random.rand.RandTestConstants.NEXT_GENERIC_CYCLES;
import static com.mockneat.sources.random.rand.RandTestConstants.RAND;
import static com.mockneat.sources.random.rand.RandTestConstants.RANDS;
import static com.mockneat.utils.FunctUtils.cycle;
import static org.junit.Assert.assertTrue;

public class ObjsTest {

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
    public void testNextObjectCorrectValues() throws Exception {
        TestModel[] array = TestModel.getTestArray();
        Set<TestModel> possibleValues = new HashSet<>(asList(array));
        cycle(NEXT_GENERIC_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.objs().from(array).val())
                    .forEach(tm -> assertTrue(possibleValues.contains(tm))));
    }

    @Test
    public void testNextOjectCorrectValues2() throws Exception {
        String[] array = { "a" };
        cycle(NEXT_GENERIC_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.objs().from(array).val())
                    .forEach(s -> assertTrue(s.equals(array[0]))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextObjectNonNullAlphabet() throws Exception {
        TestModel[] array = null;
        RAND.objs().from(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextObjectNonEmptyAlphabet() throws Exception {
        TestModel[] array = new TestModel[]{};
        RAND.objs().from(array).val();
    }

    @Test
    public void testNextObjectCorrectValues_list() throws Exception {
        List<TestModel> list = TestModel.getTestList();
        Set<TestModel> possibleValues = new HashSet<>(list);
        cycle(NEXT_GENERIC_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.objs().from(list).val())
                    .forEach(tm -> assertTrue(possibleValues.contains(tm))));
    }

    @Test
    public void testNextObjectCorrectValues_list2() throws Exception {
        List<String> list = Arrays.asList(new String[]{ "a" });
        cycle(NEXT_GENERIC_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.objs().from(list).val())
                    .forEach(s -> assertTrue(s.equals(list.get(0)))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextObjectNonNullAlphabet_list() throws Exception {
        List<TestModel> array = null;
        RAND.objs().from(array).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextObjectNonEmptyAlphabet_list() throws Exception {
        List<TestModel> list = new ArrayList<>();
        RAND.objs().from(list).val();
    }

    @Test
    public void testNextObjectCorrectValues_enum() throws Exception {
        Set<TestEnum> possible = new HashSet<>(Arrays.asList(TestEnum.values()));
        cycle(NEXT_GENERIC_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.objs().from(TestEnum.class).val())
                    .forEach(tm -> assertTrue(possible.contains(tm))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextObjectNonEmptyAlphabet_enum() throws Exception {
        RAND.objs().from(TestEnumEmpty.class).val();
    }
}
