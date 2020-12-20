package com.java_web.backend.util;

public enum ResultCode {
    SUCCESS(0, "Request is successful"),
    FAIL(1, "Request is failed"),
    FAIL_CREATE(2, "Create failed"),
    FAIL_UPDATE(3, "Update failed"),
    FAIL_DELETE(4, "Delete failed");

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
