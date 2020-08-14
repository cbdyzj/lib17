package org.jianzhao.java.closure;

public class F3 {

    public F3 apply(final int v) {
        return new F3() {
            @Override
            public int get() {
                return F3.this.get() + v * v;
            }
        };
    }

    public int get() {
        return 0;
    }

}
