package org.jianzhao.java.database.sharding;

import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ShardingDataSourceHolder {

    public static DataSource get() throws SQLException {
        var configuration = new ShardingRuleConfiguration();
        configuration.getTableRuleConfigs().add(getProductOrderTableRuleConfiguration());
        var shardingStrategy = new InlineShardingStrategyConfiguration("id", "test${(id + 1) % 2}");
        configuration.setDefaultDatabaseShardingStrategyConfig(shardingStrategy);
        return ShardingDataSourceFactory.createDataSource(getDataSourceMap(), configuration, null);
    }

    private static TableRuleConfiguration getProductOrderTableRuleConfiguration() {
        return new TableRuleConfiguration("product_order");
    }

    private static Map<String, DataSource> getDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("test0", getTest0Datasource());
        result.put("test1", getTest1Datasource());
        return result;
    }

    private static DataSource getTest0Datasource() {
        var ds = new SingleConnectionDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test0?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("password");
        ds.setSuppressClose(true);
        return ds;
    }

    private static DataSource getTest1Datasource() {
        var ds = new SingleConnectionDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test1?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("password");
        ds.setSuppressClose(true);
        return ds;
    }
}
