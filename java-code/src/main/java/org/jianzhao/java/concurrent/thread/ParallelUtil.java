package org.jianzhao.java.concurrent.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelUtil {

    public static <T> List<T> parallelExecute(List<Callable<T>> tasks) throws ExecutionException, InterruptedException {
        var threads = Runtime.getRuntime().availableProcessors() * 2;
        var executor = Executors.newFixedThreadPool(threads);
        List<T> results = parallelExecute(tasks, executor);
        executor.shutdown();
        return results;
    }

    public static <T> List<T> parallelExecute(List<Callable<T>> tasks, ExecutorService executor) throws ExecutionException, InterruptedException {
        List<Future<T>> futures = new ArrayList<>();
        for (var task : tasks) {
            Future<T> submit = executor.submit(task);
            futures.add(submit);
        }
        List<T> results = new ArrayList<>();
        for (var future : futures) {
            results.add(future.get());
        }
        return results;
    }
}
