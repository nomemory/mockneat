package net.andreinc.mockneat.abstraction;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.nio.channels.Channels.newOutputStream;
import static java.nio.channels.FileChannel.open;
import static java.nio.file.StandardOpenOption.*;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.generate;
import static net.andreinc.aleph.AlephFormatter.str;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static net.andreinc.mockneat.utils.MockUnitUtils.*;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

@FunctionalInterface
@SuppressWarnings("unchecked")
public interface MockUnit<T> {

    // Functional Method

    /**
     * This is the sole abstract method of the interface.
     *
     * Needs to be implemented every-time a MockUnit is implemented.
     *
     * @return A {@code Supplier<T>}.
     */
    Supplier<T> supplier();

    /**
     * <p>Returns the generated value as defined by the chain of constraints. This is a closing method.</p>
     *
     * <p>Each subsequent call will trigger the generating mechanism and potentially will return a distinct value from the previous one.</p>
     *
     * @return The generated value.
     */
    default T val() { return supplier().get(); }

    /**
     * <p>Returns the generated value as defined by the chain of constraints. This is a closing method.</p>
     *
     * <p>Each subsequent call will trigger the generating mechanism and potentially will return a distinct value from the previous one.</p>
     *
     * <em>In some JVM languages val is a restricted keyword so {@code get()} was introduced as an alias for the {@code val()} method.</em>
     *
     * @return The generated value.
     */
    default T get() { return val(); }

    /**
     * <p>Serializes the generated value {@code <T>} into a file.</p>
     *
     * <p>The method uses the standard Java serilization mechanism.</p>
     *
     * <p><em>Note:</em> If the specified path is not accessible a {@link java.io.UncheckedIOException} is thrown.</p>
     *
     * @param path The path of the file where to serialize the generated value.
     */
    default void serialize(String path) throws IOException {
        notNull(path, "path");
        T object = supplier().get();

        isTrue(object instanceof Serializable, OBJECT_NOT_SERIALIZABLE);

        Path cachePath = Paths.get(path);
        try(FileChannel channel = open(cachePath, CREATE, WRITE, APPEND)) {
            FileLock lock = channel.lock();
            try(ObjectOutputStream oos = new ObjectOutputStream(newOutputStream(channel))) {
                oos.writeObject(object);
                lock.release();
            }
        }
    }

    /**
     * <p>Returns the generated value, after it applies the supplied {@code Function<T, R>}</p>
     *
     * <p>Each subsequent call will trigger the generating mechanism and potentially will return a distinct value from the previous one.</p>
     *
     * @param function The {@code Function<T,R>} applied to the generated value. {@code <T>} and {@code <R>} can be the same type.
     * @param <R> The type of the newly returned {@code MockUnit}. Can be the same as {@code </T>}
     * @return A new pre-processed arbitrary value of type {@code <R>}.
     */
    default <R> R val(Function<T, R> function) {
        notNull(function, "function");
        return function.apply(supplier().get());
    }

    /**
     * <p>Returns the generated value, after it applies the supplied {@code Function<T, R>}</p>
     *
     * <p>Each subsequent call will trigger the generating mechanism and potentially will return a distinct value from the previous one.</p>
     *
     * <em>In some JVM languages val is a restricted keyword so {@code get()} was introduced as an alias for the {@code val()} method.</em>
     *
     * @param function The {@code Function<T,R>} applied to the generated value. {@code <T>} and {@code <R>} can be the same type.
     * @param <R> The type of the newly returned {@code MockUnit}. Can be the same as {@code </T>}
     * @return A new pre-processed arbitrary value of type {@code <R>}.
     */
    default <R> R get(Function<T, R> function) {
        return val(function);
    }

    /**
     * <p>Passes the arbitrary generated value to a {@code Consumer<T>}.<p>
     *
     * @param consumer The {@code Consumer<T>} method that will make use of the generated value {@code <T>}.
     */
    default void consume(Consumer<T> consumer) {
        notNull(consumer, "consumer");
        consumer.accept(val());
    }


    /**
     * <p>Passes the arbitrary generated values to a {@code BiConsumer<Integer, T>} a number of {@code times}.</p>
     *
     * @param times The number of times we are going to call the {@code BiConsumer}.
     * @param biConsumer The {@code BiConsumer} that is going to consume the arbitrary generated value(s). The first parameter of the {@code BiConsumer} represents the step.
     */
    default void consume(int times, BiConsumer<Integer, T> biConsumer) {
        isTrue(times > 0, NUMBER_OF_TIMES_POSITIVE);
        notNull(biConsumer, "consumer");
        range(0, times).forEach(i -> biConsumer.accept(i, val()));
    }

    /**
     * <p>Before retrieving the generated value, this method calls {@link Object#toString()} on {@code <T>}.</p>
     *
     * <p>If the generated value is null an empty string {@code ("")} is returned instead of throwing {@link NullPointerException}</p>
     *
     * @return The string representation of the generated value.
     */
    default String valStr() {
        return valStr("");
    }

