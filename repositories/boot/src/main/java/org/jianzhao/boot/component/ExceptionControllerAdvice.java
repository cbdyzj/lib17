package org.jianzhao.boot.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice("org.jianzhao.boot.controller")
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        var message = e.getMessage();
        if (ObjectUtils.isEmpty(message)) {
            message = e.getClass().getSimpleName();
        }
        log.debug(message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
