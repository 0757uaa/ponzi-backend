package com.uaa.ponzi.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;

public class RestResponseUtil extends JSONObject {
    // 状态
    private static final String STATUS = "status";
    // 返回数据
    private static final String DATA = "data";
    // 返回信息提示
    private static final String TIPS = "tips";

    public static RestResponseUtil ok(String tips, Object data) {
        RestResponseUtil util = new RestResponseUtil();
        util.put(STATUS, HttpStatus.OK.value());
        util.put(TIPS, tips);
        util.put(DATA, data);
        return util;
    }

    public static RestResponseUtil ok(String tips) {
        return ok(tips, null);
    }

    public static RestResponseUtil ok(Object data) {
        return ok("", data);
    }

    public static RestResponseUtil ok() {
        return ok("", null);
    }

    public static RestResponseUtil error(String tips, Object data) {
        RestResponseUtil util = new RestResponseUtil();
        util.put(STATUS, HttpStatus.BAD_REQUEST.value());
        util.put(TIPS, tips);
        util.put(DATA, data);
        return util;
    }

    public static RestResponseUtil error(String tips) {
        return error(tips, null);
    }

    public static RestResponseUtil error(Object data) {
        return error("", data);
    }

    public static RestResponseUtil error() {
        return error("", null);
    }


}
