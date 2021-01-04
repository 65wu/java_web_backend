package com.java_web.backend.model.dto;

import javax.validation.constraints.NotNull;

public class UserLoginTransfer {
    @NotNull
    private String name;
    @NotNull
    private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
