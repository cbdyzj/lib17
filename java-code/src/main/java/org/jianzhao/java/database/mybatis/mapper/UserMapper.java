package org.jianzhao.java.database.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jianzhao.java.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> searchByAge(@Param("age") Integer age);

    User findOne();

    List<User> findAll();

    List<User> findByName(@Param("name") String name);

}
