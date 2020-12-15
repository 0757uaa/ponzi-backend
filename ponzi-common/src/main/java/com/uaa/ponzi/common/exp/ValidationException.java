package com.uaa.ponzi.common.exp;

import lombok.Data;

// 验证错误信息异常
@Data
public class ValidationException extends RuntimeException {

    private String message;

    public ValidationException(String message) {
        super();
        this.message = message;
    }
}
