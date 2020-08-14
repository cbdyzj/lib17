package org.jianzhao.java.concurrent.thread;

import static org.jianzhao.sugar.Sugar.println;

public class ThreadDemo {

    public static void main(String... args) {
        var t = new Thread(() -> {
            println("hi!");
            throw new RuntimeException("线程异常！");
        });
        t.setDaemon(true);
    }
}
