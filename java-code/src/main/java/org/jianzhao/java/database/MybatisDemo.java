package org.jianzhao.java.database;

import org.jianzhao.java.database.mybatis.configuration.MyBatisConfiguration;
import org.jianzhao.java.database.mybatis.mapper.UserMapper;
import org.jianzhao.sugar.Sugar;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MybatisDemo {

    public static void main(String... args) {
        var context = new AnnotationConfigApplicationContext(MyBatisConfiguration.class);
        var template = context.getBean(SqlSessionTemplate.class);
        var mapper = template.getMapper(UserMapper.class);
        mapper.searchByAge(12).forEach(Sugar::println);
    }
}
