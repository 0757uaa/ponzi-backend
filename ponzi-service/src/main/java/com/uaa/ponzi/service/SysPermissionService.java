package com.uaa.ponzi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uaa.ponzi.bo.SysPermissionBo;
import com.uaa.ponzi.enums.SysPermissionTypeEnum;
import com.uaa.ponzi.pojo.SysPermission;

import java.util.List;

public interface SysPermissionService extends IService<SysPermission> {


    /**
     * 检查是否存在权限编码
     * @param permissionCode 权限编码
     * @return
     */
    boolean checkExistsPermissionCode(String permissionCode);

    /**
     * 查找菜单的权限
     * @param menuId 菜单id
     * @return
     */
    List<SysPermission> findByMenuId(String menuId);

    /**
     * 删除权限
     * @param permissionId 权限id
     * @param menuId 菜单id
     * @return
     */
    int deletePermission(String permissionId, String menuId);

    /**
     * 查找用户的权限
     * @param userId 用户id
     * @param permissionTypeEnum 权限类型 {@link SysPermissionTypeEnum}
     * @return
     */
    List<SysPermissionBo> findByUserIdAndPermissionType(String userId, SysPermissionTypeEnum permissionTypeEnum);
}
