package org.jianzhao.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jianzhao.boot.model.TreeNode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "开始吧")
@Slf4j
@CrossOrigin
@RestController
public class BootController {

    @GetMapping("/ping")
    public ResponseEntity<?> echo() {
        return ResponseEntity.ok("pong\n");
    }

    @GetMapping("/session")
    public ResponseEntity<?> session(HttpSession session) {
        var now = (Instant) session.getAttribute("now");
        if (now == null) {
            now = Instant.now();
            session.setAttribute("now", now);
        }
        return ResponseEntity.ok(now.toString());
    }

    @ApiOperation("Cookie")
    @GetMapping("/cookie")
    public ResponseEntity<?> cookie(HttpServletRequest request, HttpServletResponse response) {
        var cookies = request.getCookies();
        if (ObjectUtils.isEmpty(cookies)) {
            var cookie = new Cookie("foo", "bar");
            response.addCookie(cookie);
        }
        return ResponseEntity.ok("Cookie");
    }

    @PostMapping("/serialize")
    public ResponseEntity<?> serialize(@RequestBody Map<String, Object> body) {
        log.info(body.toString());
        var result = new HashMap<String, Object>();
        result.put("date", new Date());
        result.put("instant", Instant.now());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/serialize2")
    public ResponseEntity<?> serialize3(@RequestBody TreeNode node) {
        log.info(node.toString());
        return ResponseEntity.ok(node);
    }

    @GetMapping("/serialize3")
    public ResponseEntity<?> serialize4(@RequestParam("id") String[] ids) {
        log.info(Arrays.toString(ids));
        return ResponseEntity.ok(ids);
    }

}