    /**
     * <p>The method is calling {@link Object#toString()} of the generated value.</p>
     *
     * <p>If the generated value is {@code null} returns the {@code valueIfNull} parameter instead.</p>
     *
     * @param valueIfNull The default value to be returned if the generated value is null.
     * @return The string representation of the generated value.
     */
    default String valStr(String valueIfNull) {
        Object val = supplier().get();
        if (null == val) {
            return valueIfNull;
        }
        return val.toString();
    }


    /**
     * <p>This method can be used in order to add intermediary processing before generating the value {@code <T>}.</p>
     *
     * @param function The {@code Function<T,R>} applied to the generated value in the intermediary step. {@code <T>} and {@code <R>} can be the same type.
     * @param <R> The type of the newly returned {@code MockUnit<R>}.
     * @return A new MockUnit
     */
    default <R> MockUnit<R> map(Function<T, R> function) {
        notNull(function, "function");
        Supplier<R> supp = () -> function.apply(supplier().get());
        return () -> supp;
    }

    /**
     * <p>This method is used to transform a {@code MockUnit} into a {@code MockUnitInt}.</p>
     *
     * <p>{@code MockUnitInt} is a super-type of {@code MockUnit} specialized in manipulating Integers.</p>
     *
     * @param function The {@code Function<T,Integer>} applied to the generated value in the intermediary step.
     * @return A new {@code MockUnitInt}.
     */
    default MockUnitInt mapToInt(ToIntFunction<T> function) {
        notNull(function, "function");
        Supplier<Integer> supp = () -> function.applyAsInt(val());
        return () -> supp;
    }

    /**
     * <p>This method is used to transform a {@code MockUnit} into a {@code MockUnitDouble}.</p>
     *
     * <p>{@code MockUnitDouble} is a super-type of {@code MockUnit} specialized in manipulating Doubles.</p>
     *
     * @param function The {@code Function<T,Double>} applied to the generated value in the intermediary step.
     * @return A new {@code MockUnitDouble}
     */
    default MockUnitDouble mapToDouble(ToDoubleFunction<T> function) {
        notNull(function, "function");
        Supplier<Double> supp = () -> function.applyAsDouble(val());
        return () -> supp;
    }

    /**
     * <p>This method is used to transform a {@code MockUnit} into a {@code MockUnitLong}.</p>
     *
     * <p>{@code MockUnitLong} is a super-type of {@code MockUnit} specialized in manipulating Longs.</p>
     *
     * @param function The {@code Function<T,Long>} applied to the generated value in the intermediary step.
     * @return A new {@code MockUnitLong}
     */
    default MockUnitLong mapToLong(ToLongFunction<T> function) {
        notNull(function, "function");
        Supplier<Long> supp = () -> function.applyAsLong(val());
        return () -> supp;
    }

    /**
     * <p>This method is used to transform a MockUnit into a MockUnitString.</p>
     *
     * <p>MockUnitString is a super-type of MockUnit containing more useful methods for manipulating Strings.</p>
     *
     * @param function The transformation method.
     * @return A new MockUnitString
     */
    default MockUnitString mapToString(Function<T, String> function) {
        notNull(function, "function");
        Supplier<String> supp = () -> function.apply(val());
        return () -> supp;
    }

    /**
     * <p>This method is used to transform a {@code MockUnit} into a {@code MockUnitString} by calling the {@link Object#toString()} method.</p>
     *
     * <p>{@code MockUnitString} is a super-type of {@code MockUnit} containing more useful methods for manipulating Strings.</p>
     *
     * @return A new {@code MockUnitString}.
     */
    default MockUnitString mapToString() {
        return () -> ifSupplierNotNullDo(supplier(), Object::toString);
    }

