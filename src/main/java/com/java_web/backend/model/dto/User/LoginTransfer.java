package com.java_web.backend.model.dto.User;

import javax.validation.constraints.NotNull;

public class LoginTransfer {
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
