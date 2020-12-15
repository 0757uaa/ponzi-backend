package com.uaa.ponzi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uaa.ponzi.bo.SysRolePermissionBo;
import com.uaa.ponzi.bo.SysTreeRoleMenuPermissionBo;
import com.uaa.ponzi.pojo.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
@Repository
public interface SysTreeRoleMenuPermissionBoMapper extends BaseMapper<SysTreeRoleMenuPermissionBo> {


    List<SysTreeRoleMenuPermissionBo> treeRoleMenuPermission(@Param("roleId") String roleId);


    List<SysTreeRoleMenuPermissionBo> treeRoleMenuPermissionByPid(@Param("roleId") String roleId, @Param("pid") String pid);

    List<SysPermission> queryPermissionByMenuId(@Param("menuId") String menuId);

    List<SysPermission> queryPermissionByMenuIdAndRoleId(@Param("menuId") String menuId, @Param("roleId") String roleId);
}
