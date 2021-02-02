package net.andreinc.mockneat.utils;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.types.CallBack;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public final class LoopsUtils {

    private LoopsUtils() {}

    public static void loop(int cycles, CallBack callBack) {
        for (int i = 0; i < cycles; i++) {
            callBack.call();
        }
    }

    public static void loop(int cycles, MockNeat[] array, Consumer<MockNeat> consumer) {
        loop(cycles, () -> Arrays.stream(array).forEach(consumer));
    }

    public static <T> void loop(int cycles, MockNeat[] array, Function<MockNeat, T> map, Consumer<T> consume) {
        loop(cycles, () -> Arrays.stream(array)
                                    .map(map)
                                    .forEach(consume));
    }

    public static <T> void loop(boolean dbg, int cycles, MockNeat[] array, Function<MockNeat, T> map, Consumer<T> consume) {
        loop(cycles,
                () -> Arrays.stream(array)
                        .map(r -> {
                            T o = map.apply(r);
                            return o;
                        })
                        .forEach(consume));

    }
}