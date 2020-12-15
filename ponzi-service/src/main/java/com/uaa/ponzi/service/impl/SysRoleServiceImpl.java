package com.uaa.ponzi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uaa.ponzi.bo.SysRolePermissionBo;
import com.uaa.ponzi.bo.SysTreeMenuBo;
import com.uaa.ponzi.bo.SysTreeRoleMenuPermissionBo;
import com.uaa.ponzi.common.enums.DeletedEnum;
import com.uaa.ponzi.common.enums.DisabledEnum;
import com.uaa.ponzi.mapper.*;
import com.uaa.ponzi.pojo.SysRole;
import com.uaa.ponzi.pojo.SysRoleMenu;
import com.uaa.ponzi.pojo.SysRolePermission;
import com.uaa.ponzi.pojo.SysUserRole;
import com.uaa.ponzi.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
@Service
@Transactional(readOnly = true)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private SysRolePermissionBoMapper sysRolePermissionBOMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private SysTreeRoleMenuPermissionBoMapper sysTreeRoleMenuPermissionBoMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysRole> allRoles() {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeletedEnum.NO.getValue());
        queryWrapper.eq("disabled", DisabledEnum.NO.getValue());
        return sysRoleMapper.selectList(queryWrapper);
    }


    @Override
    public SysRolePermissionBo findRolePermissionByRoleId(String roleId) {
        return sysRolePermissionBOMapper.selectByRoleId(roleId);
    }


    @Transactional(readOnly = false)
    @Override
    public int addOrUpdateRolePermission(String roleId, List<String> permissionIds) {
        UpdateWrapper<SysRolePermission> rolePermissionUpdateWrapper = new UpdateWrapper<>();
        rolePermissionUpdateWrapper.eq("sys_role_id", roleId);
        sysRolePermissionMapper.delete(rolePermissionUpdateWrapper);

        int row = 0;
        if (!CollectionUtils.isEmpty(permissionIds)) {
            for (String pid : permissionIds) {
                SysRolePermission rolePermission = new SysRolePermission();
                rolePermission.setSysPermissionId(pid);
                rolePermission.setSysRoleId(roleId);
                row += sysRolePermissionMapper.insert(rolePermission);
            }
        }
        return row;
    }

    @Override
    public boolean checkExistsRoleCode(String roleCode) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_code", roleCode);
        return (null != sysRoleMapper.selectOne(queryWrapper));
    }

    @Override
    public boolean checkExistsRoleName(String roleName) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", roleName);
        return (null != sysRoleMapper.selectOne(queryWrapper));
    }

    @Override
    public int clearRolePermissions(String roleId) {
        UpdateWrapper<SysRolePermission> rolePermissionUpdateWrapper = new UpdateWrapper<>();
        rolePermissionUpdateWrapper.eq("sys_role_id", roleId);
        return sysRolePermissionMapper.delete(rolePermissionUpdateWrapper);
    }

    @Override
    public List<SysTreeMenuBo> treeRoleMenuByRoleId(String roleId) {
        return menuMapper.treeRoleMenuByRoleId(roleId);
    }

    @Override
    public List<SysTreeMenuBo> treeRoleMenuByUserId(String userId) {
        return menuMapper.treeRoleMenuByUserId(userId);
    }

    @Override
    public List<SysTreeRoleMenuPermissionBo> treeRoleMenuPermissionByRoleId(String roleId) {
        return sysTreeRoleMenuPermissionBoMapper.treeRoleMenuPermission(roleId);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdateRoleMenuPermission(String roleId, List<String> menuIdList, List<String> permissionIdList) {
        UpdateWrapper<SysRoleMenu> deleteRoleMenuWrapper = new UpdateWrapper<>();
        deleteRoleMenuWrapper.eq("sys_role_id", roleId);
        roleMenuMapper.delete(deleteRoleMenuWrapper);

        if (!CollectionUtils.isEmpty(menuIdList)) {
            for (String menuId : menuIdList) {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setSysMenuId(menuId);
                roleMenu.setSysRoleId(roleId);
                roleMenuMapper.insert(roleMenu);
            }
        }

        UpdateWrapper<SysRolePermission> rolePermissionUpdateWrapper = new UpdateWrapper<>();
        rolePermissionUpdateWrapper.eq("sys_role_id", roleId);
        sysRolePermissionMapper.delete(rolePermissionUpdateWrapper);

        if (!CollectionUtils.isEmpty(permissionIdList)) {
            for (String pid : permissionIdList) {
                SysRolePermission rolePermission = new SysRolePermission();
                rolePermission.setSysPermissionId(pid);
                rolePermission.setSysRoleId(roleId);
                sysRolePermissionMapper.insert(rolePermission);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteRole(String roleId) {
        UpdateWrapper<SysRolePermission> rolePermissionUpdateWrapper = new UpdateWrapper<>();
        rolePermissionUpdateWrapper.eq("sys_role_id", roleId);
        sysRolePermissionMapper.delete(rolePermissionUpdateWrapper);

        UpdateWrapper<SysRoleMenu> roleMenuUpdateWrapper = new UpdateWrapper<>();
        roleMenuUpdateWrapper.eq("sys_role_id", roleId);
        roleMenuMapper.delete(roleMenuUpdateWrapper);

        UpdateWrapper<SysUserRole> userRoleUpdateWrapper = new UpdateWrapper<>();
        userRoleUpdateWrapper.eq("sys_role_id", roleId);
        userRoleMapper.delete(userRoleUpdateWrapper);

        sysRoleMapper.deleteById(roleId);
    }
}
