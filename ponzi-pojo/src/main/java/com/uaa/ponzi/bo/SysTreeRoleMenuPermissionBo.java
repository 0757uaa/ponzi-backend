package com.uaa.ponzi.bo;

import lombok.Data;

import java.util.List;

@Data
public class SysTreeRoleMenuPermissionBo {

    private String id;

    private String menuName;

    private String roleId;

    private Boolean checkAll = false;

    private Boolean indeterminate = false;

    private List<SysTreeRoleMenuPermissionBo> children;

    private List<SysPermissionBo> checkedPermissions;

    private List<SysPermissionBo> permissions;
}
