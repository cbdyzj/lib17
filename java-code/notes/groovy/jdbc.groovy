#!/usr/bin/env groovy
@Grab('mysql:mysql-connector-java:8.0.27')
@Grab('org.springframework:spring-jdbc:5.3.13')
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.ColumnMapRowMapper
import org.springframework.jdbc.datasource.SingleConnectionDataSource

def createDatasource() {
    var dataSource = new SingleConnectionDataSource()
    dataSource.setUrl('jdbc:mysql://localhost:3306')
    dataSource.setDriverClassName('com.mysql.cj.jdbc.Driver')
    dataSource.setUsername('root')
    dataSource.setPassword('password')
    return dataSource
}

var jdbcTemplate = new NamedParameterJdbcTemplate(createDatasource())

var sql = 'SELECT NOW() AS now;'

var now = jdbcTemplate.query(sql, new ColumnMapRowMapper())

println(now[0]['now'])
