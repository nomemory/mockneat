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

import org.apache.commons.lang3.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.generate;
import static net.andreinc.aleph.AlephFormatter.template;
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
     * <p>Serializes the generated value {@code <T>} into a file.</p>
     *
     * <p>The method uses the standard Java serilization mechanism.</p>
     *
     * <p><em>Note:</em> If the specified path is not accessible a {@link java.io.UncheckedIOException} is thrown.</p>
     *
     * @param strPath The path of the file where to serialize the generated value.
     */
    default void serialize(String strPath) {
        T object = supplier().get();

        isTrue(object instanceof Serializable, OBJECT_NOT_SERIALIZABLE);

        Serializable sObj = (Serializable) object;
        try { SerializationUtils.serialize(sObj, new FileOutputStream(strPath)); }
        catch (FileNotFoundException e) { throw new UncheckedIOException(e); }
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
    //TODO document
    default void consume(int times, BiConsumer<Integer, T> biConsumer) {
        isTrue(times > 0, NUMBER_OF_TIMES_POSITIVE);
        notNull(biConsumer, "consumer");
        for (int i = 0; i < times; i++) {
            biConsumer.accept(i, val());
        }
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
    default MockUnitInt mapToInt(Function<T, Integer> function) {
        notNull(function, "function");
        Supplier<Integer> supp = () -> function.apply(val());
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
    default MockUnitDouble mapToDouble(Function<T, Double> function) {
        notNull(function, "function");
        Supplier<Double> supp = () -> function.apply(val());
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
    default MockUnitLong mapToLong(Function<T, Long> function) {
        notNull(function, "function");
        Supplier<Long> supp = () -> function.apply(val());
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
        return () -> ifSupplierNotNullDo(supplier(), s -> s.toString());
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
                String fmt = template("Cannot instantiate list '{l.Name}'.")
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
     * <p>This method can used to generate variable-length Lists containing arbitrary data.</p>
     *
     * @param listClass The type of List we are going to use as the internal implementation (Eg.: ArrayList.class)
     * @param sizeUnit The MockUnitInt used to generate the size of the List. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<List<T>>}
     */
    default MockUnit<List<T>> list(Class<? extends List> listClass, MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return () -> list(listClass, sizeUnit.val()).supplier();
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
        return () -> list(sizeUnit.val()).supplier();
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
     * <p>This method can be used to generate a variable-length Set containing arbitrary data.</p>
     *
     * <p><em>Note:</em> The size represents the max size of the Set, but it's not guaranteed to be so, given the nature of the Set (it doesn't accept duplicates).</p>
     *
     * <p><em>Note:</em> If you are using a TreeSet as the implementing class you need to take in consideration it doesn't accept null values.</p>
     *
     * <p><em>Note:</em> The implementing set need to have a NON-ARG constructor, otherwise it won't be instantiated.</p>
     *
     * @param setClass The {@code Set<T>} implementation we are going to use.
     * @param sizeUnit The MockUnitInt used to generate the size of the Set. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<Set<T>>}
     */
    default MockUnit<Set<T>> set(Class<? extends Set> setClass, MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return () -> set(setClass, sizeUnit.val()).supplier();
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
        return () -> set(sizeUnit.val()).supplier();
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
                String fmt = template("Cannot instantiate collection: '#{c.name}'.")
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
        return () -> collection(collectionClass, sizeUnit.val()).supplier();
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
        return () -> collection(sizeUnit.val()).supplier();
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
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
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
        return () -> mapKeys(mapClass, sizeUnit.val(), keysSupplier).supplier();
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
        return () -> mapKeys(sizeUnit.val(), keysSupplier).supplier();
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
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
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
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
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
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
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
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
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
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
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
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
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
     * @param mapClass The type of the Map (eg.: HashMap.class).
     * @param sizeUnit The MockUnitInt used to generate the size of the Map. If the MockUnitInt generates a negative value an exception will be thrown.
     * @param valuesSupplier The supplier of the values.
     * @param <R> The type the values.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, MockUnitInt sizeUnit, Supplier<R> valuesSupplier) {
        return () -> mapVals(mapClass, sizeUnit.val(), valuesSupplier).supplier();
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
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
                                .arg("m", mapClass)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }

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
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
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
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
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
     * <p><em>Note:</em> The implementing map used is HashMap.</p>
     *
     * @param values The array.
     * @return A new {@code MockUnit<Map<T, R>>}
     */
    default MockUnit<Map<T, Integer>> mapVals(int[] values) {
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
    default MockUnit<Map<T, Long>> mapVals(Class<? extends Map> mapClass, long[] values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, Long>> supp = () -> {
            try {
                Map<T, Long> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
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
     * <p><em>Note:</em> The implementing map used is HashMap.</p>
     *
     * @param values The array.
     * @return A new {@code MockUnit<Map<T, R>>}.
     */
    default MockUnit<Map<T, Long>> mapVals(long[] values) {
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
    default MockUnit<Map<T, Double>> mapVals(Class<? extends Map> mapClass, double[] values) {
        notNull(mapClass, "mapClass");
        notNull(values, "values");
        Supplier<Map<T, Double>> supp = () -> {
            try {
                Map<T, Double> result = mapClass.getDeclaredConstructor().newInstance();
                Arrays.stream(values).forEach(value -> put(mapClass, result, supplier().get(), value));
                return result;
            } catch(Exception e) {
                String fmt = template("Cannot instantiate map: '#{m.name}'.")
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

}
