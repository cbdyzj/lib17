package org.jianzhao.java.database;

import org.jianzhao.sugar.Sugar;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.SQLException;

public class JooqDemo {

    public static void main(String... args) throws SQLException {
        var ds = DataSourceHolder.get();
        var c = ds.getConnection();
        DSL.using(c, SQLDialect.MYSQL)
                .select(DSL.field("name"), DSL.field("age"))
                .from("user")
                .limit(10)
                .fetch()
                .map(Record::formatJSON)
                .forEach(Sugar::println);
    }
}
