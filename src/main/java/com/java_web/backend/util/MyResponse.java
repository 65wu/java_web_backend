package com.java_web.backend.util;

public class MyResponse {
    private final int code;
    private final String message;
    private final Object data;

    public MyResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public MyResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
