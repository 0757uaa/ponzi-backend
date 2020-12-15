package com.uaa.ponzi.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uaa.ponzi.base.pojo.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */

@Data
@TableName("sys_role")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

}
