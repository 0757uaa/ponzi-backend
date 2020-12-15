package com.uaa.ponzi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uaa.ponzi.pojo.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuService extends IService<SysRoleMenu> {

    int addOrUpdate(String roleId, List<String> menuIdList);

    /**
     * 角色id查找角色菜单
     * @param roleId 角色id
     * @return
     */
    List<SysRoleMenu> findByRoleId(String roleId);
}
