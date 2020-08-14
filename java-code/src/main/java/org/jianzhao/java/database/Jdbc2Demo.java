package org.jianzhao.java.database;

import org.jianzhao.java.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;

import static org.jianzhao.sugar.Sugar.println;

public class Jdbc2Demo {

    public static void main(String[] args) {
        var jdbcTemplate = new NamedParameterJdbcTemplate(DataSourceHolder.get());
        var rowMapper = new BeanPropertyRowMapper<>(User.class);
        var sql = """
                SELECT id, name, age, created_at, updated_at 
                FROM user
                WHERE name = :name;
                """;
        var paramMap = Map.of("name", "ada");
        var userList = jdbcTemplate.query(sql, paramMap, rowMapper);
        userList.forEach(it -> println(it.toString()));
    }
}
