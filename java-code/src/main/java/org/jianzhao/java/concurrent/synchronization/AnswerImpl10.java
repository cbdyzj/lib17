package org.jianzhao.java.concurrent.synchronization;


import static java.lang.Thread.currentThread;
import static org.jianzhao.sugar.Sugar.println;

/**
 * 使用{@link Object#wait}与{@link Object#notify}
 */
class AnswerImpl10 extends Answer {

    @Override
    void printOdd() {
        this.print(1);
    }

    @Override
    void printEven() {
        this.print(2);
    }

    private synchronized void print(int count) {
        while (count <= TOTAL) {
            this.notify();
           println(currentThread().getName() + ": " + count);
            count += 2;
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
