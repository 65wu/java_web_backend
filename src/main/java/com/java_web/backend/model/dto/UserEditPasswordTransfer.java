package com.java_web.backend.model.dto;

import javax.validation.constraints.NotNull;

public class UserEditPasswordTransfer {
    @NotNull
    private Integer username;
    @NotNull
    private String password;

    public Integer getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
