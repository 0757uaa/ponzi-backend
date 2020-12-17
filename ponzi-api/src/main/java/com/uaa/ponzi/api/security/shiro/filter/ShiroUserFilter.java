package com.uaa.ponzi.api.security.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.uaa.ponzi.common.enums.HttpStatusEnum;
import com.uaa.ponzi.common.util.RestResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Slf4j
public class ShiroUserFilter extends UserFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        log.error("必须要登录");
//        return super.onAccessDenied(request, response);
        RestResponseUtil.writeString(response, JSON.toJSONString(RestResponseUtil.error(HttpStatusEnum.LOGIN_TIMEOVER)));
        return false;
    }
}
