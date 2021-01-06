package com.java_web.backend.model.dto;

import javax.validation.constraints.NotNull;

public class UserEditBasicTransfer {
    @NotNull
    private String username;
    @NotNull
    private String email;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
