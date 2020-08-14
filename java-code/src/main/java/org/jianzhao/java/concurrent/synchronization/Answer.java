package org.jianzhao.java.concurrent.synchronization;

import static org.jianzhao.sugar.Sugar.println;

abstract class Answer {

    static final int TOTAL = 10;

    void start() throws InterruptedException {
        println(this.getClass().getSimpleName());
        var odd = new Thread(this::printOdd);
        var even = new Thread(this::printEven);
        odd.start();
        even.start();
        odd.join();
        even.join();
    }

    abstract void printOdd();

    abstract void printEven();
}
