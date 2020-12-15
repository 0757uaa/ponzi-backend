package com.uaa.ponzi.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uaa.ponzi.base.controller.BaseController;
import com.uaa.ponzi.bo.SysUserBo;
import com.uaa.ponzi.common.enums.DisabledEnum;
import com.uaa.ponzi.common.util.RestResponseUtil;
import com.uaa.ponzi.dto.SysUserNewDto;
import com.uaa.ponzi.pojo.SysUser;
import com.uaa.ponzi.query.SysUserQuery;
import com.uaa.ponzi.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/sys_user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取所有管理员
     * @return
     */
    @Deprecated
    @GetMapping("/find_all")
    private List<SysUser> findAll() {
        return sysUserService.selectAll();
    }

    /**
     * 添加新用户
     * @param sysUserNewDto
     * @param bindingResult
     * @return
     */
    @RequiresPermissions("admin:link:add:save")
    @PostMapping("/add")
    public RestResponseUtil add(@Valid SysUserNewDto sysUserNewDto, BindingResult bindingResult) {
        super.valid(bindingResult);

        if (sysUserService.checkExistsUsername(sysUserNewDto.getUsername())) {
            return RestResponseUtil.error("用户名已存在");
        }

        SysUser sysUser = SysUser.initPassword(sysUserNewDto.getDencryptPassword());
        sysUser.setUsername(sysUserNewDto.getUsername());
        sysUser.setSex(sysUserNewDto.getSex());
        sysUserService.insertNew(sysUser, sysUserNewDto.getRoleId());

        return RestResponseUtil.ok();
    }

    /**
     * 分页条件查询
     * @param sysUserQuery
     * @return
     */
    @RequiresPermissions("admin:link:query:list")
    @PostMapping("/page_query_user")
    public RestResponseUtil pageQuery(SysUserQuery sysUserQuery) {
        Page<SysUserBo> page = super.initPage();
        return RestResponseUtil.ok(sysUserService.pageQuery(page, sysUserQuery));
    }

    /**
     * 启用用户
     * @param id 用户id
     */
    @RequiresPermissions("admin:link:enable")
    @PostMapping("/enable_user")
    public RestResponseUtil enableUser(String id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setDisabled(DisabledEnum.NO);
        sysUserService.updateSysUser(sysUser);
        return RestResponseUtil.ok("启用成功");
    }

    /**
     * 禁用用户
     * @param id 用户id
     */
    @RequiresPermissions("admin:link:disable")
    @PostMapping("/disabled_user")
    public RestResponseUtil disabledUser(String id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setDisabled(DisabledEnum.YES);
        sysUser.setDisabledTime(new Date());
        sysUserService.updateSysUser(sysUser);
        return RestResponseUtil.ok("禁用成功");
    }

    /**
     * 查找用户信息及其拥有的角色
     * @param id 用户id
     * @return
     */
    @RequiresPermissions("admin:link:edit:detail")
    @PostMapping("find_user_and_role")
    public RestResponseUtil findUserAndRole(String id) {
        return RestResponseUtil.ok(sysUserService.findUserRoleByUserId(id));
    }

    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return
     */
    @PostMapping("/check_exists_username")
    public RestResponseUtil checkExistsUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return RestResponseUtil.error("请填写用户名");
        }

        if (sysUserService.checkExistsUsername(username)) {
            return RestResponseUtil.error("用户名已存在");
        }

        return RestResponseUtil.ok();
    }

    /**
     * 保存编辑用户
     * @param id 用户id
     * @param dencryptPassword 明文密码
     * @param roleId 角色id
     * @return
     */
    @RequiresPermissions("admin:link:detail:save")
    @PostMapping("/edit_user")
    public RestResponseUtil editUser(String id, String dencryptPassword, String roleId) {
        SysUser sysUser = null;
        if (!StringUtils.isEmpty(dencryptPassword)) {
            sysUser = SysUser.initPassword(dencryptPassword);
        } else {
            sysUser = new SysUser();
        }
        sysUser.setId(id);

        sysUserService.updateUser(sysUser, roleId);
        return RestResponseUtil.ok();
    }

}
