package org.jianzhao.java.algorithm;

import static org.jianzhao.sugar.Sugar.println;

public class StrictFpDemo {

    public static void main(String[] args) {
        println(strictfp1());
        println(strictfp0());
    }

    private static String strictfp0() {
        var a = 1d / 3;
        var b = 2f / 7;
        return String.valueOf(a + b);
    }

    private static strictfp String strictfp1() {
        var a = 1d / 3;
        var b = 2f / 7;
        return String.valueOf(a + b);
    }

}
