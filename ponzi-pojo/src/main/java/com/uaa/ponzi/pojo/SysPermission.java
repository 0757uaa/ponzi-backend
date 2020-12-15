package com.uaa.ponzi.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uaa.ponzi.base.pojo.IdEntity;
import lombok.Data;

import java.util.List;

@Data
@TableName("sys_permission")
public class SysPermission extends IdEntity {

    private String permissionName;

    private String permissionCode;

    private Integer permissionType;

    private String sysMenuId;

}
