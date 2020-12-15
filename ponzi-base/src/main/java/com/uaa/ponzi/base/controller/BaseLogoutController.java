package com.uaa.ponzi.base.controller;

import com.uaa.ponzi.common.util.RestResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseLogoutController extends BaseController {

    @PostMapping("/api/logout")
    public RestResponseUtil logout() {
        SecurityUtils.getSubject().logout();
        return RestResponseUtil.ok("退出登录成功");
    }

}
