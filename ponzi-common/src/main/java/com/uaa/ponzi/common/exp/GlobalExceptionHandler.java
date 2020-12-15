package com.uaa.ponzi.common.exp;

import com.uaa.ponzi.common.util.RestResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public RestResponseUtil httpMessageNotReadableExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException e) {
        log.error("发生异常，原因是：{}", e.getMessage());
        return RestResponseUtil.error("数据格式错误");
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public RestResponseUtil unauthorizedExceptionHandler(HttpServletRequest request, UnauthorizedException e) {
        log.error("发生异常，原因是：{}", e.getMessage());
        return RestResponseUtil.error("没有权限操作");
    }

    @ExceptionHandler(value = ValidationException.class)
    @ResponseBody
    public RestResponseUtil validationExceptionHandler(HttpServletRequest request, ValidationException e) {
        log.error("发生异常，原因是：{}", e.getMessage());
        return RestResponseUtil.error(e.getMessage());
    }

    @ExceptionHandler(value = UnknownAccountException.class)
    @ResponseBody
    public RestResponseUtil unknownAccountExceptionHandler(HttpServletRequest request, UnknownAccountException e) {
        log.error("发生异常，原因是：{}", e.getMessage());
        return RestResponseUtil.error("账号或密码不正确");
    }

    @ExceptionHandler(value = IncorrectCredentialsException.class)
    @ResponseBody
    public RestResponseUtil incorrectCredentialsExceptionHandler(HttpServletRequest request, IncorrectCredentialsException e) {
        log.error("发生异常，原因是：{}", e.getMessage());
        return RestResponseUtil.error("账号或密码不正确");
    }


    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public RestResponseUtil globalExceptionHandler(HttpServletRequest request, GlobalException e) {
        log.error("发生异常，原因是：{}", e.getMessage());
        return RestResponseUtil.error(e.getMessage());
    }

    // 必须要先在配置文件里添加下面两个内容才能生效
    // #出现错误时, 直接抛出异常
    //spring.mvc.throw-exception-if-no-handler-found=true
    //#不要为我们工程中的资源文件建立映射
    //spring.resources.add-mappings=false
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public RestResponseUtil noHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        log.error("发生异常，原因是：{}", e.getMessage());
        return RestResponseUtil.error("资源不存在");
    }
}