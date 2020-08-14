package org.jianzhao.java.disruptor;

import com.lmax.disruptor.dsl.Disruptor;
import org.jianzhao.java.object.ObjectHandle;

import java.time.Instant;
import java.util.concurrent.ThreadFactory;

import static org.jianzhao.sugar.Sugar.printf;

public class DisruptorDemo {

    public static void main(String[] args) {
        int bufferSize = 1024;
        var disruptor = new Disruptor<>(ObjectHandle<String>::new, bufferSize, (ThreadFactory) (Thread::new));
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> printf("%s %s %s\n", event, sequence, endOfBatch));
        disruptor.start();
        var ringBuffer = disruptor.getRingBuffer();
        int count = 100000;
        while (count-- > 0) {
            ringBuffer.publishEvent(((event, sequence) -> event.set(Instant.now().toString())));
        }
        disruptor.shutdown();
    }

}
