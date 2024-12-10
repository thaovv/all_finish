package com.huce.quiz_app.Exception;

public enum ErrorCode {
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXISTED(1002,"User existed"),
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized exception"),
    USERNAME_INVALID(1003,"Username must be at least 3 characters"),
    INVALID_PASSWORD(1004,"Password must be at least 8 characters"),
    USER_NOT_EXISTED(1005,"User not existed"),
    UNAUTHENTICATED(1006,"Unauthenticated"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}