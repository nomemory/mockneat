package com.mockneat.types;

public interface TriConsumer<T, U, R> {
    void consume(T t, U u, R r);
}
