package com.java_web.backend.model.dto;

import javax.validation.constraints.NotNull;

public class UserEditBasicTransfer {
    @NotNull
    private String nickname;
    @NotNull
    private String email;

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
