package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnit;

import java.util.ArrayList;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

//TODO document
public class Shufflers {

    private MockNeat mockNeat;

    public Shufflers(MockNeat mockNeat) {
        this.mockNeat = mockNeat;
    }

    public MockNeat getMockNeat() {
        return mockNeat;
    }

    public void setMockNeat(MockNeat mockNeat) {
        this.mockNeat = mockNeat;
    }

    public <T> MockUnit<T[]> array(T[] source) {
        notNull(source, "source");
        Supplier<T[]> supplier = () -> {
            T[] result = source.clone();
            T tmp = null;
            for(int j, i = 0; i < result.length - 2; ++i) {
                j = mockNeat.ints().range(i, result.length).val();
                tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
            return result;
        };
        return () -> supplier;
    }

    public MockUnit<int[]> arrayInt(int[] source) {
        notNull(source, "source");
        Supplier<int[]> supplier = () -> {
            int[] result = source.clone();
            int tmp;
            for(int j, i = 0; i < result.length; ++i) {
                j = mockNeat.ints().range(i, result.length).val();
                tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
            return result;
        };
        return () -> supplier;
    }

    public MockUnit<long[]> arrayLong(long[] source) {
        notNull(source, "source");
        Supplier<long[]> supplier = () -> {
            long[] result = source.clone();
            long tmp;
            for(int j, i = 0; i < result.length; ++i) {
                j = mockNeat.ints().range(i, result.length).val();
                tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
            return result;
        };
        return () -> supplier;
    }

    public MockUnit<double[]> arrayDouble(double[] source) {
        notNull(source, "source");
        Supplier<double[]> supplier = () -> {
            double[] result = source.clone();
            double tmp;
            for(int j, i = 0; i < result.length; ++i) {
                j = mockNeat.ints().range(i, result.length).val();
                tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
            return result;
        };
        return () -> supplier;
    }

    public MockUnit<float[]> arrayFloat(float[] source) {
        notNull(source, "source");
        Supplier<float[]> supplier = () -> {
            float[] result = source.clone();
            float tmp;
            for(int j, i = 0; i < result.length; ++i) {
                j = mockNeat.ints().range(i, result.length).val();
                tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
            return result;
        };
        return () -> supplier;
    }

    public <T> MockUnit<ArrayList<T>> arrayList(ArrayList<T> source) {
        notNull(source, "source");
        Supplier<ArrayList<T>> supplier = () -> {
            ArrayList<T> result = (ArrayList<T>) source.clone();
            T tmp = null;
            for(int j, i = 0; i < result.size() - 2; ++i) {
                j = mockNeat.ints().range(i, result.size()).val();
                tmp = result.get(i);
                result.set(i, result.get(j));
                result.set(j, tmp);
            }
            return result;
        };

        return () -> supplier;
    }
}
