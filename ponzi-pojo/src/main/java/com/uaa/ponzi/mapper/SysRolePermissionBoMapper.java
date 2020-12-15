package com.uaa.ponzi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uaa.ponzi.bo.SysRolePermissionBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
@Repository
public interface SysRolePermissionBoMapper extends BaseMapper<SysRolePermissionBo> {

    SysRolePermissionBo selectByRoleId(@Param("roleId") String roleId);

}
