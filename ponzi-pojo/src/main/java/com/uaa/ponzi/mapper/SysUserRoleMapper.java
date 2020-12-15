package com.uaa.ponzi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uaa.ponzi.dto.SysUserRoleDto;
import com.uaa.ponzi.pojo.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 根据用户id查找用户信息及用户拥有的角色
     * @param userId 用户id
     * @return
     */
    SysUserRoleDto selectUserRoleByUserId(@Param("userId") String userId);

}
