package com.java_web.backend.model.dto;

import javax.validation.constraints.NotNull;

public class UserRegisterTransfer {
    @NotNull
    private String password;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String nickname;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
