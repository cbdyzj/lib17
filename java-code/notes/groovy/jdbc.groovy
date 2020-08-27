#!/usr/bin/env groovy
@Grab('mysql:mysql-connector-java:8.0.21')
@Grab('org.springframework:spring-jdbc:5.2.8.RELEASE')
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.ColumnMapRowMapper
import org.springframework.jdbc.datasource.SingleConnectionDataSource


static def createDatasource() {
    def dataSource = new SingleConnectionDataSource()
    dataSource.setUrl('jdbc:mysql://localhost:3306')
    dataSource.setDriverClassName('com.mysql.cj.jdbc.Driver')
    dataSource.setUsername('root')
    dataSource.setPassword('password');
    return dataSource;
}

def jdbcTemplate = new NamedParameterJdbcTemplate(createDatasource())

def sql = 'SELECT NOW() AS now;'

def result = jdbcTemplate.query(sql, new ColumnMapRowMapper());

print(result.get(0).get('now'))
