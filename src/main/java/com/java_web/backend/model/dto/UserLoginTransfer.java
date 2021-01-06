package com.java_web.backend.model.dto;

import javax.validation.constraints.NotNull;

public class UserLoginTransfer {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
