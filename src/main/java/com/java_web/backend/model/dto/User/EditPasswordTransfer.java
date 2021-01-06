package com.java_web.backend.model.dto.User;

import javax.validation.constraints.NotNull;

public class EditPasswordTransfer {
    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }
}
