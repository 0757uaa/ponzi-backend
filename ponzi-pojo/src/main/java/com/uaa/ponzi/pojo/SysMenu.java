package com.uaa.ponzi.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uaa.ponzi.base.pojo.BaseEntity;
import lombok.Data;

@Data
@TableName("sys_menu")
public class SysMenu extends BaseEntity {

    private String menuName;

    private String pid;

    private String menuIcon;

    private String routePath;

    private String routeName;

    private String routeComponent;

    private Integer orders;

    private String menuTitle;

}
