package org.jianzhao.boot.model;

import lombok.Data;

@Data
public class JdbcQuery {

    private String sql;

    private String url;
    private String driver;
    private String username;
    private CharSequence password;
}
