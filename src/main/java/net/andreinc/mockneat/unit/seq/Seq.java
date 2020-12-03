package net.andreinc.mockneat.unit.seq;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.utils.file.FileManager;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.*;

public final class Seq<T> implements MockUnit<T> {

    private final Iterable<T> iterable;
    private Iterator<T> iterator;

    private boolean cycle;
    private Supplier<T> after;

    public static Seq<?> seq(DictType dictType) {
        return MockNeat.threadLocal().seq(dictType);
    }

    public static <R> Seq<R> seq(Iterable<R> iterable) {
        return MockNeat.threadLocal().seq(iterable);
    }

    public static <R> Seq<R> seq(R[] array) {
        return MockNeat.threadLocal().seq(array);
    }

    public static Seq<String> fromDict(DictType dictType) {
        notNull(dictType, "dictType");
        List<String> lines = FileManager.getInstance().getLines(dictType);
        return new Seq<>(lines);
    }

    public static <R> Seq<R> fromIterable(Iterable<R> iterable) {
        notNull(iterable, "iterable");
        return new Seq<>(iterable);
    }

    public static <R> Seq<R> fromArray(R[] array) {
        notNull(array, "array");
        return new Seq<>(Arrays.asList(array));
    }

    private Seq(Iterable<T> iterable) {
        notNull(iterable, "iterable");
        this.iterable = iterable;
        this.iterator = iterable.iterator();
        isTrue(iterator.hasNext(), IMPOSSIBLE_TO_SEQ_OVER_EMPTY_COLLECTION);
    }

    public Seq<T> cycle(boolean value) {
        this.cycle = value;
        return this;
    }

    public Seq<T> after(T after) {
        this.after = () -> after;
        return this;
    }

    public Seq<T> afterDoMock(MockUnit<T> after) {
        this.after = after.supplier();
        return this;
    }

    @Override
    public Supplier<T> supplier() {
        return () -> {
            if (iterator.hasNext())
                return (T) iterator.next();
            else
                if (cycle) {
                    this.iterator = iterable.iterator();
                    return (T) iterator.next();
                }
                else {
                    if (after == null) {
                        return null;
                    }
                    return (T) after.get();
                }
        };
    }

}
