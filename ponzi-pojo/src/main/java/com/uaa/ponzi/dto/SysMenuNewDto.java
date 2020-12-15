package com.uaa.ponzi.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SysMenuNewDto {

    @NotEmpty(message = "请填写菜单名称")
    private String menuName;
    @NotEmpty(message = "请选择父级菜单")
    private String pid;

    private String menuIcon;

    private String routePath;

    private String routeName;

    private String routeComponent;

    private Integer orders;
}
