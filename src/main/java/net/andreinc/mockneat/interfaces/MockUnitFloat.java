package net.andreinc.mockneat.interfaces;

import net.andreinc.mockneat.utils.ValidationUtils;

import java.util.function.Supplier;
import java.util.stream.DoubleStream;

import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.utils.ValidationUtils.isTrue;

//TODO add it in documentation
public interface MockUnitFloat extends MockUnit<Float> {

    default MockUnit<DoubleStream> doubleStream() {
        Supplier<DoubleStream> supp = () -> DoubleStream.generate(supplier()::get);
        return () -> supp;
    }

    default MockUnit<float[]> arrayPrimitive(int size) {
        isTrue(size>=0, ValidationUtils.SIZE_BIGGER_THAN_ZERO);
        Supplier<float[]> supp = () -> {
            final float[] result = new float[size];
            range(0, size).forEach(i -> result[i] = val());
            return result;
        };
        return () -> supp;
    }
    default MockUnit<Float[]> array(int size) {
        isTrue(size>=0, ValidationUtils.SIZE_BIGGER_THAN_ZERO);
        Supplier<Float[]> supp = () -> {
            final Float[] result = new Float[size];
            range(0, size).forEach(i -> result[i] = val());
            return result;
        };
        return () -> supp;
    }
}
