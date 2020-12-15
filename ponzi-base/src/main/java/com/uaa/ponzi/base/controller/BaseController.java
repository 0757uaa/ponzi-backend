package com.uaa.ponzi.base.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uaa.ponzi.common.exp.ValidationException;
import com.uaa.ponzi.common.util.HttpServletUtil;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
    private static final long PAGE_SIZE = 10L;
    private static final long PAGE_CURRENT = 1L;

    // 检验有错，抛异常处理
    public void valid(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
    }

    // 初始化分页参数
    protected Page initPage() {
        Page page = new Page();
        HttpServletRequest request = HttpServletUtil.getRequest();
        String size = request.getParameter("size"); // 每页显示数量
        String current = request.getParameter("current"); // 当前页码
        if (StringUtils.isEmpty(size)) {
            page.setSize(PAGE_SIZE);
        } else {
            page.setSize(Long.parseLong(size));
        }

        if (StringUtils.isEmpty(current)) {
            page.setCurrent(PAGE_CURRENT);
        } else {
            page.setCurrent(Long.parseLong(current));
        }

        return page;
    }

}
