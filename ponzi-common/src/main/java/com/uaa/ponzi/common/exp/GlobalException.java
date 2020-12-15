package com.uaa.ponzi.common.exp;

// 通用的异常
public class GlobalException extends RuntimeException {

    private String message;

    public GlobalException(String message) {
        super();
        this.message = message;
    }
}
