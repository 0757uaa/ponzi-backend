package com.uaa.ponzi.common.util;

import com.alibaba.fastjson.JSONObject;
import com.uaa.ponzi.common.enums.HttpStatusEnum;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletResponse;
import java.io.IOException;

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

    private static RestResponseUtil error(int status, String tips, Object data) {
        RestResponseUtil util = new RestResponseUtil();
        util.put(STATUS, status);
        util.put(TIPS, tips);
        util.put(DATA, data);
        return util;
    }

    public static RestResponseUtil error(HttpStatusEnum statusEnum) {
        return error(statusEnum.getValue(), statusEnum.getReasonPhrase(), null);
    }

    public static RestResponseUtil error(String tips, Object data) {
        return error(HttpStatus.BAD_REQUEST.value(), tips, data);
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

    /**
     * 返回数据
     * @param response
     * @param data
     * @throws IOException
     */
    public static void writeString(ServletResponse response, String data) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(data);
    }
}
