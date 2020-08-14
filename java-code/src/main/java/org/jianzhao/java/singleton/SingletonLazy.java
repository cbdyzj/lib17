package org.jianzhao.java.singleton;

import static org.jianzhao.sugar.Sugar.println;

public class SingletonLazy {

    static {
        println("SingletonLazy static block");
    }

    private static class SingletonHolder {

        static {
            println("SingletonHolder static block");
        }

        private static final SingletonLazy instance = new SingletonLazy();
    }

    public static SingletonLazy get() {
        println("SingletonLazy::get()");
        return SingletonHolder.instance;
    }

    public static void some(){
        println("SingletonLazy::some()");
    }


    public void someMethod() {
    }

    private SingletonLazy() {
        println("SingletonLazy constructor");
    }

}
