package com.java_web.backend.dao.User;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserManager {
    @Update("UPDATE user SET password=#{password} WHERE user_id=#{userId}")
    void updateUserPassword(
            @Param("password") String password,
            @Param("userId") Integer userId
    );
    @Update("UPDATE user SET email=#{email}, nickname=#{nickname} WHERE user_id=#{userId}")
    void updateUserBasic(
            @Param("email") String email,
            @Param("nickname") String nickname,
            @Param("userId") Integer userId
    );
    @Select("SELECT user_id FROM user WHERE username=#{username}")
    Integer findIdByUsername(
            @Param("username") String username
    );
}
