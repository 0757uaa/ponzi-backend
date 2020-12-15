package com.uaa.ponzi.api.controller;

import com.uaa.ponzi.base.controller.BaseController;
import com.uaa.ponzi.common.util.RestResponseUtil;
import com.uaa.ponzi.dto.SysPermissionNewDto;
import com.uaa.ponzi.pojo.SysPermission;
import com.uaa.ponzi.service.SysPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sys_permission")
public class SysPermissionController extends BaseController {

    @Autowired
    private SysPermissionService sysPermissionService;


    /**
     * 添加新权限
     * @param permissionNewDto
     * @param bindingResult
     * @return
     */
    @PostMapping("/add")
    public RestResponseUtil add(
            @Valid SysPermissionNewDto permissionNewDto
            , BindingResult bindingResult) {

        super.valid(bindingResult);

        if (sysPermissionService.checkExistsPermissionCode(permissionNewDto.getPermissionCode())) {
            return RestResponseUtil.error("权限编码已存在,请重新填写");
        }

        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(permissionNewDto, sysPermission);
        boolean result = sysPermissionService.save(sysPermission);

        return result ? RestResponseUtil.ok("添加成功") : RestResponseUtil.error("添加失败");
    }

    /**
     * 查询菜单下的所有权限
     * @param menuId
     * @return
     */
    @PostMapping("/list_by_menuid")
    public RestResponseUtil listByMenuId(String menuId) {
        return RestResponseUtil.ok(sysPermissionService.findByMenuId(menuId));
    }

    /**
     * 删除权限
     * @param permissionId
     * @param menuId
     * @return
     */
    @PostMapping("/delete_permission")
    public RestResponseUtil deletePermission(String permissionId, String menuId) {
        sysPermissionService.deletePermission(permissionId, menuId);
        return RestResponseUtil.ok();
    }
}
