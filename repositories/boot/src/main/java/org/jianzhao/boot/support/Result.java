package org.jianzhao.boot.support;

import java.util.Objects;

/**
 * Result wrapper
 */
public final class Result<T> {

    private final T t;

    private Result(T t) {
        Objects.requireNonNull(t);
        this.t = t;
    }

    public T get() {
        return this.t;
    }

    public static <U> Result<U> of(U u) {
        return new Result<>(u);
    }
}
