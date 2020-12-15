package com.uaa.ponzi.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uaa.ponzi.base.controller.BaseController;
import com.uaa.ponzi.bo.SysPermissionBo;
import com.uaa.ponzi.bo.SysTreeMenuBo;
import com.uaa.ponzi.common.util.RestResponseUtil;
import com.uaa.ponzi.dto.SysUserLoginDto;
import com.uaa.ponzi.enums.SysPermissionTypeEnum;
import com.uaa.ponzi.pojo.SysUser;
import com.uaa.ponzi.service.SysPermissionService;
import com.uaa.ponzi.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/sys_user/login/noauth")
public class SysUserLoginController extends BaseController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     *
     * @param sysUserLoginDTO
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public RestResponseUtil adminLogin(@Valid SysUserLoginDto sysUserLoginDTO, BindingResult bindingResult) {
        super.valid(bindingResult);
        log.error("登录前");
        UsernamePasswordToken token = new UsernamePasswordToken(sysUserLoginDTO.getUsername(), sysUserLoginDTO.getPassword());
        SecurityUtils.getSubject().login(token);
        log.error("登录后");

        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();

        JSONObject data = new JSONObject();
        // 获取拥有的菜单
        List<SysTreeMenuBo> menuBoList = roleService.treeRoleMenuByUserId(sysUser.getId());
        JSONArray menus = new JSONArray();
        for (SysTreeMenuBo b : menuBoList) {
            menus.add(SysRoleController.transTopTreeNode(b));
        }
        data.put("menus", menus);

        // 获取拥有的操作权限
        JSONArray permissions = new JSONArray();
        // 用户拥有的访问URL权限
        List<SysPermissionBo> permissionBoList = permissionService.findByUserIdAndPermissionType(sysUser.getId(), SysPermissionTypeEnum.BUTTON);
        if (!CollectionUtils.isEmpty(permissionBoList)) {
            for (SysPermissionBo permissionBo : permissionBoList) {
                permissions.add(permissionBo);
            }
        }
        data.put("permissions", permissions);
        return RestResponseUtil.ok("登录成功", data);
    }

}
