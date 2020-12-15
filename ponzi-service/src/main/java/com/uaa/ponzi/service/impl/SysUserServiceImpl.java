package com.uaa.ponzi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uaa.ponzi.bo.SysUserBo;
import com.uaa.ponzi.dto.SysUserRoleDto;
import com.uaa.ponzi.mapper.SysUserMapper;
import com.uaa.ponzi.mapper.SysUserRoleMapper;
import com.uaa.ponzi.pojo.SysUser;
import com.uaa.ponzi.pojo.SysUserRole;
import com.uaa.ponzi.query.SysUserQuery;
import com.uaa.ponzi.service.SysUserRoleService;
import com.uaa.ponzi.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    public List<SysUser> selectAll() {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        queryWrapper
                .eq("disabled", 0)
                .eq("deleted", 0);
        return userMapper.selectList(queryWrapper);
    }

    @Transactional(readOnly = false)
    @Override
    public int insertNew(SysUser entity, String roleId) {
        int rows = userMapper.insert(entity);
        if (rows > 0) {
            SysUserRole userRole = new SysUserRole();
            userRole.setSysUserId(entity.getId());
            userRole.setSysRoleId(roleId);
            rows = userRoleMapper.insert(userRole);
        }
        return rows;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean updateSysUser(SysUser user) {
        int row = userMapper.updateById(user);
        return  row > 0;
    }

    @Override
    public SysUser selectByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        queryWrapper
                .eq("username", username)
                .eq("disabled", 0)
                .eq("deleted", 0);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<SysUserBo> pageQuery(Page page, SysUserQuery sysUserQuery) {
        return userMapper.pageQuery(page, sysUserQuery);
    }

    @Override
    public boolean checkExistsUsername(String username) {
        return (null != selectByUsername(username));
    }

    @Override
    public SysUserRoleDto findUserRoleByUserId(String userId) {
        return userRoleMapper.selectUserRoleByUserId(userId);
    }

    @Transactional(readOnly = false)
    @Override
    public void updateUser(SysUser user, String roleId) {
        userMapper.updateById(user);

        List<String> roleIds = new ArrayList<>(1);
        roleIds.add(roleId);
        userRoleService.addOrUpdateUserRole(user.getId(), roleIds);
    }
}
