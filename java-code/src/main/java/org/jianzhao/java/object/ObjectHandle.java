package org.jianzhao.java.object;

import java.util.Objects;
import java.util.function.Consumer;

public class ObjectHandle<T> {

    public ObjectHandle() {
    }

    public ObjectHandle(T target) {
        this.set(target);
    }

    private T target;

    public T get() {
        Objects.requireNonNull(this.target);
        return this.target;
    }

    public void set(T target) {
        Objects.requireNonNull(target);
        this.target = target;
    }

    public ObjectHandle<T> apply(Consumer<T> consumer) {
        Objects.requireNonNull(consumer);
        consumer.accept(this.get());
        return this;
    }

}
