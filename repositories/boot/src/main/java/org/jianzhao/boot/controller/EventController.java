package org.jianzhao.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Api(tags = "事件观察")
@RequestMapping("/event")
@RestController
@RequiredArgsConstructor
public class EventController {

    @NonNull
    private final ApplicationEventPublisher publisher;

    @GetMapping("/local")
    public ResponseEntity<?> publishLocalEvent(@RequestParam(name = "event", required = false) String event) {
        String s = Objects.requireNonNullElse(event, "DEFAULT");
        this.publisher.publishEvent(s);
        return ResponseEntity.ok(s);
    }
}
