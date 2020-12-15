package com.uaa.ponzi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uaa.ponzi.bo.SysTreeMenuBo;
import com.uaa.ponzi.pojo.SysMenu;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    int deleteByMenuId(String menuId);

    List<SysTreeMenuBo> treeAllMenu();

}
