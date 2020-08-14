package org.jianzhao.java.database.sharding;

import java.sql.SQLException;

import static org.jianzhao.sugar.Sugar.println;

public class ShardingDemo {

    public static void main(String[] args) throws SQLException {
        var ds = ShardingDataSourceHolder.get();
        var sql = "SELECT id, description FROM product_order";
        var c = ds.getConnection();
        try (c) {
            var rs = c.createStatement().executeQuery(sql);
            while (rs.next()) {
                var id = rs.getString("id");
                var description = rs.getString("description");
                String s = String.format("%s %s", id, description);
                println(s);
            }
        }
    }
}
