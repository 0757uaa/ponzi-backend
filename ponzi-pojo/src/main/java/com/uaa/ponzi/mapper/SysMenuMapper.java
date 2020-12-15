package com.uaa.ponzi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uaa.ponzi.bo.SysTreeMenuBo;
import com.uaa.ponzi.pojo.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysTreeMenuBo> treeAllMenu();

    List<SysTreeMenuBo> queryByPid(@Param("pid") String pid);

    List<SysTreeMenuBo> treeRoleMenuByRoleId(@Param("roleId") String roleId);

    List<SysTreeMenuBo> treeRoleMenuByRoleIdAndParentId(@Param("roleId") String roleId, @Param("pid") String pid);

    List<SysTreeMenuBo> treeRoleMenuByUserId(@Param("userId") String userId);

    List<SysTreeMenuBo> treeRoleMenuByUserIdAndMenuId(@Param("userId") String userId, @Param("menuId") String menuId);

    void deleteMenuById(@Param("id") String id);
}
