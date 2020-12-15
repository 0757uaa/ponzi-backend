package com.uaa.ponzi.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uaa.ponzi.base.pojo.IdEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
@Data
@TableName("sys_user_role")
public class SysUserRole extends IdEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色代码
     */
    private String sysUserId;

    /**
     * 角色名称
     */
    private String sysRoleId;

}
