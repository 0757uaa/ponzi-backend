package com.uaa.ponzi.api.controller;

import com.uaa.ponzi.base.controller.BaseController;
import com.uaa.ponzi.common.util.RestResponseUtil;
import com.uaa.ponzi.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/sys_userrole")
public class SysUserRoleController extends BaseController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 添加或者修改用户角色
     * @param userId 用户id
     * @param roleIds 角色ids,  ids以','连接
     * @return
     */
    @PostMapping("/add_or_update_userrole")
    public RestResponseUtil addOrUpdateUserRole(String userId, String roleIds) {
        if (StringUtils.isEmpty(roleIds)) {
            sysUserRoleService.clearUserRoles(userId);
            return RestResponseUtil.ok("操作成功");
        }

        List<String> roleIdList = Arrays.asList(roleIds.split(","));
        sysUserRoleService.addOrUpdateUserRole(userId, roleIdList);
        return RestResponseUtil.ok("操作成功");
    }
}
