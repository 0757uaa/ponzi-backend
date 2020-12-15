package com.uaa.ponzi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uaa.ponzi.pojo.SysUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 清除用户的所有角色
     * @param userId 用户id
     * @return
     */
    int clearUserRoles(String userId);


    /**
     * 添加或修改用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    int addOrUpdateUserRole(String userId, List<String> roleIds);

}
