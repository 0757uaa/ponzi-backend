package com.uaa.ponzi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uaa.ponzi.bo.SysTreeMenuBo;
import com.uaa.ponzi.mapper.SysMenuMapper;
import com.uaa.ponzi.mapper.SysRoleMenuMapper;
import com.uaa.ponzi.pojo.SysMenu;
import com.uaa.ponzi.pojo.SysRoleMenu;
import com.uaa.ponzi.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public int deleteByMenuId(String menuId) {
//        UpdateWrapper<SysRoleMenu> updateRoleMenuWrapper = new UpdateWrapper<>();
//        updateRoleMenuWrapper.eq("sys_menu_id", menuId);
//        roleMenuMapper.delete(updateRoleMenuWrapper);
//
//        UpdateWrapper<SysMenu> updateMenuWrapper = new UpdateWrapper<>();
//        updateMenuWrapper.eq("id", menuId);
//        menuMapper.delete(updateMenuWrapper);
        menuMapper.deleteMenuById(menuId);

        return 0;
    }

    @Override
    public List<SysTreeMenuBo> treeAllMenu() {
        return menuMapper.treeAllMenu();
    }
}
