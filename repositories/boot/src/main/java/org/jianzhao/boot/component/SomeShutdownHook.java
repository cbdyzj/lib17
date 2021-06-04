package org.jianzhao.boot.component;

import lombok.extern.slf4j.Slf4j;
import org.jianzhao.boot.Application;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SomeShutdownHook {

    public void hook() {
        var context = Application.context();
        log.info("Shutting down {} ...", context.getDisplayName());
    }
}
