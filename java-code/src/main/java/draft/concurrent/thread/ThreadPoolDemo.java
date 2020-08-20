package draft.concurrent.thread;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static org.jianzhao.sugar.Sugar.println;

public class ThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var ints = new Random().ints(100).toArray();
        var cs = new ArrayList<Callable<Integer>>();
        for (int i : ints) {
            cs.add(() -> {
                Thread.sleep(3000);
                return i;
            });
        }
        var startTime = System.nanoTime();
        var results = ParallelUtil.parallelExecute(cs);
        var duration = Duration.ofNanos(System.nanoTime() - startTime);
        println(duration);
        println(results);
    }
}
