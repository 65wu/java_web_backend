package com.java_web.backend.util;

public class MyResponse {
    private int code;
    private String message;
    private Object data;

    public static MyResponse success() {
        MyResponse myResponse = new MyResponse();
        myResponse.setResultCode(ResultCode.SUCCESS);
        return myResponse;
    }

    public static MyResponse success(Object data) {
        MyResponse myResponse = new MyResponse();
        myResponse.setResultCode(ResultCode.SUCCESS);
        myResponse.setData(data);
        return myResponse;
    }

    public static MyResponse fail() {
        MyResponse MyResponse = new MyResponse();
        MyResponse.setResultCode(ResultCode.FAIL);
        return MyResponse;
    }

    private void setResultCode(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
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

    public void setData(Object data) {
        this.data = data;
    }
}
