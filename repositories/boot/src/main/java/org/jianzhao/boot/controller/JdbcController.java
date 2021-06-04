package org.jianzhao.boot.controller;

import io.swagger.annotations.Api;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jianzhao.boot.model.JdbcQuery;
import org.jianzhao.boot.service.JdbcService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "JDBC")
@RestController
@RequestMapping("/jdbc")
@RequiredArgsConstructor
public class JdbcController {

    @NonNull
    private final JdbcService jdbcService;

    @PostMapping(path = "/query", consumes = "application/json")
    public ResponseEntity<?> applyQuery(@RequestBody JdbcQuery jdbcQuery) {
        var result = this.applyQueryAndGetResult(jdbcQuery);
        return ResponseEntity.ok(result);
    }

    private Object applyQueryAndGetResult(JdbcQuery jdbcQuery) {
        try {
            return this.jdbcService.applyQuery(jdbcQuery);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
