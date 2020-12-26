package com.java_web.backend.model.dto;

import javax.validation.constraints.NotNull;

public class UserRegisterTransfer {
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String email;

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
