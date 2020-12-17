package com.uaa.ponzi.api.security.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Slf4j
@Deprecated
public class AuthenticationFilter extends FormAuthenticationFilter {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(AuthenticationFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.error("登录错误出错，这里经过");
        return super.onAccessDenied(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.error("登录错误出错/..........");
        return super.onLoginFailure(token, e, request, response);
    }
}
