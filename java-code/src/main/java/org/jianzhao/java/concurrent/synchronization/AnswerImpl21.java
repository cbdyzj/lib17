package org.jianzhao.java.concurrent.synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;
import static org.jianzhao.sugar.Sugar.println;

/**
 * 使用{@link ReentrantLock}与{@link Condition}
 */
class AnswerImpl21 extends Answer {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition oddCondition = this.lock.newCondition();
    private final Condition evenCondition = this.lock.newCondition();

    private int nextState = 1;

    @Override
    void printOdd() {
        var oddCount = 1;
        while (oddCount < TOTAL) {
            this.lock.lock();
            try {
                while (this.nextState != 1) {
                    this.oddCondition.await();
                }
                println(currentThread().getName() + ": " + oddCount);
                oddCount += 2;
                this.nextState = 0;
                this.evenCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.lock.unlock();
            }
        }
    }

    @Override
    void printEven() {
        var evenCount = 2;
        while (evenCount <= TOTAL) {
            this.lock.lock();
            try {
                while (this.nextState != 0) {
                    this.evenCondition.await();
                }
                println(currentThread().getName() + ": " + evenCount);
                evenCount += 2;
                this.nextState = 1;
                this.oddCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.lock.unlock();
            }
        }
    }
}
