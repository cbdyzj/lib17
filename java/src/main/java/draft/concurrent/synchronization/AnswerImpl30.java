package draft.concurrent.synchronization;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.jianzhao.sugar.Sugar.println;

/**
 * 使用{@link BlockingQueue}
 */
class AnswerImpl30 extends Answer {

    private final BlockingQueue<Object> oddQueue = new ArrayBlockingQueue<>(1);
    private final BlockingQueue<Object> evenQueue = new ArrayBlockingQueue<>(1);

    AnswerImpl30() throws InterruptedException {
        this.oddQueue.put(new Object());
    }

    @Override
    void printOdd() {
        try {
            var oddCount = 1;
            while (oddCount < TOTAL) {
                Object o = this.oddQueue.take();
                println(oddCount);
                oddCount += 2;
                this.evenQueue.put(o);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    void printEven() {
        try {
            var evenCount = 2;
            while (evenCount <= TOTAL) {
                Object o = this.evenQueue.take();
                println(evenCount);
                evenCount += 2;
                this.oddQueue.put(o);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
