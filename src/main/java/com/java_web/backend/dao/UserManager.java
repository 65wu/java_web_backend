package com.java_web.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserManager {
    @Update("update user set password=#{password} where username=#{username}")
    void updateUserPassword(
            @Param("password") String password,
            @Param("username") Integer username
    );
    @Update("update user set name=#{name}, email=#{email} where username=#{username}")
    void updateUserBasic(
            @Param("password") String name,
            @Param("password") String email,
            @Param("username") Integer username
    );
    @Select("select username from user where name=#{name}")
    Integer findIdByName(
            @Param("name") String name
    );
}
