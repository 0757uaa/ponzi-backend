package com.uaa.ponzi.bo;

import lombok.Data;

import java.util.List;

@Data
public class SysTreeMenuBo {

    private String id;

    private String menuName;

    private String pid;

    private String menuIcon;

    private String routePath;

    private String routeName;

    private String routeComponent;

    private Integer orders;

    private String roleId;

    private String menuTitle;

    private String userId;

    private List<SysTreeMenuBo> children;

}
