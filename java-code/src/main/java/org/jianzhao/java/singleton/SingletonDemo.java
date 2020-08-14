package org.jianzhao.java.singleton;

import static org.jianzhao.sugar.Sugar.println;

public class SingletonDemo {

    public static void main(String[] args) throws InterruptedException {
        println(Singleton.get());
        SingletonLazy.some();
        Thread.sleep(1000);
        println(SingletonLazy.get());
    }
}
