package com.uaa.ponzi.base.controller;

import com.uaa.ponzi.common.enums.HttpStatusEnum;
import com.uaa.ponzi.common.util.RestResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseAuthController extends BaseController {

    @GetMapping("/error_unauth")
    public RestResponseUtil error_login_fail() {
//        return RestResponseUtil.error("没有权限");
        return RestResponseUtil.error(HttpStatusEnum.NO_PERMISSION);
    }


}
