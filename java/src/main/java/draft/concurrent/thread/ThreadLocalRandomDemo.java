package draft.concurrent.thread;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.jianzhao.sugar.Sugar.println;

public class ThreadLocalRandomDemo {

    public static void main(String... args) {
        var num = Stream.generate(ThreadLocalRandom.current()::nextGaussian)
                .limit(10)
                .map(String::valueOf)
                .distinct()
                .count();
        println(num);
    }

}
