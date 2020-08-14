package org.jianzhao.java.database;

import java.sql.SQLException;

import static org.jianzhao.sugar.Sugar.println;

public class JdbcDemo {

    public static void main(String... args) throws SQLException {
        var ds = DataSourceHolder.get();
        var sql = "SELECT NOW() AS now";
        var c = ds.getConnection();
        try (c) {
            var rs = c.createStatement().executeQuery(sql);
            while (rs.next()) {
                println(rs.getString("now"));
            }
        }
    }
}
