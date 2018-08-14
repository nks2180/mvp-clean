package com.mvp.common;

import java.util.Objects;

public class Optional<T> {

    private T value;

    private Optional() {
        this.value = null;
    }

    private Optional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> Optional<T> absent() {
        return new Optional<>();
    }

    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    public boolean isPresent() {
        return value != null;
    }

    public interface Action<T> {
        void apply(T value);
    }

    public void ifPresent(Action<T> action) {
        if (value != null) {
            action.apply(value);
        }
    }

    public T get() {
        return value;
    }
}
