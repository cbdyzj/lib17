package org.jianzhao.boot.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LocalEventHandler {

    @EventListener
    public void handle(String event) {
        log.info("Handle Spring event: {}", event);
    }
}
