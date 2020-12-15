package com.uaa.ponzi.dto;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class SysPermissionNewDto {

    @NotEmpty(message = "请填写权限名称")
    private String permissionName;
    @NotEmpty(message = "请填写权限代码")
    private String permissionCode;
    @Min(value = -1, message = "请选择权限类型")
    private Integer permissionType;
    @NotEmpty(message = "请选择所属菜单")
    private String sysMenuId;


}
