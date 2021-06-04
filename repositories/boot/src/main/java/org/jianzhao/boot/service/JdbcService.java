package org.jianzhao.boot.service;

import lombok.NonNull;
import org.jianzhao.boot.model.JdbcQuery;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class JdbcService {

    /**
     * 执行SQL查询
     */
    public List<Map<String, Object>> applyQuery(@NonNull JdbcQuery jdbcQuery) {
        var sql = jdbcQuery.getSql();
        var jdbcTemplate = this.createJdbcTemplate(jdbcQuery);
        return jdbcTemplate.query(sql, new ColumnMapRowMapper());
    }

    /**
     * 创建JdbcTemplate
     */
    private NamedParameterJdbcTemplate createJdbcTemplate(JdbcQuery jdbcQuery) {
        var datasource = this.createDatasource(jdbcQuery);
        return new NamedParameterJdbcTemplate(datasource);
    }

    /**
     * 创建Datasource
     */
    private DataSource createDatasource(JdbcQuery jdbcQuery) {
        var dataSource = new SingleConnectionDataSource();
        dataSource.setUrl(jdbcQuery.getUrl());
        dataSource.setDriverClassName(jdbcQuery.getDriver());
        dataSource.setUsername(jdbcQuery.getUsername());

        var password = Objects.requireNonNullElse(jdbcQuery.getPassword(), "");
        dataSource.setPassword(password.toString());
        return dataSource;
    }

}
