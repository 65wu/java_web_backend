package com.java_web.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserManager {
    @Update("update user set password=#{password} where user_id=#{userId}")
    void updateUserPassword(
            @Param("password") String password,
            @Param("userId") Integer userId
    );
    @Update("update user set username=#{username}, email=#{email}, nickname=#{nickname} where user_id=#{userId}")
    void updateUserBasic(
            @Param("password") String username,
            @Param("password") String email,
            @Param("nickname") String nickname,
            @Param("userId") Integer userId
    );
    @Select("select user_id from user where username=#{username}")
    Integer findIdByUsername(
            @Param("username") String username
    );
}
