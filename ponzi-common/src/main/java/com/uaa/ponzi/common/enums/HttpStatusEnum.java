package com.uaa.ponzi.common.enums;

/**
 * 自定义的HttpStatus状态码,为org.springframework.http.HttpStatus的补充
 */
public enum HttpStatusEnum {

    LOGIN_TIMEOVER(4000, "未登录或登录超时"),

    NO_PERMISSION(4001, "权限不足");

    private final int value;
    private final String reasonPhrase;

    HttpStatusEnum(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
