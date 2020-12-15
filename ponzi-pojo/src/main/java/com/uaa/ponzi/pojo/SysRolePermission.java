package com.uaa.ponzi.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uaa.ponzi.base.pojo.IdEntity;
import lombok.Data;

@Data
@TableName("sys_role_permission")
public class SysRolePermission extends IdEntity {

    private String sysPermissionId;

    private String sysRoleId;


}
