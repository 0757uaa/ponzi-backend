package com.uaa.ponzi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uaa.ponzi.bo.SysPermissionBo;
import com.uaa.ponzi.enums.SysPermissionTypeEnum;
import com.uaa.ponzi.mapper.SysPermissionMapper;
import com.uaa.ponzi.mapper.SysRolePermissionMapper;
import com.uaa.ponzi.pojo.SysPermission;
import com.uaa.ponzi.pojo.SysRolePermission;
import com.uaa.ponzi.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;


    @Override
    public boolean checkExistsPermissionCode(String permissionCode) {
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("permission_code", permissionCode);
        return (null != sysPermissionMapper.selectOne(queryWrapper));
    }

    @Override
    public List<SysPermission> findByMenuId(String menuId) {
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sys_menu_id", menuId);
        return sysPermissionMapper.selectList(queryWrapper);
    }

    @Override
    public int deletePermission(String permissionId, String menuId) {
        UpdateWrapper<SysRolePermission> rolePermissionDelete = new UpdateWrapper<>();
        rolePermissionDelete.eq("sys_permission_id", permissionId);
        sysRolePermissionMapper.delete(rolePermissionDelete);

        UpdateWrapper<SysPermission> deleteWrapper = new UpdateWrapper<>();
        deleteWrapper.eq("id", permissionId);
        deleteWrapper.eq("sys_menu_id", menuId);
        return sysPermissionMapper.delete(deleteWrapper);
    }

    @Override
    public List<SysPermissionBo> findByUserIdAndPermissionType(String userId, SysPermissionTypeEnum permissionTypeEnum) {
        return sysPermissionMapper.findByUserIdAndPermissionType(userId, permissionTypeEnum.getValue());
    }

}
