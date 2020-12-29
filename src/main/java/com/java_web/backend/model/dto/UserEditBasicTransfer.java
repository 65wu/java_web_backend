package com.java_web.backend.model.dto;

import javax.validation.constraints.NotNull;

public class UserEditBasicTransfer {
    @NotNull
    private Integer username;
    @NotNull
    private String name;
    @NotNull
    private String email;

    public Integer getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
