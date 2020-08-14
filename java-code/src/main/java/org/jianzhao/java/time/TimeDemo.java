package org.jianzhao.java.time;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import static org.jianzhao.sugar.Sugar.println;

public class TimeDemo {

    public static void main(String[] args) {
        var now = Instant.now();
        println(now);
        println(Timestamp.from(now).toInstant());
        println(new Timestamp(Date.from(now).getTime()).toInstant());
        println(Date.from(now).toInstant());
    }
}
