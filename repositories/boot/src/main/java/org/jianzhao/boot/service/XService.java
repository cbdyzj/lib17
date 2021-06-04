package org.jianzhao.boot.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jianzhao.boot.support.cache.ContextCache;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class XService {

    public static final String SCRIPTS_DIR = "scripts";

    /**
     * Thread sleep
     */
    @SneakyThrows
    public Map<String, String> sleep(Long millis) {
        Thread.sleep(millis);
        var thread = Thread.currentThread().getName();
        var time = String.valueOf(millis);
        return Map.of("thread", thread, "time", time);
    }

    /**
     * 执行本地脚本
     */
    @SneakyThrows
    public InputStream script(String name) {
        var cmd = new String[]{"sh", name};
        var dir = Paths.get(SCRIPTS_DIR).toFile();
        if (!dir.exists()) {
            var message = "Directory '" + SCRIPTS_DIR + "' not exists";
            return new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        }
        var process = new ProcessBuilder(cmd)
                .directory(dir)
                .redirectErrorStream(true)
                .start();
        return process.getInputStream();
    }

    @ContextCache
    @SneakyThrows
    public String getX() {
        Thread.sleep(3000);
        log.info("Apply XService#getX");
        return "X";
    }

    /**
     * Bubble sort
     */
    public void bubbleSort(int[] array) {
        Objects.requireNonNull(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 交换位置
     */
    private static void swap(int[] a, int i, int j) {
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }
}
