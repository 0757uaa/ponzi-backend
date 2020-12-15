package com.uaa.ponzi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uaa.ponzi.mapper.SysRoleMenuMapper;
import com.uaa.ponzi.pojo.SysRoleMenu;
import com.uaa.ponzi.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public int addOrUpdate(String roleId, List<String> menuIdList) {
        UpdateWrapper<SysRoleMenu> deleteWrapper = new UpdateWrapper<>();
        deleteWrapper.eq("sys_role_id", roleId);
        roleMenuMapper.delete(deleteWrapper);

        int rows = 0;
        for (String menuId : menuIdList) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setSysMenuId(menuId);
            roleMenu.setSysRoleId(roleId);
            rows += roleMenuMapper.insert(roleMenu);
        }
        return rows;
    }

    @Override
    public List<SysRoleMenu> findByRoleId(String roleId) {
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sys_role_id", roleId);
        return roleMenuMapper.selectList(queryWrapper);
    }
}
