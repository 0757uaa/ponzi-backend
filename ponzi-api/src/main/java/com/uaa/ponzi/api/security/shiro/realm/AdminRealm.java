package com.uaa.ponzi.api.security.shiro.realm;

import com.uaa.ponzi.bo.SysPermissionBo;
import com.uaa.ponzi.dto.SysRoleDto;
import com.uaa.ponzi.dto.SysUserRoleDto;
import com.uaa.ponzi.enums.SysPermissionTypeEnum;
import com.uaa.ponzi.pojo.SysUser;
import com.uaa.ponzi.service.SysPermissionService;
import com.uaa.ponzi.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private SysUserService sysUserService;

    @Autowired
    @Lazy
    private SysPermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("开始鉴权操作");
        SimpleAuthorizationInfo simpleAuthorizationInfo =  new SimpleAuthorizationInfo();
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();

        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();

        // 用户拥有的访问URL权限
        List<SysPermissionBo> permissionBoList = permissionService.findByUserIdAndPermissionType(sysUser.getId(), SysPermissionTypeEnum.URL);
        if (!CollectionUtils.isEmpty(permissionBoList)) {
            for (SysPermissionBo permissionBo : permissionBoList) {
                permissionSet.add(permissionBo.getPermissionCode());
            }
        }

        // 用户拥有的角色
        SysUserRoleDto userRoleDto = sysUserService.findUserRoleByUserId(sysUser.getId());
        if (null != userRoleDto) {
            List<SysRoleDto> roleDtos = userRoleDto.getRoleDtoList();
            if (!CollectionUtils.isEmpty(roleDtos)) {
                for (SysRoleDto roleDto : roleDtos) {
                    roleSet.add(roleDto.getRoleCode());
                }
            }
        }

        simpleAuthorizationInfo.setRoles(roleSet);
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("开始登录认证操作");

        Object usernameObj = authenticationToken.getPrincipal();
        if (StringUtils.isEmpty(usernameObj)) {
            return null;
        }

        Object passwordObj = authenticationToken.getCredentials();
        if (StringUtils.isEmpty(passwordObj)) {
            return null;
        }

        SysUser sysUser = sysUserService.selectByUsername(usernameObj.toString());
        if (null == sysUser) {
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                sysUser, // 表示凭证
                sysUser.getEncryptPassword(),
                ByteSource.Util.bytes(sysUser.getSalt()),
                getName()
        );

        return simpleAuthenticationInfo;
    }
}
