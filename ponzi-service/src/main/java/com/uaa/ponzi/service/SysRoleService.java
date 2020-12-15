package com.uaa.ponzi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uaa.ponzi.bo.SysRolePermissionBo;
import com.uaa.ponzi.bo.SysTreeMenuBo;
import com.uaa.ponzi.bo.SysTreeRoleMenuPermissionBo;
import com.uaa.ponzi.pojo.SysRole;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取所有角色
     * @return
     */
    List<SysRole> allRoles();

    /**
     * 根据角色id查找对应的权限
     * @param roleId 角色id
     * @return
     */
    SysRolePermissionBo findRolePermissionByRoleId(String roleId);

    /**
     * 添加或修改角色权限
     * @param roleId 角色id
     * @param permissionIds 权限ids
     * @return
     */
    int addOrUpdateRolePermission(String roleId, List<String> permissionIds);

    /**
     * 检查是否存在角色编码
     * @param roleCode 角色编码
     * @return
     */
    boolean checkExistsRoleCode(String roleCode);

    /**
     * 检查是否存在角色名称
     * @param roleName 角色名称
     * @return
     */
    boolean checkExistsRoleName(String roleName);

    /**
     * 清除角色的所有权限
     * @param roleId 角色id
     * @return
     */
    int clearRolePermissions(String roleId);

    /**
     * 查找角色拥有的菜单
     * @param roleId 角色id
     * @return
     */
    List<SysTreeMenuBo> treeRoleMenuByRoleId(String roleId);

    /**
     * 查找用户拥有的菜单
     * @param userId 用户id
     * @return
     */
    List<SysTreeMenuBo> treeRoleMenuByUserId(String userId);

    /**
     * 根据角色查找拥有的菜单权限
     * @param roleId 角色id
     * @return
     */
    List<SysTreeRoleMenuPermissionBo> treeRoleMenuPermissionByRoleId(String roleId);

    /**
     * 添加或者修改角色的菜单权限
     * @param roleId
     * @param menuIdList
     * @param permissionIdList
     */
    void saveOrUpdateRoleMenuPermission(String roleId, List<String> menuIdList, List<String> permissionIdList);

    /**
     * 删除角色
     * @param roleId 角色id
     */
    void deleteRole(String roleId);
}
