package org.jianzhao.java.json;

import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Data
public class Now {

    public Now() {
        var now = Instant.now();
        this.instant = now;
        this.timestamp = Timestamp.from(now);
        this.date = Date.from(now);
    }

    private Timestamp timestamp;
    private Date date;
    private Instant instant;
}
