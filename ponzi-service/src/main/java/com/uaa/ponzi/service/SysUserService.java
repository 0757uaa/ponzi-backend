package com.uaa.ponzi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uaa.ponzi.bo.SysUserBo;
import com.uaa.ponzi.dto.SysUserRoleDto;
import com.uaa.ponzi.pojo.SysUser;
import com.uaa.ponzi.query.SysUserQuery;

import java.util.List;

public interface SysUserService extends IService<SysUser> {

    /**
     * 查找所有
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 添加新用户
     * @param entity {@link SysUser}
     * @param roleId 角色id
     * @return
     */
    int insertNew(SysUser entity, String roleId);

    /**
     * 更新用户
     * @param user {@link SysUser}
     * @return
     */
    boolean updateSysUser(SysUser user);

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return
     */
    SysUser selectByUsername(String username);

    /**
     * 分页条件查询用户
     * @param page {@link Page}
     * @param sysUserQuery {@link SysUserQuery}
     * @return
     */
    IPage<SysUserBo> pageQuery(Page page, SysUserQuery sysUserQuery);

    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return
     */
    boolean checkExistsUsername(String username);

    /**
     * 根据用户id查找用户信息及用户拥有的角色
     * @param userId 用户id
     * @return
     */
    SysUserRoleDto findUserRoleByUserId(String userId);

    /**
     * 更新用户信息
     * @param user
     * @param roleId 角色id
     */
    void updateUser(SysUser user, String roleId);
}
