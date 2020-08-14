package org.jianzhao.java.closure;

public class F2 {

    private final int sum;

    public F2() {
        this(0);
    }

    private F2(int sum) {
        this.sum = sum;
    }

    public F2 apply(int v) {
        return new F2(this.sum + v * v);
    }

    public int get() {
        return this.sum;
    }
}
