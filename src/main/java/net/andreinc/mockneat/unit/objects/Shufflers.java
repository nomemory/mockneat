package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.ArrayList;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

//TODO document
public class Shufflers extends MockUnitBase {

    public static Shufflers shufflers() {
        return MockNeat.threadLocal().shufflers();
    }

    protected Shufflers() {}

    /**
     * <p>Returns a {@code Shufflers} object than gets the arbitrary permutations of the source.</p>
     *
     * @param mockNeat a MockNeat instance
     */
    public Shufflers(MockNeat mockNeat) {
        super(mockNeat);
    }

    public <T> MockUnit<T[]> array(T[] source) {
        notNull(source, "source");
        Supplier<T[]> supplier = () -> {
            T[] result = source.clone();
            T tmp;
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
            //noinspection unchecked
            ArrayList<T> result = (ArrayList<T>) source.clone();
            T tmp;
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

    public MockUnitString string(String source) {
        notNull(source, "source");
        Supplier<String> supplier = () -> {
            char[] chars = source.toCharArray();
            char c;
            for(int j, i = 0; i < chars.length; ++i) {
                j  = mockNeat.ints().range(i, chars.length).val();
                c = chars[i];
                chars[i] = chars[j];
                chars[j] = c;
            }
            return new String(chars);
        };
        return () -> supplier;
    }
}
