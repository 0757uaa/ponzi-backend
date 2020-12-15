package com.uaa.ponzi.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SysUserLoginDto {

    @NotEmpty(message = "请填写用户名")
    private String username;

    @NotEmpty(message = "请填写密码")
    private String password;

}
