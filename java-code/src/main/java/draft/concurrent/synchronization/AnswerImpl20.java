package draft.concurrent.synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;
import static org.jianzhao.sugar.Sugar.println;

/**
 * 使用{@link ReentrantLock}与{@link Condition}
 */
class AnswerImpl20 extends Answer {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = this.lock.newCondition();

    @Override
    void printOdd() {
        this.print(1);
    }

    @Override
    void printEven() {
        this.print(2);
    }

    private void print(int count) {
        while (count <= TOTAL) {
            this.lock.lock();
            try {
                this.condition.signal();
                println(currentThread().getName() + ": " + count);
                count += 2;
                this.condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.lock.unlock();
            }
        }

    }
}
