package com.uaa.ponzi.api.controller;

import com.uaa.ponzi.base.controller.BaseController;
import com.uaa.ponzi.bo.SysTreeMenuBo;
import com.uaa.ponzi.common.util.RestResponseUtil;
import com.uaa.ponzi.dto.SysMenuNewDto;
import com.uaa.ponzi.pojo.SysMenu;
import com.uaa.ponzi.service.SysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sys_menu")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 添加菜单
     * @param menuNewDto
     * @param bindingResult
     * @return
     */
    @PostMapping("/add")
    @RequiresPermissions("menu:link:add:save")
    public RestResponseUtil add(@Valid SysMenuNewDto menuNewDto, BindingResult bindingResult) {
        super.valid(bindingResult);

        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(menuNewDto, sysMenu);
        sysMenuService.save(sysMenu);

        return RestResponseUtil.ok("操作成功");
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @PostMapping("/delete")
    @RequiresPermissions("menu:link:delete")
    public RestResponseUtil delete(String menuId) {
        sysMenuService.deleteByMenuId(menuId);

        return RestResponseUtil.ok("操作成功");
    }

    /**
     * 以树状形式加载所有的菜单
     * @return
     */
    @PostMapping("tree_all_menu")
    @RequiresPermissions("menu:link:list")
    public RestResponseUtil treeAllMenu() {
        List<SysTreeMenuBo> menuBoList = sysMenuService.treeAllMenu();
        return RestResponseUtil.ok(menuBoList);
    }

}
