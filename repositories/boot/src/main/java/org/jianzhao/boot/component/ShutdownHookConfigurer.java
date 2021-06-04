package org.jianzhao.boot.component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ShutdownHookConfigurer implements CommandLineRunner {

    @NonNull
    private final SomeShutdownHook someShutdownHook;

    @Override
    public void run(String... args) {
        var runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(this.someShutdownHook::hook));
        log.info("Shutdown hook configured");
    }
}
