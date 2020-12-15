package com.uaa.ponzi.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uaa.ponzi.base.pojo.IdEntity;
import lombok.Data;

@Data
@TableName("sys_role_menu")
public class SysRoleMenu extends IdEntity {

    private String sysRoleId;

    private String sysMenuId;
}
