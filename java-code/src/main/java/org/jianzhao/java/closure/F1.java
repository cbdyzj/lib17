package org.jianzhao.java.closure;

public class F1 {

    private int sum = 0;

    public F1 apply(int v) {
        this.sum += v * v;
        return this;
    }

    public int get() {
        return this.sum;
    }
}
