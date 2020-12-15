package com.uaa.ponzi.dto;

import com.uaa.ponzi.common.enums.SexEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SysUserNewDto {

    @NotEmpty(message = "请填写用户名")
    private String username;

    @NotEmpty(message = "请填写密码")
    private String dencryptPassword;

//    @NotEmpty(message = "请选择性别")
    private SexEnum sex;

    @NotEmpty(message = "请选择角色")
    private String roleId;

}
