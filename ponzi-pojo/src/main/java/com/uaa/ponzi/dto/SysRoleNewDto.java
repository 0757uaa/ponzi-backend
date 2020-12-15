package com.uaa.ponzi.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uaa.ponzi.base.pojo.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */

@Data
public class SysRoleNewDto {


    /**
     * 角色代码
     */
    @NotEmpty(message = "请填写角色编号")
    private String roleCode;

    /**
     * 角色名称
     */
    @NotEmpty(message = "请填写角色名称")
    private String roleName;

}
