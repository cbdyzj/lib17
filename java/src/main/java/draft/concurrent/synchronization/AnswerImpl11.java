package draft.concurrent.synchronization;


import static java.lang.Thread.currentThread;
import static org.jianzhao.sugar.Sugar.println;

/**
 * 使用{@link Object#wait}与{@link Object#notify}
 */
class AnswerImpl11 extends Answer {

    private final Object oddLock = new Object();
    private final Object evenLock = new Object();

    private int nextState = 1;

    @Override
    void printOdd() {
        var oddCount = 1;
        while (oddCount < TOTAL) {
            while (this.nextState != 1) {
                synchronized (this.oddLock) {
                    try {
                        this.oddLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            println(currentThread().getName() + ": " + oddCount);
            oddCount += 2;
            this.nextState = 0;
            synchronized (this.evenLock) {
                this.evenLock.notify();
            }
        }
    }

    @Override
    void printEven() {
        var evenCount = 2;
        while (evenCount <= TOTAL) {
            while (this.nextState != 0) {
                synchronized (this.evenLock) {
                    try {
                        this.evenLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            println(currentThread().getName() + ": " + evenCount);
            evenCount += 2;
            this.nextState = 1;
            synchronized (this.oddLock) {
                this.oddLock.notify();
            }
        }
    }
}
