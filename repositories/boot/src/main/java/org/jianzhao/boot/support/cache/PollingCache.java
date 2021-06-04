package org.jianzhao.boot.support.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class PollingCache<K, V> {

    private final Map<K, V> cache = new ConcurrentHashMap<>();
    private final long interval;
    private final Consumer<Map<K, V>> scheduler;

    private boolean running = false;

    /**
     * @param interval  调度间隔，毫秒
     * @param scheduler 定时任务
     */
    public PollingCache(long interval, Consumer<Map<K, V>> scheduler) {
        this.interval = interval;
        this.scheduler = scheduler;
    }

    @SuppressWarnings("BusyWait")
    private void loop() {
        while (this.running) {
            this.scheduler.accept(this.cache);
            try {
                Thread.sleep(this.interval);
            } catch (InterruptedException ignore) {
                this.running = false;
            }
        }
    }

    public void start() {
        this.running = true;
        new Thread(this::loop).start();
    }

    public void stop() {
        this.running = false;
    }

    public V getValue(K key) {
        return this.cache.get(key);
    }
}
