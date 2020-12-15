package com.uaa.ponzi.bo;

import com.uaa.ponzi.pojo.SysPermission;
import lombok.Data;

import java.util.List;


@Data
public class SysRolePermissionBo {

    private String id;
    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    private List<SysPermission> sysPermissionList;

}
