package com.java_web.backend.model.dto.User;

import javax.validation.constraints.NotNull;

public class EditBasicTransfer {
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
