package com.uaa.ponzi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uaa.ponzi.mapper.SysUserRoleMapper;
import com.uaa.ponzi.pojo.SysUserRole;
import com.uaa.ponzi.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int clearUserRoles(String userId) {
        UpdateWrapper<SysUserRole> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("sys_user_id", userId);
        return sysUserRoleMapper.delete(updateWrapper);
    }

    @Override
    public int addOrUpdateUserRole(String userId, List<String> roleIds) {
        this.clearUserRoles(userId);
        int rows = 0;
        for (String roleId : roleIds) {
            SysUserRole userRole = new SysUserRole();
            userRole.setSysRoleId(roleId);
            userRole.setSysUserId(userId);
            sysUserRoleMapper.insert(userRole);
            rows++;
        }
        return rows;
    }
}
