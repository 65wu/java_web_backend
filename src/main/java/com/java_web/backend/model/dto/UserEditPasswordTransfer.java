package com.java_web.backend.model.dto;

import javax.validation.constraints.NotNull;

public class UserEditPasswordTransfer {
    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }
}
