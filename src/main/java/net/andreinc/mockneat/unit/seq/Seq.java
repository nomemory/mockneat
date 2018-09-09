package net.andreinc.mockneat.unit.seq;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.utils.file.FileManager;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.*;

public final class  Seq<T> implements MockUnit<T> {

    private final Iterable<T> iterable;
    private Iterator<T> iterator;

    private boolean cycle;
    private Supplier<T> after;

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