    /**
     * <p>This method is used to transform a {@code MockUnit} into a {@code MOckUnitLocaldate}.</p>
     *
     * @param dateTransformer The transformation {@code Function<T, LocalDate>}
     *
     * @return A new {@code MockUnitLocalDate}
     */
    default MockUnitLocalDate mapToLocalDate(Function<T, LocalDate> dateTransformer) {
        notNull(dateTransformer, "dateTransformer");
        Supplier<LocalDate> supp = () -> dateTransformer.apply(val());
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Stream<T>>}.<p>
     *
     * @return A {@code MockUnit<Stream<T>>}
     */
    default MockUnit<Stream<T>> stream() {
        Supplier<Stream<T>> supp = () -> generate(supplier());
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<List<T>>}.</p>
     *
     * <p>This method can used to generate fixed-length Lists containing arbitrary data.</p>
     *
     * @param listClass The type of List we are going to use as the internal implementation (Eg.: ArrayList.class)
     * @param size The size of the List
     *
     * @return A new {@code MockUnit<List<T>>}
     */
    default MockUnit<List<T>> list(Class<? extends List> listClass, int size) {
        notNull(listClass, "listClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<List<T>> supp = () -> {
            try {
                List<T> result = listClass.getDeclaredConstructor().newInstance();
                loop(size, () -> add(listClass, result, supplier()));
                return result;
            } catch (Exception e) {
                String fmt = str("Cannot instantiate list '#{l.name}'.")
                                .arg("l", listClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<List<T>>}.</p>
     *
     * <p><em>Note:</em> The {@code listSupplier} should not return NULL values. Otherwise a NullPointer exception is thrown.</p>
     *
     * @param listSupplier The supplier that returns a {@code List<T>}
     * @param size The final size of the list.
     * @return A new {@code MockUnit<List<T>>}.
     */
    default MockUnit<List<T>> list(Supplier<List<T>> listSupplier, int size) {
        notNull(listSupplier, "listSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);

        Supplier<List<T>> supp = () -> {
            final List<T> result = listSupplier.get();
            notNullSupp(result, "listSupplier");
            range(0, size).forEach(i -> result.add(supplier().get()));
            return result;
        };

        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<List<T>>}.</p>
     *
     * <p>This method can used to generate variable-length Lists containing arbitrary data.</p>
     *
     * @param listClass The type of List we are going to use as the internal implementation (Eg.: ArrayList.class)
     * @param sizeUnit The MockUnitInt used to generate the size of the List. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<List<T>>}
     */
    default MockUnit<List<T>> list(Class<? extends List> listClass, MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return list(listClass, sizeUnit.val())::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<List<T>>}.</p>
     *
     * <p><em>Note:</em> The {@code listSupplier} should not return NULL values. Otherwise a NullPointer exception is thrown.</p>
     *
     * @param listSupplier The supplier that returns a {@code List<T>}
     * @param sizeUnit The MockUnitInt used to generate the size of the List. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<List<T>>}
     */
    default MockUnit<List<T>> list(Supplier<List<T>> listSupplier, MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return list(listSupplier, sizeUnit.val())::supplier;
    }


    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<List<T>>}.</p>
     *
     * <p>This method can used to generate fixed-length Lists containing arbitrary data.</p>
     *
     * <p><em>Note</em>The internal List implementation will be an {@code ArrayList}.</p>
     *
     * @param size The size of the list.
     * @return A new {@code MockUnit<List<T>>}
     */
    default MockUnit<List<T>> list(int size) {
        return list(ArrayList.class, size);
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<List<T>>}.</p>
     *
     * <p>This method can used to generate variable-length Lists containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The internal List implementation will be {@code ArrayList}.</p>
     *
     * @param sizeUnit The MockUnitInt used to generate the size of the List. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<List<T>>}
     */
    default MockUnit<List<T>> list(MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return list(sizeUnit.val())::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Set<T>>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Set containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The size represents the max size of the Set, but it's not guaranteed to be so, given the nature of the Set (it doesn't accept duplicates).</p>
     *
     * <p><em>Note:</em> If you are using a TreeSet.class as the implementing class you need to take in consideration it doesn't accept null values.</p>
     *
     * <p><em>Note:</em> The implementing set need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param setClass The {@code Set<T>} implementation we are going to use.
     * @param size The max size of the Set.
     * @return A new {@code MockUnit<Set<T>>}
     */
    default MockUnit<Set<T>> set(Class<? extends Set> setClass, int size) {
        notNull(setClass, "setClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Set<T>> supp = () -> {
            try {
                Set<T> result = setClass.getDeclaredConstructor().newInstance();
                loop(size, () -> add(setClass, result, supplier()));
                return result;
            } catch (Exception e) {
                String fmt = format("Cannot instantiate set: '%s'.", setClass.getName());
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Set<T>>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Set containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The {@code setSupplier} should not return NULL values. Otherwise a NullPointer exception is thrown.</p>
     *
     * <p><em>Note:</em> The size represents the max size of the Set, but it's not guaranteed to be so, given the nature of the Set (it doesn't accept duplicates).</p>
     *
     * <p><em>Note:</em> If you are using a TreeSet.class as the implementing class you need to take in consideration it doesn't accept null values.</p>
     *
     * <p><em>Note:</em> The implementing set need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param setSupplier The supplier that returns a {@code Set<T>}.
     * @param size The max size of the Set.
     * @return A new {@code MockUnit<Set<T>>}
     */
    default MockUnit<Set<T>> set(Supplier<Set<T>> setSupplier, int size) {
        notNull(setSupplier, "setSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Set<T>> supp = () -> {
            Set<T> result = setSupplier.get();
            notNullSupp(result, "setSupplier");
            range(0, size).forEach(i -> result.add(supplier().get()));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Set<T>>}.</p>
     *
     * <p>This method can be used to generate a variable-length Set containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The size represents the max size of the Set, but it's not guaranteed to be so, given the nature of the Set (it doesn't accept duplicates).</p>
     *
     * <p><em>Note:</em> If you are using a TreeSet as the implementing class you need to take in consideration it doesn't accept null values.</p>
     *
     * <p><em>Note:</em> The implementing set need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param setClass setSupplier The supplier that returns a {@code Set<T>}.
     * @param sizeUnit The MockUnitInt used to generate the size of the Set. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<Set<T>>}
     */
    default MockUnit<Set<T>> set(Class<? extends Set> setClass, MockUnitInt sizeUnit) {
        notNull(setClass, "setClass");
        notNull(sizeUnit, "sizeUnit");
        return set(setClass, sizeUnit.val())::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Set<T>>}.</p>
     *
     * <p><em>Note:</em> The {@code setSupplier} should not return NULL values. Otherwise a NullPointer exception is thrown.</p>
     *
     * <p>This method can be used to generate a variable-length Set containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The size represents the max size of the Set, but it's not guaranteed to be so, given the nature of the Set (it doesn't accept duplicates).</p>
     *
     * <p><em>Note:</em> If you are using a TreeSet as the implementing class you need to take in consideration it doesn't accept null values.</p>
     *
     * <p><em>Note:</em> The implementing set need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param setSupplier The {@code Set<T>} implementation we are going to use.
     * @param sizeUnit The MockUnitInt used to generate the size of the Set. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<Set<T>>}
     */
    default MockUnit<Set<T>> set(Supplier<Set<T>> setSupplier, MockUnitInt sizeUnit) {
        notNull(setSupplier, "setSupplier");
        notNull(sizeUnit, "sizeUnit");
        return set(setSupplier, sizeUnit.val())::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Set<T>>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Set containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The size represents the max size of the Set, but it's not guaranteed to be so, given the nature of the Set (it doesn't accept duplicates).</p>
     *
     * <p><em>Note:</em> The internal Set implementation is HashSet.</p>
     *
     * @param size The max size of the Set
     * @return A new {@code MockUnit<Set<T>>}
     */
    default MockUnit<Set<T>> set(int size) {
        return set(HashSet.class, size);
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Set<T>>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Set containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The internal Set implementation is HashSet</p>
     *
     * <p><em>Note:</em> The size represents the max size of the Set, but it's not guaranteed to be so, given the nature of the Set.</p>
     *
     * @param sizeUnit The MockUnitInt used to generate the size of the Set. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<Set<T>>}
     */
    default MockUnit<Set<T>> set(MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return set(sizeUnit.val())::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Collection<T>>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Collection containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing collection need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param collectionClass  The {@code Collection<T>} implementation we are going to use.
     * @param size The size of the collection. (If the collection is a Set, this is guaranteed to be the max size, not the actual one).
     * @return A new {@code MockUnit<Collection<T>>}
     */
    default MockUnit<Collection<T>> collection(Class<? extends Collection> collectionClass, int size) {
        notNull(collectionClass, "collectionClass");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Collection<T>> supp = () -> {
            try {
                Collection<T> result = collectionClass.getDeclaredConstructor().newInstance();
                loop(size, () -> add(collectionClass, result, supplier()));
                return result;
            } catch (Exception e) {
                String fmt = str("Cannot instantiate collection: '#{c.name}'.")
                                .arg("c", collectionClass.getName())
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Collection<T>>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Collection containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing collection need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param collectionSupplier  The {@code Supplier<Collection<T>>} we are going to use to obtain the Collection instance.
     * @param size The size of the collection. (If the collection is a Set, this is guaranteed to be the max size, not the actual one).
     * @return A new {@code MockUnit<Collection<T>>}
     */
    default MockUnit<Collection<T>> collection(Supplier<Collection<T>> collectionSupplier, int size) {
        notNull(collectionSupplier, "collectionSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Collection<T>> supp = () -> {
            Collection<T> result = collectionSupplier.get();
            notNullSupp(result, "collectionSupplier");
            range(0, size).forEach(i -> result.add(supplier().get()));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Collection<T>>}.</p>
     *
     * <p>This method can be used to generate a variable-length Collection containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing collection need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param collectionClass  The {@code Collection<T>} implementation we are going to use.
     * @param sizeUnit The MockUnitInt used to generate the size of the Collection. If the MockUnitInt generates a negative value an exception will be thrown. (If the collection is a Set, this is guaranteed to be the max size, not the actual one).
     * @return A new {@code MockUnit<Collection<T>>}
     */
    default MockUnit<Collection<T>> collection(Class<? extends Collection> collectionClass, MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return collection(collectionClass, sizeUnit.val())::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Collection<T>>}.</p>
     *
     * <p>This method can be used to generate a variable-length Collection containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing collection need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param collectionSupplier  The {@code Supplier<Collection<T>>} we are going to use to obtain the Collection instance.
     * @param sizeUnit The MockUnitInt used to generate the size of the Collection. If the MockUnitInt generates a negative value an exception will be thrown. (If the collection is a Set, this is guaranteed to be the max size, not the actual one).
     * @return A new {@code MockUnit<Collection<T>>}
     */
    default MockUnit<Collection<T>> collection(Supplier<Collection<T>> collectionSupplier, MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return collection(collectionSupplier, sizeUnit.val())::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Collection<T>>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Collection containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing collection is ArrayList.class.</p>
     *
     * @param size The size of the Collection. (If the collection is a Set, this guaranteed to be the max size, not the actual one).
     * @return A new {@code MockUnit<Collection<T>>}
     */
    default MockUnit<Collection<T>> collection(int size) {
        return collection(ArrayList.class, size);
    }


    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Collection<T>>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Collection containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing collection is ArrayList.class.</p>
     *
     * @param sizeUnit The size of the Collection generated through a MockUnitInt.
     * @return A new {@code MockUnit<Collection<T>>}
     */
    default MockUnit<Collection<T>> collection(MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return collection(sizeUnit.val())::supplier;
    }


    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Map containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The implementing class for the Map (eg.: HashMap).
     * @param size The size of the Map.
     * @param keysSupplier The supplier of the keys.
     * @param <R> The type of the Keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, int size, Supplier<R> keysSupplier) {
        notNull(mapClass, "mapClass");
        notNull(keysSupplier, "keysSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.getDeclaredConstructor().newInstance();
                loop(size, () -> put(mapClass, result, keysSupplier, supplier()));
                return result;
            } catch (Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>} where the keys are generated from a given {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Map containing arbitrary data.</p>
     *
     * @param mapSupplier The {@code Supplier<Map<R, T>} used to instantiate the {@code Map<R, T>}.
     * @param size The size of the Map.
     * @param keySupplier The supplier of the keys.
     * @param <R> The type of the Keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(Supplier<Map<R, T>> mapSupplier, int size, Supplier<R> keySupplier) {
        notNull(mapSupplier, "mapSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        notNull(keySupplier, "keySupplier");

        Supplier<Map<R,T>> supp = () -> {
            Map<R, T> result = mapSupplier.get();
            notNullSupp(result, "keySupplier");
            range(0, size).forEach(i -> result.put(keySupplier.get(), supplier().get()));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a variable-length Map containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The implementing class for the Map (eg.: HashMap).
     * @param sizeUnit The MockUnitInt used to generate the size of the Map. If the MockUnitInt generates a negative value an exception will be thrown.
     * @param keysSupplier The supplier of the keys.
     * @param <R> The type of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, MockUnitInt sizeUnit, Supplier<R> keysSupplier) {
        notNull(sizeUnit, "sizeUnit");
        return mapKeys(mapClass, sizeUnit.val(), keysSupplier)::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a variable-length Map containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapSupplier The {@code Supplier<Map<R, T>>} that is used to obtain a Map instance.
     * @param sizeUnit The MockUnitInt used to generate the size of the Map. If the MockUnitInt generates a negative value an exception will be thrown.
     * @param keySupplier The supplier of the keys.
     * @param <R> The type of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(Supplier<Map<R, T>> mapSupplier, MockUnitInt sizeUnit, Supplier<R> keySupplier) {
        notNull(sizeUnit, "sizeUnit");
        return mapKeys(mapSupplier, sizeUnit.val(), keySupplier)::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Map containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing Map is HashMap.</p>
     *
     * @param size The size of the Map.
     * @param keysSupplier The supplier of the keys.
     * @param <R> The type of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(int size, Supplier<R> keysSupplier) {
        return mapKeys(HashMap.class, size, keysSupplier);
    }


    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a variable-length Map containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing Map is HashMap.</p>
     *
     * @param sizeUnit The MockUnitInt used to generate the size of the Map. If the MockUnitInt generates a negative value an exception will be thrown.
     * @param keysSupplier The supplier of the keys.
     * @param <R> The type of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(MockUnitInt sizeUnit, Supplier<R> keysSupplier) {
        notNull(sizeUnit, "sizeUnit");
        return mapKeys(sizeUnit.val(), keysSupplier)::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Iterable<R>}.</p>
     *
     * <p>The size of the Map is determined by the supplied {@code Iterable<R>}.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The implementing class for the Map (eg.: HashMap.class).
     * @param keys The {@code Iterable<R>} used to generate the keys.
     * @param <R> The type of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, Iterable<R> keys) {
        notNull(mapClass, "mapClass");
        notNull(keys, "keys");
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.getDeclaredConstructor().newInstance();
                keys.forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            } catch (Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Iterable<R>}.</p>
     *
     * <p>The size of the Map is determined by the supplied {@code Iterable<R>}.</p>
     *
     * @param mapSupplier A supplier method returning a {@code Map<R, T>}.
     * @param keys The {@code Iterable<R>} used to generate the keys.
     * @param <R> The type of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(Supplier<Map<R, T>> mapSupplier, Iterable<R> keys) {
        notNull(mapSupplier, "mapSupplier");
        notNull(keys, "keys");
        Supplier<Map<R, T>> supp = () -> {
            Map<R, T> result = mapSupplier.get();
            notNullSupp(result, "mapSupplier");
            keys.forEach(key -> result.put(key, supplier().get()));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Iterable<R>}.</p>
     *
     * <p>The size of the Map is determined by the supplied {@code Iterable<R>}.</p>
     *
     * <p><em>Note:</em> The implementing Map is HashMap.</p>
     *
     * @param keys The {@code Iterable<R>} used to generate the keys.
     * @param <R> The type of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(Iterable<R> keys) {
        return mapKeys(HashMap.class, keys);
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Iterable<R>}.</p>
     *
     * <p>The size of the Map is determined by the supplied {@code Iterable<R>}.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The implementing class of the collection (eg.: LinkedList).
     * @param keys The {@code Iterable<R>} used to generate the keys.
     * @param <R> The type of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, R[] keys) {
        notNull(mapClass, "mapClass");
        notNull(keys, "keys");
        Supplier<Map<R, T>> supp = () -> {
            try {
                Map<R, T> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Iterable<R>}.</p>
     *
     * <p>The size of the Map is determined by the supplied {@code Iterable<R>}.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapSupplier The supplier method returning a {@code Map<R,T>}.
     * @param keys The {@code Iterable<R>} used to generate the keys.
     * @param <R> The type of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(Supplier<Map<R, T>> mapSupplier, R[] keys) {
        notNull(mapSupplier, "mapSupplier");
        notNull(keys, "keys");
        Supplier<Map<R, T>> supp = () -> {
            Map<R, T> result = mapSupplier.get();
            notNullSupp(result, "mapSupplier");
            Arrays.stream(keys).forEach(key -> result.put(key, supplier().get()));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.</p>
     *
     * <p>The size of the Map is determined by the supplied array.</p>
     *
     * <p><em>Note:</em> The map type is HashMap.</p>
     *
     * @param keys The array used to generate the keys.
     * @param <R> The type of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(R[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.</p>
     *
     * <p>The size of the Map is determined by the supplied array.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The type of the Map (eg.: HashMap.class).
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Integer, T>> mapKeys(Class<? extends Map> mapClass, int[] keys) {
        notNull(mapClass, "mapClass");
        notNull(keys, "keys");
        Supplier<Map<Integer, T>> supp = () -> {
            try {
                Map<Integer, T> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.</p>
     *
     * <p>The size of the Map is determined by the supplied array.</p>
     *
     * @param mapSupplier The supplier method that returns a {@code Map<Integer, T>}.
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Integer, T>> mapKeys(Supplier<Map<Integer, T>> mapSupplier, int[] keys) {
        notNull(mapSupplier, "mapSupplier");
        notNull(keys, "keys");
        Supplier<Map<Integer, T>> supp = () -> {
            Map<Integer, T> result = mapSupplier.get();
            notNullSupp(result, "mapSupplier");
            Arrays.stream(keys).forEach(key -> result.put(key, supplier().get()));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.</p>
     *
     * <p>The size of the Map is determined by the supplied array.</p>
     *
     * <p><em>Note:</em> The implementing Map is HashMap.</p>
     *
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Integer, T>> mapKeys(int[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.</p>
     *
     * <p>The size of the Map is determined by the supplied array.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The type of the Map (eg.: HashMap.class).
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Long, T>> mapKeys(Class<? extends Map> mapClass, long[] keys) {
        notNull(mapClass, "mapClass");
        notNull(keys, "keys");
        Supplier<Map<Long, T>> supp = () -> {
            try {
                Map<Long, T> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.</p>
     *
     * <p>The size of the Map is determined by the supplied array.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapSupplier The supplier method that returns a {@code Map<Long, T>}.
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Long, T>> mapKeys(Supplier<Map<Long, T>> mapSupplier, long[] keys) {
        notNull(mapSupplier, "mapSupplier");
        notNull(keys, "keys");
        Supplier<Map<Long, T>> supp = () -> {
            Map<Long, T> result = mapSupplier.get();
            notNullSupp(result, "mapSupplier");
            Arrays.stream(keys).forEach(key -> result.put(key, supplier().get()));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.</p>
     *
     * <p>The size of the Map is determined by the supplied array.</p>
     *
     * <p><em>Note:</em> The implementing Map is HashMap.</p>
     *
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Long, T>> mapKeys(long[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.</p>
     *
     * <p>The size of the Map is determined by the supplied array.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The type of the Map (eg.: HashMap.class).
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Double, T>> mapKeys(Class<? extends Map> mapClass, double[] keys) {
        notNull(mapClass, "mapClass");
        notNull(keys, "keys");
        Supplier<Map<Double, T>> supp = () -> {
            try {
                Map<Double, T> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(keys).forEach(key -> put(mapClass, result, key, supplier().get()));
                return result;
            }
            catch (Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.</p>
     *
     * <p>The size of the Map is determined by the supplied array.</p>
     *
     * @param mapSupplier The supplier method that returns a {@code Map<Double, T>}.
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Double, T>> mapKeys(Supplier<Map<Double, T>> mapSupplier, double[] keys) {
        notNull(mapSupplier, "mapSupplier");
        notNull(keys, "keys");
        Supplier<Map<Double, T>> supp = () -> {
            Map<Double, T> result = mapSupplier.get();
            notNullSupp(result, "mapSupplier");
            Arrays.stream(keys).forEach(key -> result.put(key, supplier().get()));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.</p>
     *
     * <p>The size of the Map is determined by the supplied array.</p>
     *
     * <p><em>Note:</em> The implementing Map is HashMap.</p>
     *
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Double, T>> mapKeys(double[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values generated from a {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a variable-length Map containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The type of the Map (eg.: HashMap.class).
     * @param size The size of the map.
     * @param valuesSupplier The supplier of the values.
     * @param <R> The type of the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, int size, Supplier<R> valuesSupplier) {
        notNull(mapClass, "mapClass");
        notNull(valuesSupplier, "valuesSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.getDeclaredConstructor().newInstance();
                loop(size, () -> put(mapClass, result, supplier(), valuesSupplier));
                return result;
            } catch (Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values generated from a {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a variable-length Map containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapSupplier The supplier method that returns a {@code Map<T, R>}.
     * @param size The size of the map.
     * @param valuesSupplier The supplier of the values.
     * @param <R> The type of the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(Supplier<Map<T, R>> mapSupplier, int size, Supplier<R> valuesSupplier) {
        notNull(mapSupplier, "mapSupplier");
        notNull(valuesSupplier, "valuesSupplier");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<Map<T, R>> supp = () -> {
            Map<T, R> result = mapSupplier.get();
            notNullSupp(result, "mapSupplier");
            range(0, size).forEach(i -> result.put(supplier().get(), valuesSupplier.get()));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values generated from a {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a variable-length Map containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The type of the Map (eg.: HashMap.class).
     * @param sizeUnit The MockUnitInt used to generate the size of the Map. If the MockUnitInt generates a negative value an exception will be thrown.
     * @param valuesSupplier The supplier of the values.
     * @param <R> The type the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, MockUnitInt sizeUnit, Supplier<R> valuesSupplier) {
        return mapVals(mapClass, sizeUnit.val(), valuesSupplier)::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values generated from a {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a variable-length Map containing arbitrary data.</p>
     *
     * @param mapSupplier The supplier method that returns {@code Map<T, R>}.
     * @param sizeUnit The MockUnitInt used to generate the size of the Map. If the MockUnitInt generates a negative value an exception will be thrown.
     * @param valuesSupplier The supplier of the values.
     * @param <R> The type the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(Supplier<Map<T, R>> mapSupplier, MockUnitInt sizeUnit, Supplier<R> valuesSupplier) {
        notNull(sizeUnit, "sizeUnit");
        return mapVals(mapSupplier, sizeUnit.val(), valuesSupplier)::supplier;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values generated from a {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a fixed-length Map containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing Map is HashMap.</p>
     *
     * @param size The size of the map.
     * @param valuesSupplier The supplier of the values.
     * @param <R> The type the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(int size, Supplier<R> valuesSupplier) {
        return mapVals(HashMap.class, size, valuesSupplier);
    }


    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values generated from a {@code Supplier<R>}.</p>
     *
     * <p>This method can be used to generate a variable-length Map containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The implementing Map is HashMap.</p>
     *
     * @param sizeUnit The MockUnitInt used to generate the size of the Map. If the MockUnitInt generates a negative value an exception will be thrown.
     * @param valuesSupplier The supplier of the values.
     * @param <R> The type the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(MockUnitInt sizeUnit, Supplier<R> valuesSupplier) {
        return () -> mapVals(sizeUnit.val(), valuesSupplier).supplier();
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values generated from a {@code Iterable<R>}.</p>
     *
     * <p>The size of the Map is strictly determined by the size of the {@code Iterable<R>}.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The implementing class for the Map (eg.: HashMap.class).
     * @param values The {@code Iterable<R>} from where the values are selected in order.
     * @param <R> The type the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, Iterable<R> values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.getDeclaredConstructor().newInstance();
                values.forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch (Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }

        };
        return () -> supp;
    }


    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values generated from a {@code Iterable<R>}.</p>
     *
     * <p>The size of the Map is strictly determined by the size of the {@code Iterable<R>}.</p>
     *
     * @param  mapSupplier The supplier returning the {@code Map<T, R>} that will hold the values.
     * @param values The {@code Iterable<R>} from where the values are selected in order.
     * @param <R> The type the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(Supplier<Map<T, R>> mapSupplier, Iterable<R> values) {
        notNull(mapSupplier, "mapSupplier");
        notNull(values, "values");
        Supplier<Map<T, R>> supp = () -> {
            Map<T, R> result = mapSupplier.get();
            notNullSupp(result, "result");
            values.forEach(value -> result.put(supplier().get(), value));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values are generated from a {@code Iterable<R>}.</p>
     *
     * <p>The size of the map is strictly determined by the size of the {@code Iterable<R>}.</p>
     *
     * <p><em>Note:</em> The implementing map used is HashMap.</p>
     *
     * @param values The {@code Iterable<R>} from where the values are selected in order.
     * @param <R> The type the values.
     * @return A new {@code MockUnit<T,R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(Iterable<R> values) {
        return mapVals(HashMap.class, values);
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The implementing class for the Map (eg.: HashMap.class).
     * @param values The array.
     * @param <R> The type the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, R[] values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, R>> supp = () -> {
            try {
                Map<T, R> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapSupplier The supplier method that returns the {@code Map<T, R>}.
     * @param values The array.
     * @param <R> The type the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(Supplier<Map<T, R>> mapSupplier, R[] values) {
        notNull(mapSupplier, "mapSupplier");
        notNull(values, "values");
        Supplier<Map<T, R>> supp = () -> {
            Map<T, R> result = mapSupplier.get();
            notNullSupp(result, "result");
            Arrays.stream(values).forEach(value -> result.put(supplier().get(), value));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * <p><em>Note:</em> The implementing map used is HashMap.</p>
     *
     * @param values The array.
     * @param <R> The type the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(R[] values) {
        return mapVals(HashMap.class, values);
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The implementing class for the Map (eg.: HashMap.class).
     * @param values The array.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default MockUnit<Map<T, Integer>> mapVals(Class<? extends Map> mapClass, int[] values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, Integer>> supp = () -> {
            try {
                Map<T, Integer> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,Integer>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * @param mapSupplier The supplier method that returns a {@code Map<T, Integer>}.
     * @param values The array.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default MockUnit<Map<T, Integer>> mapVals(Supplier<Map<T, Integer>> mapSupplier, int[] values) {
        notNull(mapSupplier, "mapSupplier");
        notNull(values, "values");
        Supplier<Map<T, Integer>> supp = () -> {
            Map<T, Integer> result = mapSupplier.get();
            notNullSupp(result, "mapSupplier");
            Arrays.stream(values).forEach(value -> result.put(supplier().get(), value));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,Integer>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * <p><em>Note:</em> The implementing map used is HashMap.</p>
     *
     * @param values The array.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default MockUnit<Map<T, Integer>> mapVals(int[] values) {
        return mapVals(HashMap.class, values);
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,Long>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The implementing class for the Map (eg.: HashMap.class).
     * @param values The array.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default MockUnit<Map<T, Long>> mapVals(Class<? extends Map> mapClass, long[] values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, Long>> supp = () -> {
            try {
                Map<T, Long> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,Long>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * @param mapSupplier The supplier method that returns a {@code Map<T, Long>}.
     * @param values The array.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default MockUnit<Map<T, Long>> mapVals(Supplier<Map<T, Long>> mapSupplier, long[] values) {
        notNull(mapSupplier, "mapSupplier");
        notNull(values, "values");
        Supplier<Map<T, Long>> supp = () -> {
            Map<T, Long> result = mapSupplier.get();
            notNullSupp(result, "mapSupplier");
            Arrays.stream(values).forEach(value -> result.put(supplier().get(), value));
            return result;
        };
        return () -> supp;
    }


    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * <p><em>Note:</em> The implementing map used is HashMap.</p>
     *
     * @param values The array.
     * @return A new {@code MockUnit<Map<T, R>>}.
     */
    default MockUnit<Map<T, Long>> mapVals(long[] values) {
        return mapVals(HashMap.class, values);
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,Double>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * <p><em>Note:</em> The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param mapClass The implementing class for the Map (eg.: HashMap.class).
     * @param values The array.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default MockUnit<Map<T, Double>> mapVals(Class<? extends Map> mapClass, double[] values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, Double>> supp = () -> {
            try {
                Map<T, Double> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(Exception e) {
                String fmt = str("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,Double>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * @param mapSupplier The supplier method that returns a {@code Map<T, Double>}.
     * @param values The array.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default MockUnit<Map<T, Double>> mapVals(Supplier<Map<T, Double>> mapSupplier, double[] values) {
        notNull(mapSupplier, "mapSupplier");
        notNull(values, "values");
        Supplier<Map<T, Double>> supp = () -> {
            Map<T, Double> result = mapSupplier.get();
            notNullSupp(result, "mapSupplier");
            Arrays.stream(values).forEach(value -> result.put(supplier().get(), value));
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T,R>} where the values are generated from an array.</p>
     *
     * <p>The size of the map is strictly determined by the size of the array.</p>
     *
     * <p><em>Note:</em> The implementing map used is HashMap.</p>
     *
     * @param values The array.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default MockUnit<Map<T, Double>> mapVals(double[] values) {
        return mapVals(HashMap.class, values);
    }


    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T[]>}.</p>
     *
     * @param cls The type of the value generated by the MockUnit (eg. Student.class)
     * @param size The size of the array.
     * @return A new {@code MockUnit<T[]>}.
     */
    default MockUnit<T[]> array(Class<T> cls, int size) {
        notNull(cls, "cls");
        isTrue(size>=0, SIZE_BIGGER_THAN_ZERO);
        Supplier<T[]> supp = () -> {
            T[] objs = (T[]) Array.newInstance(cls, size);
            range(0, size).forEach(i -> objs[i] = supplier().get());
            return objs;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms a {@code MockUnit<T>} into a {@code MockUnit<T[]>}.</p>
     *
     * @param arraySupplier The supplier method for the {@code T[]} array. The array size will be used.
     * @return A new {@code MockUnit<T[]>}.
     */
    default MockUnit<T[]> array(Supplier<T[]> arraySupplier) {
        notNull(arraySupplier, "arraySupplier");
        Supplier<T[]> supp = () -> {
            T[] result = arraySupplier.get();
            notNullSupp(result, "arraySupplier");
            range(0, result.length).forEach(i -> result[i] = supplier().get());
            return result;
        };
        return () -> supp;
    }
}
