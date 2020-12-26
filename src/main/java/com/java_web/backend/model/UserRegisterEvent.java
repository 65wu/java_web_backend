package com.java_web.backend.model;

import javax.validation.constraints.NotNull;

public class UserRegisterEvent extends User {
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "昵称不能为空")
    private String name;
    @NotNull(message = "邮箱不能为空")
    private String email;
}
