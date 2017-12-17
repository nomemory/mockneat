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
    Supplier<T> supplier();

    /**
     * Returns the generated value.
     * Each subsequent call will trigger the generating mechanism.
     *
     * @return The generated value.
     */
    default T val() { return supplier().get(); }

    /**
     * Serializes the generated value (T) into a file.
     * The method uses the standard Java serilization mechanism.
     *
     * If the specified path is not accessible a {@link java.io.UncheckedIOException} is thrown.
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
     * Returns the generated value, after it applies the supplied function on it.
     * Each subsequent call will trigger the generating mechanism.
     *
     * Example for generating a String that always ends with "X":
     *  {@code
     *  MockNeat m = MockNeat.threadLocal();
     *  String endsWithX = m.strings().val(s -> s + "X");
     *  }
     *
     * @param function This function is applied to the generated value.
     * @return A new pre-processed arbitrary value.
     */
    default <R> R val(Function<T, R> function) {
        notNull(function, "function");
        return function.apply(supplier().get());
    }

    /**
     * Passes the generated value to a @{linkg java.util.function.Consumer}.
     *
     * Example for printing a random email address to the standard output:
     * {@code
     * MockNeat m = MockNeat.threadLocal();
     * m.emails().domain("mail.com").consume(System.out::print);
     * }
     *
     * @param consumer
     */
    default void consume(Consumer<T> consumer) {
        notNull(consumer, "consumer");
        consumer.accept(val());
    }

    /**
     * The method is calling {@link Object#toString()} of the generated value.
     * If the generated value is null an empty string ("") is returned instead of throwing {@link NullPointerException}
     *
     * @return The string representation of the generated value.
     */
    default String valStr() {
        return valStr("");
    }

    /**
     * The method is calling {@link Object#toString()} of the generated value.
     * If the generated value is null returns the 'valueIfNull' parameter instead.
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
     * This can be used in order to add intermediary processing steps before actually generating the value.
     * It works in a very similar way the {@link java.util.stream.Stream#map(Function)} method works.
     *
     * Example for printing an arbitrary email with only uppercase letters:
     * {@code
     *   MockNeat m = MockNeat.threadLocal();
     *   m.emails()
     *    .map(String::toUpperCase)
     *    .consume(System.out::println);
     * }
     *
     * @param function The processing function
     * @return A new MockUnit
     */
    default <R> MockUnit<R> map(Function<T, R> function) {
        notNull(function, "function");
        Supplier<R> supp = () -> function.apply(supplier().get());
        return () -> supp;
    }

    /**
     * This method is used to transform a MockUnit into a MockUnitInt.
     * MockUnitInt is a super-type of MockUnit containing more useful methods for manipulating Integers.
     *
     * @param function The transformation method.
     * @return A new MockUnitInt
     */
    default MockUnitInt mapToInt(Function<T, Integer> function) {
        notNull(function, "function");
        Supplier<Integer> supp = () -> function.apply(val());
        return () -> supp;
    }

    /**
     * This method is used to transform a MockUnit into a MockUnitDouble.
     * MockUnitDouble is a super-type of MockUnit containing more useful methods for manipulating Doubles.
     *
     * @param function The transformation method.
     * @return A new MockUnitDouble
     */
    default MockUnitDouble mapToDouble(Function<T, Double> function) {
        notNull(function, "function");
        Supplier<Double> supp = () -> function.apply(val());
        return () -> supp;
    }

    /**
     * This method is used to transform a MockUnit into a MockUnitLong.
     * MockUnitDouble is a super-type of MockUnit containing more useful methods for manipulating Longs.
     *
     * @param function The transformation method.
     * @return A new MockUnitLong
     */
    default MockUnitLong mapToLong(Function<T, Long> function) {
        notNull(function, "function");
        Supplier<Long> supp = () -> function.apply(val());
        return () -> supp;
    }

    /**
     * This method is used to transform a MockUnit into a MockUnitString.
     * MockUnitString is a super-type of MockUnit containing more useful methods for manipulating Strings.
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
     * This method is used to transform a MockUnit into a MockUnitString by calling the {@link Object#toString()} method.
     * MockUnitString is a super-type of MockUnit containing more useful methods for manipulating Strings.
     *
     * @return
     */
    default MockUnitString mapToString() {
        return () -> ifSupplierNotNullDo(supplier(), s -> s.toString());
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Stream<T>>}.
     * @return A {@code MockUnit<Stream<T>>}
     */
    default MockUnit<Stream<T>> stream() {
        Supplier<Stream<T>> supp = () -> generate(supplier());
        return () -> supp;
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<List<T>>}.
     * This method can used to generate fixed-length Lists containing arbitrary data.
     *
     * <p>Example for creating a {@code List<String>} of (10) emails:</p>
     * {@code
     *  MockNeat m = MockNeat.threadLocal();
     *  List<String> emails = m.emails()
     *                         .list(LinkedList.class, 10)
     *                         .val();
     * }
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
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<List<T>>}.
     * This method can used to generate variable-length Lists containing arbitrary data.
     *
     * <p>Example for creating {@code List<String>} of emails:</p>
     * {@code
     *  MockNeat m = MockNeat.threadLocal();
     *  List<String> emails = m.emails()
     *                         .list(LinkedList.class, m.ints().range(1,10))
     *                         .val();
     * }
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
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<List<T>>}.
     * This method can used to generate fixed-length Lists containing arbitrary data.
     * The internal List implementation will be ArrayList.
     *
     * Example for creating {@code List<String>} of emails:
     *
     * {@code
     *  MockNeat m = MockNeat.threadLocal();
     *  List<String> emails = m.emails()
     *                         .list(10)
     *                         .val();
     * }
     *
     * @param size The size of the list.
     * @return A new {@code MockUnit<List<T>>}
     */
    default MockUnit<List<T>> list(int size) {
        return list(ArrayList.class, size);
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<List<T>>}.
     * This method can used to generate variable-length Lists containing arbitrary data.
     * The internal List implementation will be ArrayList.
     *
     * Example for creating {@code List<String>} of emails:
     *
     * {@code
     *  MockNeat m = MockNeat.threadLocal();
     *  List<String> emails = m.emails()
     *                         .list(   m.ints().range(1,10))
     *                         .val();
     * }
     *
     * @param sizeUnit The MockUnitInt used to generate the size of the List. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<List<T>>}
     */
    default MockUnit<List<T>> list(MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return () -> list(sizeUnit.val()).supplier();
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Set<T>>}.
     * This method can be used to generate a fixed-length Set containing arbitrary data.
     *
     * Note: The size represents the max size of the Set, but it's not guaranteed to be so,
     * given the nature of the Set (it doesn't accept duplicates).
     *
     * Note: If you are using a TreeSet.class as the implementing class you need to take in consideration
     * it doesn't accept null values.
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
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Set<T>>}.
     * This method can be used to generate a fixed-length Set containing arbitrary data.
     *
     * Note: The size represents the max size of the Set, but it's not guaranteed to be so,
     * given the nature of the Set (it doesn't accept duplicates).
     *
     * @param setClass The {@code Set<T>} implementation we are going to use.
     * @param sizeUnit The MockUnitInt used to generate the size of the Set. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<Set<T>>}
     */
    default MockUnit<Set<T>> set(Class<? extends Set> setClass, MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return () -> set(setClass, sizeUnit.val()).supplier();
    }

    default MockUnit<Set<T>> set(int size) {
        return set(HashSet.class, size);
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Set<T>>}.
     * This method can be used to generate a fixed-length Set containing arbitrary data.
     * Internally a HashSet.class will be used.
     *
     * Note: The size represents the max size of the Set, but it's not guaranteed to be so,
     * given the nature of the Set.
     *
     * @param sizeUnit The MockUnitInt used to generate the size of the Set. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<Set<T>>}
     */
    default MockUnit<Set<T>> set(MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return () -> set(sizeUnit.val()).supplier();
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Collection<T>>}.
     * This method can be used to generate a fixed-length Collection containing arbitrary data.
     *
     * NOTE: The implementing collection need to have a NON-ARG constructor, otherwise it won't be instantiated.
     *
     * @param collectionClass  The {@code Collection<T>} implementation we are going to use.
     * @param size The size of the collection.
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
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Collection<T>>}.
     * This method can be used to generate a variable-length Collection containing arbitrary data.
     *
     * NOTE: The implementing collection need to have a NON-ARG constructor, otherwise it won't be instantiated.
     *
     * @param collectionClass  The {@code Collection<T>} implementation we are going to use.
     * @param sizeUnit The MockUnitInt used to generate the size of the Collection. If the MockUnitInt generates a negative value an exception will be thrown.
     * @return A new {@code MockUnit<Collection<T>>}
     */
    default MockUnit<Collection<T>> collection(Class<? extends Collection> collectionClass, MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return () -> collection(collectionClass, sizeUnit.val()).supplier();
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Collection<T>>}.
     * This method can be used to generate a fixed-length Collection containing arbitrary data.
     *
     * Note: The implementing collection is ArrayList.class.
     *
     * @return A new {@code MockUnit<Collection<T>>}
     */
    default MockUnit<Collection<T>> collection(int size) {
        return collection(ArrayList.class, size);
    }


    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Collection<T>>}.
     * This method can be used to generate a fixed-length Collection containing arbitrary data.
     *
     * Note: The implementing collection is ArrayList.class.
     *
     * @return A new {@code MockUnit<Collection<T>>}
     */
    default MockUnit<Collection<T>> collection(MockUnitInt sizeUnit) {
        notNull(sizeUnit, "sizeUnit");
        return () -> collection(sizeUnit.val()).supplier();
    }


    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Supplier<R>}.
     * This method can be used to generate a fixed-length Map containing arbitrary data.
     *
     * NOTE: The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.
     *
     * @param mapClass The implementing class for the Map (eg.: HashMap).
     * @param size The size of the Map.
     * @param keysSupplier The supplier of the keys.
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
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Supplier<R>}.
     * This method can be used to generate a variable-length Map containing arbitrary data.
     *
     * NOTE: The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.
     *
     * @param mapClass The implementing class for the Map (eg.: HashMap).
     * @param sizeUnit The MockUnitInt used to generate the size of the Map. If the MockUnitInt generates a negative value an exception will be thrown.
     * @param keysSupplier The supplier of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, MockUnitInt sizeUnit, Supplier<R> keysSupplier) {
        notNull(sizeUnit, "sizeUnit");
        return () -> mapKeys(mapClass, sizeUnit.val(), keysSupplier).supplier();
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Supplier<R>}.
     * This method can be used to generate a fixed-length Map containing arbitrary data.
     *
     * NOTE: The implementing Map is HashMap.
     *
     * @param size The size of the Map.
     * @param keysSupplier The supplier of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(int size, Supplier<R> keysSupplier) {
        return mapKeys(HashMap.class, size, keysSupplier);
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Supplier<R>}.
     * This method can be used to generate a variable-length Map containing arbitrary data.
     *
     * NOTE: The implementing Map is HashMap.
     *
     * @param sizeUnit The MockUnitInt used to generate the size of the Map. If the MockUnitInt generates a negative value an exception will be thrown.
     * @param keysSupplier The supplier of the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(MockUnitInt sizeUnit, Supplier<R> keysSupplier) {
        return () -> mapKeys(sizeUnit.val(), keysSupplier).supplier();
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Iterable<R>}.
     * The size of the Map is determined by the supplied {@code Iterable<R>}.
     *
     * NOTE: The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.
     *
     * @param mapClass The implementing class for the Map (eg.: HashMap.class).
     * @param keys The {@code Iterable<R>} used to generate the keys.
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
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Iterable<R>}.
     * The size of the Map is determined by the supplied {@code Iterable<R>}.
     *
     * NOTE: The implementing Map is HashMap.
     *
     * @param keys The {@code Iterable<R>} used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(Iterable<R> keys) {
        return mapKeys(HashMap.class, keys);
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given {@code Iterable<R>}.
     * The size of the Map is determined by the supplied {@code Iterable<R>}.
     *
     * NOTE: The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.
     *
     * @param keys The {@code Iterable<R>} used to generate the keys.
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
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.
     * The size of the Map is determined by the supplied array.
     *
     * Note: The map type is HashMap.
     *
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default <R> MockUnit<Map<R, T>> mapKeys(R[] keys) {
        return mapKeys(HashMap.class, keys);
    }


    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.
     * The size of the Map is determined by the supplied array.
     *
     * NOTE: The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.
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
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.
     * The size of the Map is determined by the supplied array.
     *
     * NOTE: The implementing Map is HashMap.
     *
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Integer, T>> mapKeys(int[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.
     * The size of the Map is determined by the supplied array.
     *
     * NOTE: The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.
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
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.
     * The size of the Map is determined by the supplied array.
     *
     * NOTE: The implementing Map is HashMap.
     *
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Long, T>> mapKeys(long[] keys) {
        return mapKeys(HashMap.class, keys);
    }

    /**
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.
     * The size of the Map is determined by the supplied array.
     *
     * NOTE: The implementing Map need to have a NON-ARG constructor, otherwise it won't be instantiated.
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
     * Transforms a {@code MockUnit<T>} into a {@code MockUnit<Map<R,T>>} where the keys are generated from a given array.
     * The size of the Map is determined by the supplied array.
     *
     * NOTE: The implementing Map is HashMap.
     *
     * @param keys The array used to generate the keys.
     * @return A new {@code MockUnit<Map<R, T>>}
     */
    default MockUnit<Map<Double, T>> mapKeys(double[] keys) {
        return mapKeys(HashMap.class, keys);
    }

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

    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, MockUnitInt sizeUnit, Supplier<R> valuesSupplier) {
        return () -> mapVals(mapClass, sizeUnit.val(), valuesSupplier).supplier();
    }

    default <R> MockUnit<Map<T, R>> mapVals(int size, Supplier<R> valuesSupplier) {
        return mapVals(HashMap.class, size, valuesSupplier);
    }

    default <R> MockUnit<Map<T, R>> mapVals(MockUnitInt sizeUnit, Supplier<R> valuesSupplier) {
        return () -> mapVals(sizeUnit.val(), valuesSupplier).supplier();
    }

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

    default <R> MockUnit<Map<T, R>> mapVals(Iterable<R> values) {
        return mapVals(HashMap.class, values);
    }

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

    default <R> MockUnit<Map<T, R>> mapVals(R[] values) {
        return mapVals(HashMap.class, values);
    }

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

    default MockUnit<Map<T, Integer>> mapVals(int[] values) {
        return mapVals(HashMap.class, values);
    }

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

    default MockUnit<Map<T, Long>> mapVals(long[] values) {
        return mapVals(HashMap.class, values);
    }

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

    default MockUnit<Map<T, Double>> mapVals(double[] values) {
        return mapVals(HashMap.class, values);
    }

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
