package com.uaa.ponzi.base.controller;

import com.uaa.ponzi.common.util.RestResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseLoginController extends BaseController {

    @GetMapping("/error_login_fail")
    public RestResponseUtil errorLoginFail() {
        return RestResponseUtil.error("请登录");
    }


    @PostMapping("/login_success")
    public RestResponseUtil loginSuccess() {
        return RestResponseUtil.ok("登录成功");
    }

}
