package com.uaa.ponzi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uaa.ponzi.bo.SysUserBo;
import com.uaa.ponzi.pojo.SysUser;
import com.uaa.ponzi.query.SysUserQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    IPage<SysUserBo> pageQuery(Page page, @Param("sysUserQuery") SysUserQuery sysUserQuery);

}
