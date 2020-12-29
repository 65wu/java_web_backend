package com.java_web.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserManager {
    @Update("update user set password=#{password} where username=#{username}")
    void updateUserPassword(
            @Param("password") String password,
            @Param("username") Integer username
    );
}
