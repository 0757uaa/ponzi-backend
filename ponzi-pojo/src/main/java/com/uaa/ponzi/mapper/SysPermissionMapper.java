package com.uaa.ponzi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uaa.ponzi.bo.SysPermissionBo;
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
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermissionBo> findByUserIdAndPermissionType(@Param("userId") String userId, @Param("permissionType") Integer permissionType);

}
